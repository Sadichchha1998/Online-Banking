package com.onlinebanking.util;

public class AccountNumberCreater {
	 public static String generateRandomString(int length) {
	        StringBuilder sb = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            int digit = (int) (Math.random() * 10); // Generate random digit between 0 and 9
	            sb.append(digit);
	        }
	        return sb.toString();
	    }
}
