package com.generator.writer.java;

import com.generator.Application;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class JavaApiUtilWriter {
	public void create() throws Exception {
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getUtilsPackagePath(), "ApiUtil.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Application.getSpringProperties().getPackageName() + ".utils;");
			file.writeln(0, "import org.springframework.data.domain.PageRequest;");
			file.writeln(0, "import org.springframework.data.domain.Pageable;");
			file.writeln(0, "import org.springframework.data.domain.Sort;");
			file.writeln(0, "import org.springframework.data.domain.Sort.Direction;");
			file.writeln(0, "import org.springframework.data.domain.Sort.Order;");
			file.writeln(0, "");
			file.writeln(0, "import lombok.NoArgsConstructor;");
			file.writeln(0, "import java.util.ArrayList;");
			file.writeln(0, "import java.util.List;");
			file.writeln(0, "");
			file.writeln(0, "@NoArgsConstructor");
			file.writeln(0, "public class ApiUtil {");
			file.writeln(1, "public static final String DEFAULT_PAGE = \"0\";");
			file.writeln(1, "public static final String DEFAULT_SIZE = \"50\";");
			file.writeln(1, "public static final int MIN_PAGE = 0;");
			file.writeln(1, "public static final int MIN_SIZE = 1;");
			file.writeln(1, "public static final int MAX_SIZE = 100;");
			file.writeln(0, "");
			file.writeln(1, "public static Pageable resolveSortingAndPagination(Integer page, Integer size, String[] sort) {");
			file.writeln(2, "List<Order> orders = new ArrayList<>();");
			file.writeln(2, "for (String sortParam : sort) {");
			file.writeln(3, "Direction sortDir = sortParam.startsWith(\"-\") ? Direction.DESC : Direction.ASC;");
			file.writeln(3, "sortParam = sortParam.startsWith(\"-\") || sortParam.startsWith(\"+\") ? sortParam.substring(1) : sortParam;");
			file.writeln(3, "orders.add(new Order(sortDir, sortParam));");
			file.writeln(2, "}");
			file.writeln(2, "return PageRequest.of(page, size, Sort.by(orders));");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}
}
