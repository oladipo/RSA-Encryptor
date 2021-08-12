package com.payfluent.encryptor.models;

public class EncryptRequest {
	
	private String publicKey;
	private String publicKeyExponent;
	private String publicKeyModulus;
	private String data; 

	public EncryptRequest(){}

	public EncryptRequest(String publicKey, String publicKeyExponent, String publicKeyModulus, String data){
		this.publicKey = publicKey;
		this.publicKeyExponent = publicKeyExponent;
		this.publicKeyModulus = publicKeyModulus;
		this.data = data;
	}

	public String getPublicKey(){
		return this.publicKey;
	}

	public String getPublicKeyExponent(){
		return this.publicKeyExponent;
	}

	public String getPublicKeyModulus(){
		return this.publicKeyModulus;
	}
	public String getData(){
		return this.data;
	}

	public void setPublicKey(String publicKey){
		this.publicKey = publicKey;
	}
	public void setPublicKeyExponent(String exponent){

		this.publicKeyExponent = exponent;
	}
	public void setPublicKeyModulus(String modulus){

		this.publicKeyModulus = modulus;
	}

}
