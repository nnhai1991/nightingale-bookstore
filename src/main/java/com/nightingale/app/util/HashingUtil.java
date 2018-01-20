package com.nightingale.app.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

public class HashingUtil {
	public static String hashMD5Hex(String input){

		MessageDigest md;
		try {
			byte[] bytesOfMessage = input.getBytes("UTF-8");
			md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			return Hex.encodeHexString(thedigest);
		} catch (NoSuchAlgorithmException e) {
			//wont happen
		} catch (UnsupportedEncodingException e) {
			//wont happen
		}
		return "";

	}
}
