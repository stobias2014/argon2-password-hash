package com.tobias.saul;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Argon2PasswordHasher {

	public static void main(String[] args) {

		Argon2 argon2 = Argon2Factory.create();

		char[] password = "password".toCharArray();

		Instant start = Instant.now();

		try {
			String hashedPassword = argon2.hash(22, 65536, 1, password);
			
			System.out.println(hashedPassword);

			if (argon2.verify(hashedPassword, password)) {
				System.out.println("Match exists");
			}
		} finally {
			argon2.wipeArray(password);
		}

		Instant end = Instant.now();

		System.out.println(String.format("Hashing took %s ms", 
				ChronoUnit.MILLIS.between(start, end)));
	}

}
