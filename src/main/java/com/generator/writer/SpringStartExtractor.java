package com.generator.writer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.generator.Application;

public class SpringStartExtractor {
	public static void main(String[] args) {
		extractApp();
	}
	public static void extractApp() {
		String url = Application.getSpringProperties().getSpringAppDownloadUrl();
		String extractPath = Application.getSpringProperties().getProjectPath();

		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				// Create a temporary file to store the downloaded zip data
				File tempFile = File.createTempFile("temp", ".zip");
				try (FileOutputStream fos = new FileOutputStream(tempFile)) {
					byte[] buffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						fos.write(buffer, 0, bytesRead);
					}
				}

				// Extract the downloaded zip file
				try (ZipInputStream zipInput = new ZipInputStream(new FileInputStream(tempFile))) {
					byte[] buffer = new byte[1024];
					ZipEntry entry;

					while ((entry = zipInput.getNextEntry()) != null) {
						String filePath = extractPath + entry.getName();
						if (!entry.isDirectory()) {
							new File(filePath).getParentFile().mkdirs();
							try (FileOutputStream fos = new FileOutputStream(filePath)) {
								int len;
								while ((len = zipInput.read(buffer)) > 0) {
									fos.write(buffer, 0, len);
								}
							}
						}
						zipInput.closeEntry();
					}
				}

				// Delete the temporary zip file
				tempFile.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
