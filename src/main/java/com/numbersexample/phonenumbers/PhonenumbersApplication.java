package com.numbersexample.phonenumbers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhonenumbersApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(PhonenumbersApplication.class, args);
		}
		catch (Throwable throwable) {
			System.out.println(throwable.toString());
			throwable.printStackTrace();
		}
	}

}
