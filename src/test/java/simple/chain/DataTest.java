package simple.chain;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DataTest {

    @Test
    public void DataTest() {

        SimpleBlockchain<Transaction_creation> chain1 = new SimpleBlockchain<Transaction_creation>();
        SimpleBlockchain<Transaction_creation> chain2 = new SimpleBlockchain<Transaction_creation>();

        assertTrue(chain1.blockChainHash().equals(chain2.blockChainHash()));
    }
}
