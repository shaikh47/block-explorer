package com.blockchain.blockexplorer;

import com.blockchain.blockexplorer.Config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlockexplorerApplication {

	public static void main(String[] args) {
		try{
			Config conf = new Config("src/main/resources/config.properties");
			conf.load();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(BlockexplorerApplication.class, args);
	}

}
