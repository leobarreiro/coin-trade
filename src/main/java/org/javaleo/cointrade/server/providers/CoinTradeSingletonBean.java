package org.javaleo.cointrade.server.providers;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

@Component
public class CoinTradeSingletonBean {

	private static final String SHA_512 = "SHA-512";
	private static final String HMAC_SHA512 = "HmacSHA512";

	public boolean amIAlive() {
		return true;
	}

	public String macToolsEncrypt(final String secretKey, final String rawMessage) throws DecoderException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] decodedKey = Hex.decodeHex(secretKey.toCharArray());
		SecretKeySpec keySpec = new SecretKeySpec(decodedKey, HMAC_SHA512);
		Mac mac = Mac.getInstance(HMAC_SHA512);
		mac.init(keySpec);
		byte[] dataBytes = rawMessage.getBytes("UTF-8");
		byte[] signatureBytes = mac.doFinal(dataBytes);
		String signature = new String(Base64.encodeBase64(signatureBytes), "UTF-8");
		return signature;
	}

	public boolean verifySignature(final String secretKey, final String signature, final String rawMessage) {
		String reSignature;
		try {
			reSignature = macToolsEncrypt(secretKey, rawMessage);
			return reSignature.equals(signature);
		} catch (Exception e) {
			return false;
		}
	}

	public String generateHashToUser(final String passwd) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(SHA_512);
		byte[] digest = md.digest(passwd.getBytes());
		return Hex.encodeHexString(digest);
	}

}
