package com.blockchain.blockexplorer.BlockInformation.GetBlockDetails;

import com.blockchain.blockexplorer.Model.BlockInformations.BlockInformationModel;
import com.blockchain.blockexplorer.Model.Response;
import com.blockchain.blockexplorer.Model.TransactionInformation.TransactionInformationModel;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;

import java.math.BigInteger;
import java.util.List;

public class FetchBlockDetailsImpl implements FetchBlockDetailsService {
    public Response getSingleBlockDetails(Web3j web3j, String blockNumber) {
        Response response = new Response();
        response.setStatus(false);
        try {
            EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(new BigInteger(blockNumber)), true).send().getBlock();

            ParseTransactionDetails trxParser = new ParseTransactionDetails();
            List<TransactionInformationModel> transactionInformationsOfBlock = trxParser.parseTransactionDetails(block.getTransactions());

            BlockInformationModel blockInformation = new BlockInformationModel(block.getNumber().intValue(),
                                                                                block.getTransactions().size(),
                                                                                transactionInformationsOfBlock,
                                                                                block.getBaseFeePerGas().toString(10),
                                                                                block.getDifficulty().toString(10),
                                                                                block.getExtraData(),
                                                                                block.getGasLimit().toString(10),
                                                                                block.getGasUsed().toString(10),
                                                                                block.getHash(),
                                                                                block.getMiner(),
                                                                                block.getLogsBloom(),
                                                                                block.getNonce().toString(10),
                                                                                block.getAuthor(),
                                                                                block.getParentHash(),
                                                                                block.getSize().toString(10),
                                                                                block.getTimestamp().toString(10),
                                                                                block.getTotalDifficulty().toString(10));


            Transaction trx = (Transaction) block.getTransactions().get(0).get();
            System.out.println(trx.getHash());
            System.out.println(trx.getFrom());
            System.out.println(trx.getTo());

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
