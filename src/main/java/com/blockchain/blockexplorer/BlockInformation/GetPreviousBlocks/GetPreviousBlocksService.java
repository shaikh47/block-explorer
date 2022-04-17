package com.blockchain.blockexplorer.BlockInformation.GetPreviousBlocks;

import com.blockchain.blockexplorer.Model.Response;
import org.web3j.protocol.Web3j;

public interface GetPreviousBlocksService {
    public Response getPreviousBlocksMultiThread(Web3j web3j, int lowerBlock, int higherBlock);

    public Response getPreviousBlocks(Web3j web3j, int lowerBlock, int higherBlock);
}
