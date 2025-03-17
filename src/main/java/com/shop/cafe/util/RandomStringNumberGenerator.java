package com.shop.cafe.util;

import java.security.SecureRandom;

public class RandomStringNumberGenerator {
	public static String generateRandomStringNumber(int length) {
		SecureRandom random = new SecureRandom();
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < length; i++) {
			result.append(random.nextInt(10)); // 0 ~ 9
		}
		
		return result.toString();
	}

}
