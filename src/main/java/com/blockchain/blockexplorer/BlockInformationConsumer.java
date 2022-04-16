package com.blockchain.blockexplorer;

import com.blockchain.blockexplorer.BlockInformation.GetLatestBlock;
import com.blockchain.blockexplorer.BlockInformation.GetLatestBlockImpl;
import com.blockchain.blockexplorer.Config.Config;
import com.blockchain.blockexplorer.Model.Response;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class BlockInformationConsumer {
    public static Web3j web3j = Web3j.build(new HttpService(Config.ETHEREUM_URL));

    static GetLatestBlock getLatestBlock = new GetLatestBlockImpl();

    public static Response getBlockInformation() {
        Response response = new Response();
        response.setStatus(false);

        response = getLatestBlock.getLatestBlockInformation(web3j);
        return response;
    }

    public static Response getBlockInformation(int countFromLatest) {
        Response response = new Response();
        response.setStatus(false);

        response = getLatestBlock.getLatestBlocksInformation(web3j, countFromLatest);
        return response;
    }
}
