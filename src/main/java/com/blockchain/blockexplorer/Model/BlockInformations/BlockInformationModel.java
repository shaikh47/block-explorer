package com.blockchain.blockexplorer.Model.BlockInformations;

public class BlockInformationModel implements Comparable<BlockInformationModel> {

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

    @Override
	public int compareTo(BlockInformationModel e) {
		return Integer.valueOf(this.getBlockNumber()).compareTo(Integer.valueOf(e.getBlockNumber()));
	}
}
