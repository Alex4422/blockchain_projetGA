package simple.chain;

import helpers.SHA256;

import java.util.List;

public class Transaction_inscription {
    private String hash; //Pub_Key
    private byte[] value;
    private List<Object> payload;

    public Transaction_inscription(String a) {
        this.hash = SHA256.generateHash(value.toString());
        this.payload.add(this.hash);
        this.setValue(value);
    }

    public String hash() { return hash; }

    public byte[] getValue() {
        return value;
    }
    public void setValue(byte[] value) {

        // new value need to recalc hash
        this.hash = SHA256.generateHash(value.toString());
        this.value = value;
    }

    public String toString() {
        return this.hash + " : "+this.getValue();
    }


}

