package com.payfluent.encryptor.models;

public class EncryptRequest {
	
	private String publicKey;
	private String data; 

	public EncryptRequest(){}

	public EncryptRequest(String publicKey, String data){
		this.publicKey = publicKey;
		this.data = data;
	}

	public String getPublicKey(){
		return this.publicKey;
	}

	public String getData(){
		return this.data;
	}

	public void setPublicKey(String publicKey){
		this.publicKey = publicKey;
	}
	public void setData(String data){

		this.data = data;
	}
}
