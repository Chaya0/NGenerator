package com.generator.writer.java.config.security;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaJwtUtilWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaJwtUtilWriter javaJwtUtilWriter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		// Setup default package
		Properties properties = new Properties();
		properties.setProperty("importDefaultPackage", "com.myApp");

		// Mock static methods from WriterUtils
		try {
			when(WriterUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(mockGeneratorOutputFile);
		} catch (Exception e) {
			fail("Failed to mock WriterUtils.getOutputResource method");
		}
	}

	@Test
    public void testCreate() throws Exception {
        // Given
        when(mockGeneratorOutputFile.hasAlreadyExisted()).thenReturn(false);
        doNothing().when(mockGeneratorOutputFile).writeln(anyInt(), anyString());

        // When
        javaJwtUtilWriter.create();

        // Then
        verify(mockGeneratorOutputFile).writeln(0, "package com.myApp.config.security;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import java.util.Date;");
        verify(mockGeneratorOutputFile).writeln(0, "import java.util.HashMap;");
        verify(mockGeneratorOutputFile).writeln(0, "import java.util.Map;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.beans.factory.annotation.Value;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.security.core.userdetails.UserDetails;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.stereotype.Component;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import io.jsonwebtoken.Claims;");
        verify(mockGeneratorOutputFile).writeln(0, "import io.jsonwebtoken.Jwts;");
        verify(mockGeneratorOutputFile).writeln(0, "import io.jsonwebtoken.SignatureAlgorithm;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "@Component");
        verify(mockGeneratorOutputFile).writeln(0, "public class JwtUtil {");
        verify(mockGeneratorOutputFile).writeln(1, "");
        verify(mockGeneratorOutputFile).writeln(1, "@Value(\"${jwt.secret}\")");
        verify(mockGeneratorOutputFile).writeln(1, "private String secret = \"MY JWT SECRET\";");
        verify(mockGeneratorOutputFile).writeln(1, "");
        verify(mockGeneratorOutputFile).writeln(1, "public Claims extractAllClaims(String token) {");
        verify(mockGeneratorOutputFile).writeln(2, "return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(1, "");
        verify(mockGeneratorOutputFile).writeln(1, "public String extractUsername(String token) {");
        verify(mockGeneratorOutputFile).writeln(2, "return extractAllClaims(token).getSubject();");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(1, "");
        verify(mockGeneratorOutputFile).writeln(1, "public boolean isTokenExpired(String token){");
        verify(mockGeneratorOutputFile).writeln(2, "return extractAllClaims(token).getExpiration().before(new Date());");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(1, "");
        verify(mockGeneratorOutputFile).writeln(1, "public String generateToken(String username){");
        verify(mockGeneratorOutputFile).writeln(2, "Map<String, Object> claims = new HashMap<>();");
        verify(mockGeneratorOutputFile).writeln(2, "return Jwts.builder()");
        verify(mockGeneratorOutputFile).writeln(3, ".setClaims(claims)");
        verify(mockGeneratorOutputFile).writeln(3, ".setSubject(username)");
        verify(mockGeneratorOutputFile).writeln(3, ".setIssuedAt(new Date(System.currentTimeMillis()))");
        verify(mockGeneratorOutputFile).writeln(3, ".setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))");
        verify(mockGeneratorOutputFile).writeln(3, ".signWith(SignatureAlgorithm.HS512, secret).compact();");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(1, "");
        verify(mockGeneratorOutputFile).writeln(1, "public boolean validateToken(String token, UserDetails user) {");
        verify(mockGeneratorOutputFile).writeln(2, "return (user.getUsername().equals(extractUsername(token)) && !isTokenExpired(token));");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "}");

        verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
    }
}