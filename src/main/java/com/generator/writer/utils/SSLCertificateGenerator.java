package com.generator.writer.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

public class SSLCertificateGenerator {
	// Method to generate and store self-signed certificate to a given path
	public static void generateSelfSignedCertificate(String targetAppPath, String alias, String password) throws Exception {
		Security.addProvider(new BouncyCastleProvider());

		// Generate a key pair (public and private key)
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// Create the certificate builder
		X500Name issuer = new X500Name("CN=MyApp, OU=MyOrgUnit, O=MyOrganization, L=MyCity, ST=MyState, C=MyCountry");
		BigInteger serial = BigInteger.valueOf(System.currentTimeMillis());
		Date startDate = new Date();
		Date endDate = new Date(startDate.getTime() + 3650L * 24 * 60 * 60 * 1000); // Valid for 10 years

		// Generate the certificate
		X509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(issuer, serial, startDate, endDate, issuer, keyPair.getPublic());

		// Sign the certificate
		ContentSigner signer = new JcaContentSignerBuilder("SHA256withRSA").build(keyPair.getPrivate());
		X509Certificate certificate = new JcaX509CertificateConverter().setProvider("BC").getCertificate(certBuilder.build(signer));

		// Create the target directory if it doesn't exist
		File targetDir = new File(targetAppPath + "/src/main/resources/");
		if (!targetDir.exists()) {
			targetDir.mkdirs(); // Create the directory if it doesn't exist
		}

		// Define the keystore path where the certificate will be saved
		String keystorePath = targetAppPath + "/src/main/resources/keystore.p12";

		// Store the certificate in a KeyStore
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(null, null);
		keyStore.setKeyEntry(alias, keyPair.getPrivate(), password.toCharArray(), new Certificate[] { certificate });

		// Save the keystore to the specified file
		try (FileOutputStream fos = new FileOutputStream(keystorePath)) {
			keyStore.store(fos, password.toCharArray());
		}

		System.out.println("Self-signed certificate generated and saved in " + keystorePath);
	}
}
