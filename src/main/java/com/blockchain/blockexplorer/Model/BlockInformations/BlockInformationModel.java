package com.blockchain.blockexplorer.Model.BlockInformations;

import com.blockchain.blockexplorer.Model.TransactionInformation.TransactionInformationModel;
import org.web3j.protocol.core.methods.response.EthBlock.TransactionResult;
import org.web3j.protocol.core.methods.response.Transaction;

import java.util.List;

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

    private String author; // probably miner

    public String getAuthor() {
        return author;
    }

    public List<TransactionInformationModel> getTransactions() {
        return transactions;
    }

    public String getBaseFeePerGas() {
        return baseFeePerGas;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getExtraData() {
        return extraData;
    }

    public String getGasLimit() {
        return gasLimit;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getMiner() {
        return miner;
    }

    public String getLogsBloom() {
        return logsBloom;
    }

    public String getNonce() {
        return nonce;
    }

    public String getParentHash() {
        return parentHash;
    }

    public String getSize() {
        return size;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTotalDifficulty() {
        return totalDifficulty;
    }

    private int blockNumber;
    private int transationCount;
    private List<TransactionInformationModel> transactions; // transaction hashes
    private String baseFeePerGas;
    private String difficulty;
    private String extraData;
    private String gasLimit;
    private String gasUsed;
    private String blockHash;
    private String miner;
    private String logsBloom;
    private String nonce;
    private String parentHash;
    private String size;
    private String timestamp;
    private String totalDifficulty;

    public BlockInformationModel(int blockNumber,
                                 int transationCount,
                                 List<TransactionInformationModel> transactions,
                                 String baseFeePerGas,
                                 String difficulty,
                                 String extraData,
                                 String gasLimit,
                                 String gasUsed,
                                 String blockHash,
                                 String miner,
                                 String logsBloom,
                                 String nonce,
                                 String author,
                                 String parentHash,
                                 String size,
                                 String timestamp,
                                 String totalDifficulty) {
        this.blockNumber = blockNumber;
        this.transationCount = transationCount;
        this.transactions = transactions;
        this.baseFeePerGas = baseFeePerGas;
        this.difficulty = difficulty;
        this.extraData = extraData;
        this.gasLimit = gasLimit;
        this.gasUsed = gasUsed;
        this.blockHash = blockHash;
        this.miner = miner;
        this.logsBloom = logsBloom;
        this.nonce = nonce;
        this.author = author;
        this.parentHash = parentHash;
        this.size = size;
        this.timestamp = timestamp;
        this.totalDifficulty = totalDifficulty;
    }

    @Override
    public int compareTo(BlockInformationModel e) {
        return Integer.valueOf(this.getBlockNumber()).compareTo(Integer.valueOf(e.getBlockNumber()));
    }
}
