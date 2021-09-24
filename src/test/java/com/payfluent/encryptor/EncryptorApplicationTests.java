package com.payfluent.encryptor;

import com.payfluent.encryptor.controllers.EncryptionController;
import com.payfluent.encryptor.models.EncryptRequest;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;
@SpringBootTest
class EncryptorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void encryptUsingPublicKey_Is_Valid(){

		EncryptionController controller = new EncryptionController();
		
		String base64UrlString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkKOihrnps0dsxDFrOYjPirMBmr-DVtgcgUWZYf3N58vGKY0KKG4sLiDTduSm3FAAJCSOI41y8Zk1w-dYRhzP9YahgoKuwhvDghini-m7rmyCkPKTaAW6i49RCGWlhuabN4Ad5a4967nG__v-cXRgLou8VFexCZ91kjR8oFicmz_k3yVFcebIjg9Pv6561NfwaLmrr7fFifJUCYvqBUILr4I8kr_UvcybFGF36PWTVeKAxMwkm9fx9x9Ae7BWCdVWsvAU47kFVlsuYONiUZ96MDHxYdV6uOvDHDybL9XtEo6xUO9ap_MdwIlY0nbdMxxUJSWy2O_VAOeh9VB3KYVBBQIDAQAB";
		String data = "12345678901234|202012|123";

		String expected = "QmVheUdaRVVjKzQxeno3a085UWZxSXNua3RtcjF1RUNndWJ6ZE4rY0tQMktvbTg4UGNMc21MOVhPbVJWSDRpaytJTVlBWHgwUTFaRXRKRENVMTdUcUxuZjE0L1VpYTM2a0o5b3ZwSks1ZDBHVnVUNnV0bzZ0U05TL2pMbUVVTlN0TjZqaXVJSTZOMndWS29XWUJ1ZEU0N0hBVEJydUQzMDloQ1UvWHJGOHlwNmcyeHBxRHhzMXo4WE9Db1cySHNrUlNuUkkraVZ1S29EUXpiZjBZSlZSY29sYVJqRnBhNGJUVkxENGd2ZXZJU0tnVTdDSEJudVF4OFBEVmRmSzRNb2o1NEJFSVlpUmk3WUtBSk5KSU9vR0tjNUNiTEJydFQrMmszT2gyNmVCUWQ5TzFadUJkdWdVdHZDU3lqMDNRYVZmdHNwL1djdDlVeGlkZUR2TFBqRTRRPT0=";

		EncryptRequest encryptRequest = new EncryptRequest(base64UrlString, data);

		Map<String, String> actual = controller.encryptUsingPublicKey(encryptRequest);

		System.out.println(actual.get("data"));

