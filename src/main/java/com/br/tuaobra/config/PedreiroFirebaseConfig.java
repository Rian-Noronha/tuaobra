package com.br.tuaobra.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class PedreiroFirebaseConfig {

	private static final String SERVICE_ACCOUNT_JSON_PATH = "/home/rian/firebase-service-account-key.json";

	@Bean
	public FirebaseApp pedreiroFirebaseApp() throws IOException {

		if (FirebaseApp.getApps().isEmpty()) {
			GoogleCredentials credentials = GoogleCredentials
					.fromStream(new FileInputStream(SERVICE_ACCOUNT_JSON_PATH));
			FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).build();

			return FirebaseApp.initializeApp(options);
		} else {
			return FirebaseApp.getInstance();
		}

	}

}
