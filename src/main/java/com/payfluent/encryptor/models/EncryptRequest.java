package com.payfluent.encryptor.models;

public class EncryptRequest {
	
	private String publicKeyExponent;
	private String publicKeyModulus;
	private String data; 

	public EncryptRequest(){}

	public EncryptRequest(String publicKeyExponent, String publicKeyModulus, String data){
		this.publicKeyExponent = publicKeyExponent;
		this.publicKeyModulus = publicKeyModulus;
		this.data = data;
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

	public void setPublicKeyExponent(String exponent){

		this.publicKeyExponent = exponent;
	}
	public void setPublicKeyModulus(String modulus){

		this.publicKeyModulus = modulus;
	}

}
