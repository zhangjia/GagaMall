package io.zhangjia.mall.api.verificationCode;

import java.util.Random;

public class VerificationCode {
	static char[] codes = {'0','1','2','3','4','5','6','7','8','9'};
	

	public static String randomCode() {
		int length = 4;
		Random random = new Random();
		StringBuilder code = new StringBuilder();
		while(code.length() != length) {
			int nextInt = random.nextInt(codes.length);
			char c = codes[nextInt];
			if(code.indexOf(String.valueOf(c)) == -1) {
				code.append(c);
			}
		}
		return code.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(randomCode());
	}
}
