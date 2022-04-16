package com.blockchain.blockexplorer.BlockInformation;

import com.blockchain.blockexplorer.Model.Response;
import org.web3j.protocol.Web3j;

public interface GetLatestBlock {
    public Response getLatestBlockInformation(Web3j web3j);

    public Response getLatestBlocksInformation(Web3j web3j, int countFromLatest);

    public Response getSpecificBlockInformation(Web3j web3j, int blockNumber);
}
