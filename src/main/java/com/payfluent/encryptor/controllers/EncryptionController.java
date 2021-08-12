package com.payfluent.encryptor.controllers;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.RSAPublicKeySpec;
// import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import com.payfluent.encryptor.models.EncryptRequest;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/encrypt")
public class EncryptionController {

	public String response;
	public HashMap<String, String> map;

	@PostMapping("/")
	public Map<String, String> create(@RequestBody EncryptRequest encryptRequest) {

		return encryptUsingPublicKey(encryptRequest);
	}

	public Map<String, String> encryptUsingPublicKey(EncryptRequest encryptRequest) {

		map = new HashMap<>();

		try {

			System.out.println(String.format("public key modulus: %s", encryptRequest.getPublicKeyModulus()));
			System.out.println(String.format("public key exponent: %s", encryptRequest.getPublicKeyExponent()));
			System.out.println(String.format("data: %s", encryptRequest.getData()));

			PublicKey publicKey = generatePublicKey(encryptRequest.getPublicKeyModulus(),
					encryptRequest.getPublicKeyExponent());

			response = encryptUsingPublicKey(encryptRequest.getData(), publicKey);

			map.put("code", "00");
			map.put("description", "success");
			map.put("data", response);

		} catch (Exception exception) {

			exception.printStackTrace();
			map.put("code", "30");
			map.put("description", String.format("error encrypting | %s", exception.getMessage()));
		}

		return map;
	}

	public PublicKey generatePublicKey(String publicModulus, String publicExponent) throws Exception {

		if (publicModulus != null && publicModulus.length() != 0) {

			byte[] modBytes = publicModulus.getBytes();
			byte[] expBytes = publicExponent.getBytes();

			BigInteger modulus = new BigInteger(1, modBytes);
			BigInteger exponent = new BigInteger(1, expBytes);

			Security.addProvider(new BouncyCastleProvider());
			RSAPublicKeySpec publicKeyspec = new RSAPublicKeySpec(modulus, exponent);

			try {
				KeyFactory factory = KeyFactory.getInstance("RSA"); // , "JHBCI");
				PublicKey publicKey = factory.generatePublic(publicKeyspec);

				return publicKey;
			}

			catch (Exception Ex) {
				System.out.println(String.format("error: %s", Ex.getMessage()));
				throw Ex;
			}
		} else {

			throw new Exception("Key modulus Cannot be empty");
		}
	}

	public String encryptUsingPublicKey(String p_sData, PublicKey keyPublic) throws Exception {

		if (p_sData != null && p_sData.length() != 0) {
			Base64.Encoder encoder = Base64.getEncoder();

			try {

				Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");
				OAEPParameterSpec paramSpec = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256,
						PSource.PSpecified.DEFAULT);
				cipher.init(1, keyPublic, paramSpec);
				byte[] abData = cipher.doFinal(p_sData.getBytes(StandardCharsets.UTF_8));

				return encoder.encodeToString(abData);
			} catch (Exception exception) {
				System.out.println(String.format("error: %s", exception.getMessage()));
				throw exception;
			}
		} else {
			throw new Exception("Data cannot be empty");
		}
	}
}
