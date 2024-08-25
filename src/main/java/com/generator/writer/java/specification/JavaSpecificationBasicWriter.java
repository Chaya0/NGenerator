package com.generator.writer.java.specification;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaSpecificationBasicWriter {
	public void create() throws Exception {
        try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getSpecificationPackagePath(), "GenericSpecification.java", false)) {
            if (file.hasAlreadyExisted()) {
                return;
            }
            file.writeln(0, "package " + WriterUtils.getSpecificationPackageImportPath() + ";");
            file.writeln(0, "");
            file.writeln(0, "import jakarta.persistence.criteria.Root;");
            file.writeln(0, "import lombok.Getter;");
            file.writeln(0, "import org.springframework.data.jpa.domain.Specification;");
            file.writeln(0, "");
            file.writeln(0, "@SuppressWarnings(\"unused\")");
            file.writeln(0, "@Getter");
            file.writeln(0, "public class SpecificationBasic<T> {");
            file.writeln(1, "private Specification<T> specification;");
            file.writeln(0, "");
            file.writeln(1, "public static <T> SpecificationBasic<T> get() {");
            file.writeln(2, "return new SpecificationBasic<>(new Filter(\"\", SearchOperator.EMPTY_FILTER, null));");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public static <T> SpecificationBasic<T> where(String key, SearchOperator operation, Object value) {");
            file.writeln(2, "return new SpecificationBasic<>(new Filter(key, operation, value));");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public static <T> SpecificationBasic<T> where(String key, Object value) {");
            file.writeln(2, "return new SpecificationBasic<>(new Filter(key, value));");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "protected SpecificationBasic(Filter filter) {");
            file.writeln(2, "this.specification = Specification.where(new GenericSpecification<>(filter));");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public SpecificationBasic<T> and(String key, Object value) {");
            file.writeln(2, "specification = specification.and(new GenericSpecification<>(new Filter(key, value)));");
            file.writeln(2, "return this;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public SpecificationBasic<T> and(Specification<T> specification) {");
            file.writeln(2, "this.specification = specification.and(this.specification);");
            file.writeln(2, "return this;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public SpecificationBasic<T> and(String key, SearchOperator operation, Object value) {");
            file.writeln(2, "specification = specification.and(new GenericSpecification<>(new Filter(key, operation, value)));");
            file.writeln(2, "return this;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public SpecificationBasic<T> and(SpecificationBasic<T> spec) {");
            file.writeln(2, "specification = specification.and(spec.getSpecification());");
            file.writeln(2, "return this;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public SpecificationBasic<T> or(String key, Object value) {");
            file.writeln(2, "specification = specification.or(new GenericSpecification<>(new Filter(key, value)));");
            file.writeln(2, "return this;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public SpecificationBasic<T> or(String key, SearchOperator operation, Object value) {");
            file.writeln(2, "specification = specification.or(new GenericSpecification<>(new Filter(key, operation, value)));");
            file.writeln(2, "return this;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public SpecificationBasic<T> or(SpecificationBasic<T> spec) {");
            file.writeln(2, "specification = specification.or(spec.getSpecification());");
            file.writeln(2, "return this;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public SpecificationBasic<T> joinAnd(String joinEntity, String key, SearchOperator operation, Object value) {");
            file.writeln(2, "specification = specification.and(new GenericSpecification<>(new Filter(joinEntity, key, operation, value)));");
            file.writeln(2, "return this;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public <X> SpecificationBasic<T> joinAnd(String joinEntity, SpecificationBasic<X> joinSpec) {");
            file.writeln(2, "Specification<T> innerJoinSpec = getJoinedSpecification(joinEntity, joinSpec);");
            file.writeln(2, "specification = specification.and(innerJoinSpec);");
            file.writeln(2, "return this;");
            file.writeln(1, "}");
            file.writeln(1, "");
            file.writeln(1, "public <X> SpecificationBasic<T> joinOr(String joinEntity, SpecificationBasic<X> joinSpec) {");
            file.writeln(2, "Specification<T> innerJoinSpec = getJoinedSpecification(joinEntity, joinSpec);");
            file.writeln(2, "specification = specification.or(innerJoinSpec);");
            file.writeln(2, "return this;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public SpecificationBasic<T> joinAnd(String joinEntity, String key, Object value) {");
            file.writeln(2, "specification = specification.and(new GenericSpecification<>(new Filter(joinEntity, key, SearchOperator.EQUAL, value)));");
            file.writeln(2, "return this;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "public SpecificationBasic<T> joinOr(String joinEntity, String key, SearchOperator operation, Object value) {");
            file.writeln(2, "specification = specification.or(new GenericSpecification<>(new Filter(joinEntity, key, operation, value)));");
            file.writeln(2, "return this;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "private static <T, X> Specification<T> getJoinedSpecification(String joinEntity, SpecificationBasic<X> joinSpec) {");
            file.writeln(2, "return (root, query, criteriaBuilder) -> {");
            file.writeln(3, "Root<X> rootX = new JoinRoot<>(root.join(joinEntity));");
            file.writeln(3, "return joinSpec.getSpecification().toPredicate(rootX, query, criteriaBuilder);");
            file.writeln(2, "};");
            file.writeln(1, "}");
            file.writeln(0, "}");

        }
 }
}
