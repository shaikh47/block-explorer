package com.blockchain.blockexplorer.BlockInformation.MultiThreadBlockInfo;

import com.blockchain.blockexplorer.BlockInformationConsumer;
import com.blockchain.blockexplorer.Model.BlockInformations.BlockInformationModel;
import com.blockchain.blockexplorer.Model.Response;

import java.util.List;

public class GetBlockInfoMultiThread extends Thread {
    private int blockNumber;
    private boolean isFinished=false;
    private BlockInformationModel result;

    public boolean isFinished() {
        return isFinished;
    }

    public BlockInformationModel getResult() {
        return result;
    }

    // initialize the constructor
    public GetBlockInfoMultiThread(int blockNumber) {
        this.blockNumber=blockNumber;
    }

    public void run() {
        // main code block to run
        Response response = BlockInformationConsumer.getSpecificBlockInformation(blockNumber);
        if(response.getStatus()) {
            result=(BlockInformationModel) response.getData();
            isFinished=true;
        }
    }
}
