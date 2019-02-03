package simple.chain;

import helpers.SHA256;

import java.util.*;

public class Transaction_creation implements Tx {

    private String hash; //Pub_Key
    private byte[] value;
   private String type_transaction = "Creation";
   /*  private String name;
    private String descr;
    private Date begin;
    private Date end;
    private Date end_subscription;
    private Map<String, Date> date = new HashMap<String, Date>();
    private String location;
    private Integer min;
    private Integer max;
    private Map<String, Integer> limits = new HashMap<String, Integer>();*/
    private Map<String, Object> payload ;


    public Transaction_creation(byte[] value, Map<String, Object> payload) {
        this.hash = SHA256.generateHash(value.toString());
        this.setValue(value);
        this.payload = payload;
    }


    public String hash() { return hash; }

    /*public Transaction_creation(List<Object> payload) {
        this.setPayload(payload);
    }*/


    public byte[] getValue() {
        return value;
    }
    public void setValue(byte[] value) {

        // new value need to recalc hash
        this.hash = SHA256.generateHash(value.toString());
        this.value = value;
    }
    /*public Map<String, Object> setPayload(Map<String, Object>  payload) {
        payload.put("name",this.name);
        payload.put("descr",this.descr);
        this.date.put("begin",this.begin);
        this.date.put("end",this.end);
        this.date.put("end_subscription",this.end_subscription);
        payload.put("date",this.date);
        payload.put("location",this.location);
        this.limits.put("min",this.min);
        this.limits.put("max",this.max);
        payload.put("limits",this.limits);
        return payload;
    }*/

    public String toString() {
        return this.hash + " : "+this.getValue();
    }


}
