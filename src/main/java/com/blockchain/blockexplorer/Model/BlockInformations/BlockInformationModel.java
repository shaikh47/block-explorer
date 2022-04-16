package com.blockchain.blockexplorer.Model.BlockInformations;

public class BlockInformationModel {

    public int getBlockNumber() {
        return blockNumber;
    }

    public int getTransationCount() {
        return transationCount;
    }

    public BlockInformationModel(int blockNumber, int transationCount) {
        this.blockNumber = blockNumber;
        this.transationCount = transationCount;
    }

    public BlockInformationModel() {
    }

    private int blockNumber;
    private int transationCount;
}
