package simple.chain;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SimpleChainTest {
    /*
	public Map<String, Object> payload_A = new HashMap<String, Object>();
	public Map<String, Object> payload_B = new HashMap<String, Object>();


	@Test
	public void testBlockchain() {

		SimpleBlockchain<Transaction_creation> chain1 = new SimpleBlockchain<Transaction_creation>();

		payload_A.put("name","Name_Test_1");
		payload_A.put("descr","Descr_Test_1");
		payload_B.put("name","Name_Test_2");
		payload_B.put("descr","Descr_Test_2");
		chain1.add(new Transaction_creation("A",payload_A)).add(new Transaction_creation("B",payload_B));

		SimpleBlockchain<Transaction_creation> chain2 = chain1.Clone();

		//chain1.add(new Transaction_creation("D"));

		System.out.println(String.format("Chain 1 Hash: %s", chain1.getHead().getHash()));
		//System.out.println(String.format("Chain 2 Hash: %s", chain2.getHead().getHash()));
		System.out.println(
				String.format("Chains Are In Sync: %s", chain1.getHead().getHash().equals(chain2.getHead().getHash())));

		//chain2.add(new Transaction_creation("D"));

		System.out.println(String.format("Chain 1 Hash: %s", chain1.getHead().getHash()));
		//System.out.println(String.format("Chain 2 Hash: %s", chain2.getHead().getHash()));
		System.out.println(
				String.format("Chains Are In Sync: %s", chain1.getHead().getHash().equals(chain2.getHead().getHash())));

		//assertTrue(chain1.blockChainHash().equals(chain2.()));

		System.out.println("Current Chain Head Transactions: ");
		for (Block block : chain1.chain) {
			for (Object tx : block.getTransactions()) {
				System.out.println("\t" + tx);
			}
		}

		// Block Merkle root should equal root hash in Merkle Tree computed from
		// block transactions
		Block headBlock = chain1.getHead();
		List<Transaction_creation> merkleTree = headBlock.merkleTree();
		assertTrue(headBlock.getMerkleRoot().equals(merkleTree.get(merkleTree.size() - 1)));

		// Validate block chain
		assertTrue(chain1.validate());
		System.out.println(String.format("Chain is Valid: %s", chain1.validate()));

	}


	@Test
	public void merkleTreeTest() {
		payload_A.put("name","Name_Test_1");
		payload_A.put("descr","Descr_Test_1");
		payload_B.put("name","Name_Test_2");
		payload_B.put("descr","Descr_Test_2");
		// create chain, add transaction

		SimpleBlockchain<Transaction_creation> chain1 = new SimpleBlockchain<Transaction_creation>();

		chain1.add(new Transaction_creation("A",payload_A)).add(new Transaction_creation("B",payload_B));
		// get a block in chain
		Block<Transaction_creation> block = chain1.getHead();

		System.out.println("Merkle Hash tree :" + block.merkleTree());

		// get a transaction from block
		Transaction_creation tx = block.getTransactions().get(0);

		// see if block transactions are valid, they should be
		block.transasctionsValid();
		assertTrue(block.transasctionsValid());

		// mutate the data of a transaction
		tx.setValue("Z");

		// block should no longer be valid, blocks MerkleRoot does not match computed merkle tree of transactions
		assertFalse(block.transasctionsValid());

	}

	@Test
	public void blockMinerTest() {

		// create 30 transactions, that should result in 3 blocks in the chain.
		SimpleBlockchain<Transaction_creation> chain = new SimpleBlockchain<Transaction_creation>();

		// Respresents a proof of work miner
		// Creates
		Miner miner = new Miner(chain);

		Map[] payloads = new Map[30];
		for (int j = 0; j < 30; j++) {
			payload_A = new HashMap<String, Object>();
			payload_A.put("name","Name_Test_"+j);
			payload_A.put("descr","Descr_Test"+j);
			payloads[j] = payload_A;
		}
		// This represents transactions being created by a network
		for (int i = 0; i < 30; i++) {
			System.out.println("iiiiiiiiiiii " + i+" " +payloads[i]);
			miner.mine(new Transaction_creation("" + i, payloads[i]));
		}

		System.out.println("Number of Blocks Mined = " + chain.getChain().size());
		//assertTrue(chain.getChain().size() == 3);

	}


	@Test
	public void testValidateBlockchain() {
		payload_A.put("name","Name_Test_1");
		payload_A.put("descr","Descr_Test_1");
		payload_B.put("name","Name_Test_2");
		payload_B.put("descr","Descr_Test_2");
		SimpleBlockchain<Transaction_creation> chain = new SimpleBlockchain<Transaction_creation>();
		// add 30 transaction should result in 3 blocks in chain.
		for (int i = 0; i < 30 ; i++) {
	   	       chain.add(new Transaction_creation("tx:"+i,payload_A));
		}
		
		// is chain valid 
		System.out.println(String.format("Chain is Valid: %s", chain.validate()));

        // get second block from chain and add a tx..		
		Block<Transaction_creation> block = chain.getChain().get(1);
		Transaction_creation tx = new Transaction_creation("X",payload_A);
		block.add(tx);
		
		// is chain valid, should not be changed a block... 
		System.out.println(String.format("Chain is Valid: %s", chain.validate()));
	
	
	}
*/
}
