package com.blockchain.blockexplorer.Controller;

import com.blockchain.blockexplorer.BlockInformationConsumer;
import com.blockchain.blockexplorer.Model.Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.web3j.protocol.core.methods.response.EthBlock.Block;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;
import reactor.core.publisher.Flux;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnOpen;
import javax.websocket.Session;

@RequestMapping("/api")
@RestController
public class APIController {

    @GetMapping("/getLatestBlockInformation")
    public Response getLatestBlockInformation() {
        return BlockInformationConsumer.getLatestBlockInformation();
    }

    @GetMapping("/getBulkBlockInformationWithCountFromLatest")
    public Response getBlockInformation(@RequestParam("countDownFromLatest") int countDownFromLatest) {
        return BlockInformationConsumer.getBlockInformation(countDownFromLatest);
    }

    @GetMapping("/getBulkBlockInformationWithLastUpdatedBlockNumber")
    public Response getLatestBlocksInformationWithLastUpdatedBlockNumber(@RequestParam("lastUpdatedBlockNumber") int lastUpdatedBlockNumber) {
        return BlockInformationConsumer.getLatestBlocksInformationWithLastUpdatedBlockNumber(lastUpdatedBlockNumber);
    }

    @GetMapping("/getPreviousBlocksMultiThread")
    public Response getPreviousBlocksMultiThread(@RequestParam("lowerBlock") int lowerBlock,
                                      @RequestParam("higherBlock") int higherBlock) {
        return BlockInformationConsumer.getPreviousBlocksMultiThread(lowerBlock, higherBlock);
    }

    @GetMapping("/getPreviousBlocks")
    public Response getPreviousBlocks(@RequestParam("lowerBlock") int lowerBlock,
                                      @RequestParam("higherBlock") int higherBlock) {
        return BlockInformationConsumer.getPreviousBlocks(lowerBlock, higherBlock);
    }

    @GetMapping("/getSingleBlockDetails")
    public Response getSingleBlockDetails(@RequestParam("blockNumber") String blockNumber) {
        return BlockInformationConsumer.getSingleBlockDetails(blockNumber);
    }
}
