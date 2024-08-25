package com.generator.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generator.model.spring.SpringStartIOData;

public class SpringInitializrDependencies {

	public static void main(String[] args) {

		try {
			String springStartUrl = "https://start.spring.io/metadata/client";
			URL url = new URL(springStartUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
			System.out.println("Response Code: " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			String jsonResponse = response.toString();
//			System.out.println(jsonResponse);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			SpringStartIOData data = objectMapper.readValue(jsonResponse, SpringStartIOData.class);
			System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}