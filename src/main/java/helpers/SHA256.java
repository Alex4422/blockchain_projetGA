package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

	public static String toHex(byte[] theHash) {
		StringBuilder hexString = new StringBuilder();
		for(byte aTheHash:theHash){
			String hex = Integer.toHexString(0xff&aTheHash);
			if(hex.length()==1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public static String generateHash(String value) {
		String hash = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] bytes = md.digest(value.getBytes());

			//byte[] bytes = md.digest(value);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			hash = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return toHex(hash.getBytes());
	}

}
