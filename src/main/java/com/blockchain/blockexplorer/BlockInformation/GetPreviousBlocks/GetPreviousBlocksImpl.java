package com.blockchain.blockexplorer.BlockInformation.GetPreviousBlocks;

import com.blockchain.blockexplorer.BlockInformation.MultiThreadBlockInfo.GetBlockInfoMultiThread;
import com.blockchain.blockexplorer.BlockInformationConsumer;
import com.blockchain.blockexplorer.Model.BlockInformations.BlockInformationModel;
import com.blockchain.blockexplorer.Model.Response;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetPreviousBlocksImpl implements GetPreviousBlocksService {
    public Response getPreviousBlocksMultiThread(Web3j web3j, int lowerBlock, int higherBlock) {
        Response response = new Response();
        response.setStatus(false);

        try {
            List<BlockInformationModel> blockInformations = new ArrayList<BlockInformationModel>();
            EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
            int transactionCount = block.getTransactions().size();
            int latestBlockNumber = block.getNumber().intValue();

            if (latestBlockNumber < higherBlock) {
                response.setMessage("Queried Block (" + higherBlock + ") is not Mined Yet!");
                return response;
            }

            List<GetBlockInfoMultiThread> listOfObj = new ArrayList<GetBlockInfoMultiThread>();
            for (int blockNumber = lowerBlock; blockNumber <= higherBlock; blockNumber++) {
                GetBlockInfoMultiThread obj = new GetBlockInfoMultiThread(blockNumber);
                obj.start();
                listOfObj.add(obj);
            }

            // check if the threads have finished
            List<BlockInformationModel> results = new ArrayList<BlockInformationModel>();
            while (true) {
                int finishedCounter = 0;
                results.clear();

                for (int i = 0; i < listOfObj.size(); i++) {
                    if (listOfObj.get(i).isFinished()) {
                        finishedCounter++;
                        results.add(listOfObj.get(i).getResult());
                    }
                }

                if (finishedCounter == listOfObj.size()) {
                    break;
                }
                Thread.sleep(200);
            }
            Collections.sort(results); // sort the collections of blocks object based on block number

            response.setStatus(true);
            response.setMessage("Block Information's Fetched Successfully");
            response.setData(results);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public Response getPreviousBlocks(Web3j web3j, int lowerBlock, int higherBlock) {
        Response response = new Response();
        response.setStatus(false);

        try {
            List<BlockInformationModel> blockInformations = new ArrayList<BlockInformationModel>();
            EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST,
                    false).send().getBlock();
            int transactionCount = block.getTransactions().size();
            int latestBlockNumber = block.getNumber().intValue();

            if (latestBlockNumber < higherBlock) {
                response.setMessage("Queried Block (" + higherBlock + ") is not Mined Yet!");
                return response;
            }

            for (int blockNumber = lowerBlock; blockNumber <= higherBlock; blockNumber++) {
                BlockInformationModel blockInformation = (BlockInformationModel) BlockInformationConsumer.getSpecificBlockInformation(blockNumber).getData();
                blockInformations.add(blockInformation);
            }
            response.setStatus(true);
            response.setMessage("Block Information's Fetched Successfully");
            response.setData(blockInformations);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
