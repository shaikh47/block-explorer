package com.blockchain.blockexplorer.BlockInformation;

import com.blockchain.blockexplorer.BlockInformationConsumer;
import com.blockchain.blockexplorer.Model.BlockInformations.BlockInformationModel;
import com.blockchain.blockexplorer.Model.Response;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlock.Block;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GetLatestBlockImpl implements  GetLatestBlock{
    public Response getLatestBlockInformation(Web3j web3j) {
        Response response = new Response();
        response.setStatus(false);

        try {
            Block block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
            int transactionCount = block.getTransactions().size();
            BlockInformationModel blockInformation = new BlockInformationModel(block.getNumber().intValue(), transactionCount);

            response.setStatus(true);
            response.setMessage("Block Information Fetched Successfully");
            response.setData(blockInformation);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
        }
        return  response;
    }

    public Response getLatestBlocksInformation(Web3j web3j, int countFromLatest) {
        Response response = new Response();
        response.setStatus(false);

        try {
            List<BlockInformationModel> blockInformations = new ArrayList<BlockInformationModel>();
            EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
            int transactionCount = block.getTransactions().size();
            int latestBlockNumber = block.getNumber().intValue();
            BlockInformationModel blockInformation = new BlockInformationModel(latestBlockNumber, transactionCount);
            blockInformations.add(blockInformation);

            for (int i = 1; i < countFromLatest; i++) {
                blockInformation = (BlockInformationModel) getSpecificBlockInformation(web3j, latestBlockNumber-i).getData();
                blockInformations.add(blockInformation);
            }
            response.setStatus(true);
            response.setMessage("Block Information's Fetched Successfully");
            response.setData(blockInformations);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
        }
        return  response;
    }

    public Response getSpecificBlockInformation(Web3j web3j, int blockNumber) {
        Response response = new Response();
        response.setStatus(false);

        try {
            Block block = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(BigInteger.valueOf(blockNumber)), false).send().getBlock();
            int transactionCount = block.getTransactions().size();
            BlockInformationModel blockInformation = new BlockInformationModel(block.getNumber().intValue(), transactionCount);

            response.setStatus(true);
            response.setMessage("Block Information Fetched Successfully");
            response.setData(blockInformation);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
        }
        return  response;
    }
}