		assertThat(actual).isNotNull();
		// assertThat(actual.get("data")).isEqualTo(expected);
	}

	@Test
	void generatePublicKey_Returns_Public_Key() throws Exception{

		EncryptionController controller = new EncryptionController();
		String publicKeyExponent = "AQAB";
		String publicKeyModulus = "AJCjooa56bNHbMQxazmIz4qzAZq_g1bYHIFFmWH9zefLximNCihuLC4g03bkptxQACQkjiONcvGZNcPnWEYcz_WGoYKCrsIbw4IYp4vpu65sgpDyk2gFuouPUQhlpYbmmzeAHeWuPeu5xv_7_nF0YC6LvFRXsQmfdZI0fKBYnJs_5N8lRXHmyI4PT7-uetTX8Gi5q6-3xYnyVAmL6gVCC6-CPJK_1L3MmxRhd-j1k1XigMTMJJvX8fcfQHuwVgnVVrLwFOO5BVZbLmDjYlGfejAx8WHVerjrwxw8my_V7RKOsVDvWqfzHcCJWNJ23TMcVCUlstjv1QDnofVQdymFQQU";

		PublicKey publicKey = controller.generatePublicKey(publicKeyModulus, publicKeyExponent);

		assertThat(publicKey).isNotNull();
		assertThat(publicKey).isInstanceOf(PublicKey.class);
		
		StringWriter writer = new StringWriter();
		PemWriter pemWriter = new PemWriter(writer);

		pemWriter.writeObject(new PemObject("PUBLIC KEY", publicKey.getEncoded()));
		pemWriter.flush();
		pemWriter.close();

		String strPemData = writer.toString();

		System.out.println(strPemData);

		assertThat(strPemData).isNotNull();
	}

	@Test 
	void base64URLDecode_Returns_Correct_Decoded_String() throws Exception{

		EncryptionController controller = new EncryptionController();
		String base64UrlString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkKOihrnps0dsxDFrOYjPirMBmr-DVtgcgUWZYf3N58vGKY0KKG4sLiDTduSm3FAAJCSOI41y8Zk1w-dYRhzP9YahgoKuwhvDghini-m7rmyCkPKTaAW6i49RCGWlhuabN4Ad5a4967nG__v-cXRgLou8VFexCZ91kjR8oFicmz_k3yVFcebIjg9Pv6561NfwaLmrr7fFifJUCYvqBUILr4I8kr_UvcybFGF36PWTVeKAxMwkm9fx9x9Ae7BWCdVWsvAU47kFVlsuYONiUZ96MDHxYdV6uOvDHDybL9XtEo6xUO9ap_MdwIlY0nbdMxxUJSWy2O_VAOeh9VB3KYVBBQIDAQAB";
		String expected = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkKOihrnps0dsxDFrOYjPirMBmr+DVtgcgUWZYf3N58vGKY0KKG4sLiDTduSm3FAAJCSOI41y8Zk1w+dYRhzP9YahgoKuwhvDghini+m7rmyCkPKTaAW6i49RCGWlhuabN4Ad5a4967nG//v+cXRgLou8VFexCZ91kjR8oFicmz/k3yVFcebIjg9Pv6561NfwaLmrr7fFifJUCYvqBUILr4I8kr/UvcybFGF36PWTVeKAxMwkm9fx9x9Ae7BWCdVWsvAU47kFVlsuYONiUZ96MDHxYdV6uOvDHDybL9XtEo6xUO9ap/MdwIlY0nbdMxxUJSWy2O/VAOeh9VB3KYVBBQIDAQAB";

		String actual = controller.base64UrlDecode(base64UrlString);

		assertThat(actual.length()).isEqualTo(expected.length());
		assertThat(actual).isEqualTo(expected);
	}

	@Test 
	void generatePublicKeyFromBase64_returns_public_key() throws Exception{

		EncryptionController controller = new EncryptionController();

		String base64String = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkKOihrnps0dsxDFrOYjPirMBmr+DVtgcgUWZYf3N58vGKY0KKG4sLiDTduSm3FAAJCSOI41y8Zk1w+dYRhzP9YahgoKuwhvDghini+m7rmyCkPKTaAW6i49RCGWlhuabN4Ad5a4967nG//v+cXRgLou8VFexCZ91kjR8oFicmz/k3yVFcebIjg9Pv6561NfwaLmrr7fFifJUCYvqBUILr4I8kr/UvcybFGF36PWTVeKAxMwkm9fx9x9Ae7BWCdVWsvAU47kFVlsuYONiUZ96MDHxYdV6uOvDHDybL9XtEo6xUO9ap/MdwIlY0nbdMxxUJSWy2O/VAOeh9VB3KYVBBQIDAQAB";
		
		PublicKey publicKey = controller.generatePublicKeyFromBase64(base64String);

		assertThat(publicKey).isNotNull();

		assertThat(publicKey).isInstanceOf(PublicKey.class);
		
		StringWriter writer = new StringWriter();
		PemWriter pemWriter = new PemWriter(writer);

		pemWriter.writeObject(new PemObject("PUBLIC KEY", publicKey.getEncoded()));
		pemWriter.flush();
		pemWriter.close();

		String strPemData = writer.toString();

		System.out.println(strPemData);
	}

}
