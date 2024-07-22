package com.generator.writer.java.config.security;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaJwtFilterWriter {

	public void create() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getConfigPackagePath() + "/security", "JwtFilter.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "package " + WriterUtils.getImportDefaultPackage() + ".config.security;");
			file.writeln(0, "");
			file.writeln(0, "import java.io.IOException;");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;");
			file.writeln(0, "import org.springframework.security.core.context.SecurityContextHolder;");
			file.writeln(0, "import org.springframework.security.core.userdetails.UserDetails;");
			file.writeln(0, "import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;");
			file.writeln(0, "import org.springframework.stereotype.Component;");
			file.writeln(0, "import org.springframework.web.filter.OncePerRequestFilter;");
			file.writeln(0, "");
			file.writeln(0, "import " + WriterUtils.getImportDefaultPackage() + ".service.UserService;");
			file.writeln(0, "import " + WriterUtils.getImportDefaultPackage() + ".config.security.JwtUtil;");
			file.writeln(0, "");
			file.writeln(0, "import jakarta.servlet.FilterChain;");
			file.writeln(0, "import jakarta.servlet.ServletException;");
			file.writeln(0, "import jakarta.servlet.http.HttpServletRequest;");
			file.writeln(0, "import jakarta.servlet.http.HttpServletResponse;");
			file.writeln(0, "");
			file.writeln(0, "@Component");
			file.writeln(0, "public class JwtFilter extends OncePerRequestFilter {");
			file.writeln(1, "private final UserService userService;");
			file.writeln(1, "private final JwtUtil jwtUtil;");
			file.writeln(1, "");
			file.writeln(1, "public JwtFilter(UserService userService, JwtUtil jwtUtil) {");
			file.writeln(2, "this.userService = userService;");
			file.writeln(2, "this.jwtUtil = jwtUtil;");
			file.writeln(1, "}");
			file.writeln(1, "");
			file.writeln(1, "@Override");
			file.writeln(1,
					"protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {");
			file.writeln(2, "String authHeader = httpServletRequest.getHeader(\"Authorization\");");
			file.writeln(2, "String jwt = null;");
			file.writeln(2, "String username = null;");
			file.writeln(2, "");
			file.writeln(2, "if(authHeader != null && authHeader.startsWith(\"Bearer \")) {");
			file.writeln(3, "jwt = authHeader.substring(7);");
			file.writeln(3, "username = jwtUtil.extractUsername(jwt);");
			file.writeln(2, "}");
			file.writeln(2, "");
			file.writeln(2, "if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {");
			file.writeln(3, "UserDetails userDetails = this.userService.loadUserByUsername(username);;");
			file.writeln(3, "");
			file.writeln(3, "if (jwtUtil.validateToken(jwt, userDetails)) {");
			file.writeln(4, "UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(");
			file.writeln(5, "userDetails, null, userDetails.getAuthorities());");
			file.writeln(4, "usernamePasswordAuthenticationToken");
			file.writeln(5, ".setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));");
			file.writeln(4, "SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);");
			file.writeln(3, "}");
			file.writeln(2, "}");
			file.writeln(2, "filterChain.doFilter(httpServletRequest, httpServletResponse);");
			file.writeln(1, "}");
			file.writeln(0, "}");
		}
	}

}
