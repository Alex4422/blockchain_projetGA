package simple.chain;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import helpers.SHA256;

/**
 * 
 * Simulates a Miner that performs a Proof of Work consensus mechanism
 * transactions created in the network.
 * 
 * This is used for Unit testing, in a real blockchain network, 
 * miners would listen for transactions nodes created and then perform
 * POW and create a block, which would be sent back to network, and validated 
 * by each node.
 * 
 */
public class Miner<T extends Tx> {

	List<T> transactionPool = new ArrayList<T>();
	SimpleBlockchain chain = null;

	public Miner(SimpleBlockchain chain) {
		this.chain = chain;
	}

	public void mine(T tx) {
		transactionPool.add(tx);
		if (transactionPool.size() > SimpleBlockchain.BLOCK_SIZE) {
			createBlockAndApplyToChain();
		}
	}

	private void createBlockAndApplyToChain() {

		Block block = chain.newBlock();
		// set previous hash with current hash
		block.setPreviousHash(chain.getHead().getHash());
		// set block hashes from POW
		// block
		block.setHash(proofOfWork(block));
		chain.addAndValidateBlock(block);
		// empty pool
		transactionPool = new ArrayList<T>();
	}

	private String proofOfWork(Block block) {

		String nonceKey = block.getNonce();
		long nonce = 0;
		boolean nonceFound = false;
		String nonceHash = "";

		Gson parser = new Gson();
		String serializedData = parser.toJson(transactionPool);
		System.out.println("========= " + serializedData);

		try(FileWriter file = new FileWriter("jsonfile.txt")){
			file.write(serializedData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
		String message = block.getTimeStamp() + block.getIndex() + block.getMerkleRoot() + serializedData
				+ block.getPreviousHash();

		System.out.println("====message===== " + message);


		while (!nonceFound) {

			nonceHash = (String) SHA256.generateHash(String.format("%s%d", message, nonce));
			nonceFound = nonceHash.substring(0, nonceKey.length()).equals(nonceKey);
			nonce++;

		}

		return nonceHash;

	}

}
