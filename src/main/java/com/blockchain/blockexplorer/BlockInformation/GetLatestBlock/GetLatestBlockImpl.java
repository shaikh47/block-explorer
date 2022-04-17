package com.blockchain.blockexplorer.BlockInformation.GetLatestBlock;

import com.blockchain.blockexplorer.BlockInformation.MultiThreadBlockInfo.GetBlockInfoMultiThread;
import com.blockchain.blockexplorer.Model.BlockInformations.BlockInformationModel;
import com.blockchain.blockexplorer.Model.Response;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlock.Block;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetLatestBlockImpl implements GetLatestBlockService {
    public Response getLatestBlockInformation(Web3j web3j) {
        Response response = new Response();
        response.setStatus(false);

        try {
            Block block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
            int transactionCount = block.getTransactions().size();
            BlockInformationModel blockInformation = new BlockInformationModel(block.getNumber().intValue(),
                    transactionCount);

            response.setStatus(true);
            response.setMessage("Block Information Fetched Successfully");
            response.setData(blockInformation);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public Response getLatestBlocksInformationWithLastUpdatedBlockNumber(Web3j web3j, int lastUpdatedBlockNumber) {
        Response response = new Response();
        response.setStatus(false);

        try {
            List<BlockInformationModel> blockInformations = new ArrayList<BlockInformationModel>();
            EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST,
                    false).send().getBlock();
            int transactionCount = block.getTransactions().size();
            int latestBlockNumber = block.getNumber().intValue();
            BlockInformationModel blockInformation = new BlockInformationModel(latestBlockNumber, transactionCount);
            blockInformations.add(blockInformation);

            if(latestBlockNumber == lastUpdatedBlockNumber) {
                response.setStatus(true);
                response.setMessage("Already Have the Latest Block");
                response.setData(blockInformation);
                return response;
            }

            int diff=latestBlockNumber-lastUpdatedBlockNumber;
            int MAXIMUM_ALLOWED_QUERY=30;
            if(diff>MAXIMUM_ALLOWED_QUERY) // allow maximum 20 blocks
                diff=MAXIMUM_ALLOWED_QUERY; 

            for (int i = 1; i < diff; i++) {
                blockInformation = (BlockInformationModel) getSpecificBlockInformation(web3j,
                        latestBlockNumber - i).getData();
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

    public Response getLatestBlocksInformation(Web3j web3j, int countDownFromLatest) {
        Response response = new Response();
        response.setStatus(false);

        try {
            List<BlockInformationModel> blockInformations = new ArrayList<BlockInformationModel>();
            EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST,
                    false).send().getBlock();
            int transactionCount = block.getTransactions().size();
            int latestBlockNumber = block.getNumber().intValue();

            block.getAuthor(); // probably miner
            block.getTransactions(); // transaction hashes
            block.getBaseFeePerGas();
            block.getDifficulty();
            block.getExtraData();
            block.getGasLimit();
            block.getGasUsed();
            block.getHash();
            block.getMiner();
            block.getLogsBloom();
            block.getNonce();
            block.getParentHash();
            block.getSize();
            block.getTimestamp();
            block.getTotalDifficulty();

            BlockInformationModel blockInformation = new BlockInformationModel(latestBlockNumber, transactionCount);
            blockInformations.add(blockInformation);

            for (int i = 1; i < countDownFromLatest; i++) {
                blockInformation = (BlockInformationModel) getSpecificBlockInformation(web3j,
                        latestBlockNumber - i).getData();
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

    // implemented multi threading
    public Response getLatestBlocksInformationMultiThread(Web3j web3j, int countFromLatest) {
        Response response = new Response();
        response.setStatus(false);

        try {
            List<BlockInformationModel> blockInformations = new ArrayList<BlockInformationModel>();
            EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
            int transactionCount = block.getTransactions().size();
            int latestBlockNumber = block.getNumber().intValue();
            BlockInformationModel blockInformation = new BlockInformationModel(latestBlockNumber, transactionCount);
            blockInformations.add(blockInformation);

            List<GetBlockInfoMultiThread> listOfObj = new ArrayList<GetBlockInfoMultiThread>();
            for (int i = 1; i < countFromLatest; i++) {
                GetBlockInfoMultiThread obj = new GetBlockInfoMultiThread(latestBlockNumber - i);
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

            results.add(blockInformation);
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

    public Response getSpecificBlockInformation(Web3j web3j, int blockNumber) {
        Response response = new Response();
        response.setStatus(false);

        try {
            Block block = web3j
                    .ethGetBlockByNumber(DefaultBlockParameter.valueOf(BigInteger.valueOf(blockNumber)), false).send()
                    .getBlock();
            int transactionCount = block.getTransactions().size();
            BlockInformationModel blockInformation = new BlockInformationModel(block.getNumber().intValue(),
                    transactionCount);

            response.setStatus(true);
            response.setMessage("Block Information Fetched Successfully");
            response.setData(blockInformation);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
