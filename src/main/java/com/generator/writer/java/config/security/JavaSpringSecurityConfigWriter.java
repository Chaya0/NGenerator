package com.generator.writer.java.config.security;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaSpringSecurityConfigWriter {
	public void create() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getConfigPackagePath() + "/security", "SpringSecurityConfig.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "package " + WriterUtils.getImportDefaultPackage() + ".config.security;");

			file.writeln(0, "");
			file.writeln(0, "import org.springframework.context.annotation.Bean;");
			file.writeln(0, "import org.springframework.context.annotation.Configuration;");
			file.writeln(0, "import org.springframework.security.authentication.AuthenticationManager;");
			file.writeln(0, "import org.springframework.security.config.Customizer;");
			file.writeln(0, "import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;");
			file.writeln(0, "import org.springframework.security.config.annotation.web.builders.HttpSecurity;");
			file.writeln(0, "import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;");
			file.writeln(0, "import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;");
			file.writeln(0, "import org.springframework.security.config.http.SessionCreationPolicy;");
			file.writeln(0, "import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;");
			file.writeln(0, "import org.springframework.security.crypto.password.PasswordEncoder;");
			file.writeln(0, "import org.springframework.security.web.SecurityFilterChain;");
			file.writeln(0, "import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;");
			file.writeln(0, "");
			file.writeln(0, "import " + WriterUtils.getImportDefaultPackage() + ".config.security.JwtFilter;");
			file.writeln(0, "import " + WriterUtils.getImportDefaultPackage() + ".model.User;");
			file.writeln(0, "");
			file.writeln(0, "@EnableWebSecurity");
			file.writeln(0, "@Configuration");
			file.writeln(0, "public class SpringSecurityConfig {");
			file.writeln(1, "private final UserService userService;");
			file.writeln(1, "private final JwtFilter jwtFilter;");
			file.writeln(1, "private static final String[] EXCLUDED_URLS = { \"/auth/**\", \"/v3/api-docs/**\", \"/swagger-ui.html\", \"/swagger-ui/**\" };");
			file.writeln(1, "");
			file.writeln(1, "public SpringSecurityConfig(UserService userService, JwtFilter jwtFilter) {");
			file.writeln(2, "this.jwtFilter = jwtFilter;");
			file.writeln(2, "this.userService = userService;");
			file.writeln(1, "}");
			file.writeln(1, "");
			file.writeln(1, "@Bean");
			file.writeln(1, "public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {");
			file.writeln(2, "return http");
			file.writeln(3, ".cors(Customizer.withDefaults())");
			file.writeln(3, ".csrf(AbstractHttpConfigurer::disable)");
			file.writeln(3, ".authorizeHttpRequests(req -> ");
			file.writeln(4, "req.requestMatchers(EXCLUDED_URLS)");
			file.writeln(4, ".permitAll()");
			file.writeln(4, ".anyRequest()");
			file.writeln(4, ".authenticated())");
			file.writeln(3, ".sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))");
			file.writeln(3, ".userDetailsService(userService)");
			file.writeln(3, ".httpBasic(Customizer.withDefaults())");
			file.writeln(3, ".addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)");
			file.writeln(3, ".build();");
			file.writeln(1, "}");
			file.writeln(1, "");
			file.writeln(1, "@Bean");
			file.writeln(1, "public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {");
			file.writeln(2, "return configuration.getAuthenticationManager();");
			file.writeln(1, "}");
			file.writeln(1, "");
			file.writeln(1, "@Bean");
			file.writeln(1, "public PasswordEncoder passwordEncoder() {");
			file.writeln(2, "return new BCryptPasswordEncoder();");
			file.writeln(1, "}");
			file.writeln(0, "}");

		}
	}
}
