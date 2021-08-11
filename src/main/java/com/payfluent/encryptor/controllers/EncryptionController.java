package com.payfluent.encryptor.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import com.payfluent.encryptor.models.EncryptRequest;

import java.util.Base64;

@RestController
@RequestMapping("/api/v1/encrypt")
public class EncryptionController {

	@PostMapping("/")
	public String getCurrentTime(@RequestBody EncryptRequest encryptRequest) {

		// String publicKey =
		// "AJCjooa56bNHbMQxazmIz4qzAZq_g1bYHIFFmWH9zefLximNCihuLC4g03bkptxQACQkjiONcvGZNcPnWEYcz_WGoYKCrsIbw4IYp4vpu65sgpDyk2gFuouPUQhlpYbmmzeAHeWuPeu5xv_7_nF0YC6LvFRXsQmfdZI0fKBYnJs_5N8lRXHmyI4PT7-uetTX8Gi5q6-3xYnyVAmL6gVCC6-CPJK_1L3MmxRhd-j1k1XigMTMJJvX8fcfQHuwVgnVVrLwFOO5BVZbLmDjYlGfejAx8WHVerjrwxw8my_V7RKOsVDvWqfzHcCJWNJ23TMcVCUlstjv1QDnofVQdymFQQU";
		// String data = "sdpkwmfpwmefpwfwfpociwefpiwf";

		return encryptUsingPublicKey(encryptRequest);
	}

	public String encryptUsingPublicKey(EncryptRequest encryptRequest) {

		try {

			System.out.println(String.format("public key: %s", encryptRequest.getPublicKey()));
			System.out.println(String.format("data: %s", encryptRequest.getData()));

			return encryptUsingPublicKey(encryptRequest.getPublicKey(), encryptRequest.getData());

		} catch (Exception exception) {

			return String.format("Error Encrypting : %s", exception.getMessage());
		}
	}

	public static String encryptUsingPublicKey(String p_sEncodedPublicKey, String p_sData) throws Exception {
		if (p_sEncodedPublicKey != null && p_sEncodedPublicKey.length() != 0) {
			if (p_sData != null && p_sData.length() != 0) {
				byte[] abPublicKey;
				Base64.Decoder decoder = Base64.getDecoder();
				Base64.Encoder encoder = Base64.getEncoder();
				try {
					// abPublicKey = msf_decoderBase64.decode(p_sEncodedPublicKey);
					abPublicKey = decoder.decode(p_sEncodedPublicKey);
				} catch (Exception exception) {
					throw exception;
				}

				try {
					KeyFactory factoryKey = KeyFactory.getInstance("RSA");
					X509EncodedKeySpec specX509EncodedKey = new X509EncodedKeySpec(abPublicKey);
					PublicKey keyPublic = factoryKey.generatePublic(specX509EncodedKey);
					Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");
					OAEPParameterSpec paramSpec = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256,
							PSource.PSpecified.DEFAULT);
					cipher.init(1, keyPublic, paramSpec);
					byte[] abData = cipher.doFinal(p_sData.getBytes(StandardCharsets.UTF_8));
					// return msf_encoderBase64.encodeToString(abData);
					return encoder.encodeToString(abData);
				} catch (Exception exception) {
					// throw new Exception("Encrypt Using Public Key", var9);
					throw exception;
				}
			} else {
				throw new Exception("Data");
			}
		} else {
			throw new Exception("Public Key");
		}
	}
}