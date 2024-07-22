package com.generator.writer.java.components.generic;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaGenericRepositoryWriter implements DefaultWriter {

	@Override
	public void create(AppModel model) throws Exception {
		create();
	}
	
	/**
	 * 	Creates a generic repository that doesn't require object type.
	 * @throws Exception
	 */
	public void create() throws Exception {
		
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getRepositoryPackagePath(false), "GenericRepository.java", true)) {
			file.writeln(0, "package " + WriterUtils.getImportRepositoryPackageName(false) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.data.jpa.domain.Specification;");
			file.writeln(0, "import org.springframework.data.jpa.repository.JpaRepository;");
			file.writeln(0, "import org.springframework.data.repository.NoRepositoryBean;");
			file.writeln(0, "import org.springframework.data.domain.Pageable;");
			file.writeln(0, "import org.springframework.data.domain.Page;");
			file.writeln(0, "import java.util.List;");
			file.writeln(0, "");
			file.writeln(0, "@NoRepositoryBean");
			file.writeln(0, "public interface GenericRepository<T> extends JpaRepository<T, Long> {");
			file.writeln(0, "");
			file.writeln(1, "Page<T> findAll(Specification<T> spec, Pageable pageSort);");
			file.writeln(1, "List<T> findAll(Specification<T> spec);");
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}

	@Override
	public void create(Entity entity) throws Exception {
	}
}
