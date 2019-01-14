package simple.chain;

import helpers.SHA256;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Transaction_Creation implements Tx {
	
	private String hash; //Pub_Key
	private String value;
	private String type_transaction;
	private String name;
	private String descr;
	private Date begin;
	private Date end;
	private Date end_subscription;
	private List<Date> date;
	private String location;
	private Integer min;
	private Integer max;
	private List<Integer> limits;
	private List<Object> payload;


	public String hash() { return hash; }
	
	public Transaction_Creation(String value,List<Object> payload) {
		this.hash = SHA256.generateHash(value);
		this.setValue(value);
		this.setPayload(payload);
	}

	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		
		// new value need to recalc hash
		this.hash = SHA256.generateHash(value);	
		this.value = value;
	}
	public void setPayload(List<Object> payload) {
		this.payload.add(this.name);
		this.payload.add(this.descr);
		this.date.add(this.begin);
		this.date.add(this.end);
		this.date.add(this.end_subscription);
		this.payload.add(this.date);
		this.payload.add(this.location);
		this.limits.add(this.min);
		this.limits.add(this.max);
		this.payload.add(this.limits);
	}

	public String toString() { 
		return this.hash + " : "+this.getValue();		
	}
	

}
