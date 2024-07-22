package com.generator.writer.java.config.security;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaJwtUtilWriter {

	public void create() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getConfigPackagePath() + "/security", "JwtUtil.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "package " + WriterUtils.getImportDefaultPackage() + ".config.security;");
			file.writeln(0, "");
			file.writeln(0, "import java.util.Date;");
			file.writeln(0, "import java.util.HashMap;");
			file.writeln(0, "import java.util.Map;");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.beans.factory.annotation.Value;");
			file.writeln(0, "import org.springframework.security.core.userdetails.UserDetails;");
			file.writeln(0, "import org.springframework.stereotype.Component;");
			file.writeln(0, "");
			file.writeln(0, "import io.jsonwebtoken.Claims;");
			file.writeln(0, "import io.jsonwebtoken.Jwts;");
			file.writeln(0, "import io.jsonwebtoken.SignatureAlgorithm;");
			file.writeln(0, "");
			file.writeln(0, "@Component");
			file.writeln(0, "public class JwtUtil {");
			file.writeln(1, "");
			file.writeln(1, "@Value(\"${jwt.secret}\")");
			file.writeln(1, "private String secret = \"MY JWT SECRET\";");
			file.writeln(1, "");
			file.writeln(1, "public Claims extractAllClaims(String token) {");
			file.writeln(2, "return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();");
			file.writeln(1, "}");
			file.writeln(1, "");
			file.writeln(1, "public String extractUsername(String token) {");
			file.writeln(2, "return extractAllClaims(token).getSubject();");
			file.writeln(1, "}");
			file.writeln(1, "");
			file.writeln(1, "public boolean isTokenExpired(String token){");
			file.writeln(2, "return extractAllClaims(token).getExpiration().before(new Date());");
			file.writeln(1, "}");
			file.writeln(1, "");
			file.writeln(1, "public String generateToken(String username){");
			file.writeln(2, "Map<String, Object> claims = new HashMap<>();");
			file.writeln(2, "return Jwts.builder()");
			file.writeln(3, ".setClaims(claims)");
			file.writeln(3, ".setSubject(username)");
			file.writeln(3, ".setIssuedAt(new Date(System.currentTimeMillis()))");
			file.writeln(3, ".setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))");
			file.writeln(3, ".signWith(SignatureAlgorithm.HS512, secret).compact();");
			file.writeln(1, "}");
			file.writeln(1, "");
			file.writeln(1, "public boolean validateToken(String token, UserDetails user) {");
			file.writeln(2, "return (user.getUsername().equals(extractUsername(token)) && !isTokenExpired(token));");
			file.writeln(1, "}");
			file.writeln(0, "}");

		}
	}
}
