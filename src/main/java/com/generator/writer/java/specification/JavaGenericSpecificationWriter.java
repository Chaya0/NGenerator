package com.generator.writer.java.specification;

import com.generator.annotations.CustomComponent;
import com.generator.annotations.WriterVersion;
import com.generator.model.enums.ComponentType;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;


@CustomComponent(ComponentType.REPOSITORY)
@WriterVersion("1.0")
public class JavaGenericSpecificationWriter {
    public void create() throws Exception {
        try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getSpecificationPackagePath(), "GenericSpecification.java", false)) {
            if (file.hasAlreadyExisted()) {
                return;
            }

            file.writeln(0, "package " + WriterUtils.getSpecificationPackageImportPath() + ";");
            file.writeln(0, "import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;");
            file.writeln(0, "import com.mds.merv.config.MapperConfig;");
            file.writeln(0, "import com.mds.merv.utils.LocalDateTimeParser;");
            file.writeln(0, "import jakarta.persistence.criteria.*;");
            file.writeln(0, "import org.springframework.data.jpa.domain.Specification;");
            file.writeln(0, "import org.springframework.lang.NonNull;");
            file.writeln(0, "");
            file.writeln(0, "import java.util.ArrayList;");
            file.writeln(0, "import java.util.Collection;");
            file.writeln(0, "import java.util.List;");
            file.writeln(0, "import java.util.Map;");
            file.writeln(0, "");
            file.writeln(0, "public class GenericSpecification<T> implements Specification<T> {");
            file.writeln(1, "private static final long serialVersionUID = 1L;");
            file.writeln(1, "private final transient Filter filter;");
            file.writeln(0, "");
            file.writeln(1, "public GenericSpecification(Filter filter) {");
            file.writeln(2, "this.filter = filter;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "@Override");
            file.writeln(1, "public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {");
            file.writeln(2, "if (filter.getJoinEntity() == null && filter.getOperator() != SearchOperator.BETWEEN && (filter.getOperator() == SearchOperator.IN || filter.getValue() instanceof Collection<?>)) {");
            file.writeln(3, "return handleCollectionValue(root, builder);");
            file.writeln(2, "}");
            file.writeln(2, "if (filter.getJoinEntity() != null) {");
            file.writeln(3, "return handleJoinValue(root, builder);");
            file.writeln(2, "}");
            file.writeln(2, "try {");
            file.writeln(3, "return handleSingleValue(root, builder);");
            file.writeln(2, "} catch (ClassCastException e) {");
            file.writeln(3, "return handleFKStructure(root, builder);");
            file.writeln(2, "}");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "@SuppressWarnings({\"rawtypes\"})");
            file.writeln(1, "private Predicate handleSingleValue(Root<T> root, CriteriaBuilder builder) {");
            file.writeln(2, "Comparable value = (Comparable) filter.getValue();");
            file.writeln(2, "return buildPredicate(root, value, builder, filter.getKey());");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "@SuppressWarnings({\"unchecked\", \"rawtypes\"})");
            file.writeln(1, "private Predicate handleFKStructure(Root<T> root, CriteriaBuilder builder) {");
            file.writeln(2, "if (filter.getOperator() == SearchOperator.BETWEEN) {");
            file.writeln(3, "return buildBetweenPredicate(root, filter.getValue(), builder, filter.getKey());");
            file.writeln(2, "}");
            file.writeln(2, "Map<String, Object> map = MapperConfig.getObjectMapper().registerModule(new JavaTimeModule()).convertValue(filter.getValue(), Map.class);");
            file.writeln(2, "cleanMap(map);");
            file.writeln(2, "if (map.containsKey(\"id\") && map.get(\"id\") != null) {");
            file.writeln(3, "return buildPredicate(root.get(filter.getKey()), (Comparable) map.get(\"id\"), builder, \"id\");");
            file.writeln(2, "}");
            file.writeln(2, "Join<?, T> join = root.join(filter.getKey());");
            file.writeln(2, "List<Predicate> predicates = new ArrayList<>(List.of(builder.isTrue(builder.literal(true))));");
            file.writeln(2, "addPredicatesFromMap(builder, map, predicates, join);");
            file.writeln(2, "return builder.and(predicates.toArray(new Predicate[0]));");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "@SuppressWarnings({\"unchecked\", \"rawtypes\"})");
            file.writeln(1, "private void addPredicatesFromMap(CriteriaBuilder builder, Map<String, Object> map, List<Predicate> predicates, Join<?, ?> join) {");
            file.writeln(2, "for (Map.Entry<String, Object> entry : map.entrySet()) {");
            file.writeln(3, "if (entry.getValue() == null) continue;");
            file.writeln(3, "if (entry.getValue() instanceof Map) {");
            file.writeln(4, "predicates.add(handleNestedMap(join.join(entry.getKey()), (Map<String, Object>) entry.getValue(), builder));");
            file.writeln(3, "} else {");
            file.writeln(4, "predicates.add(buildPredicate(join, (Comparable) entry.getValue(), builder, entry.getKey()));");
            file.writeln(3, "}");
            file.writeln(2, "}");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "private Predicate handleNestedMap(Join<?, ?> join, Map<String, Object> map, CriteriaBuilder builder) {");
            file.writeln(2, "List<Predicate> predicates = new ArrayList<>();");
            file.writeln(2, "addPredicatesFromMap(builder, map, predicates, join);");
            file.writeln(2, "return builder.and(predicates.toArray(new Predicate[0]));");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "@SuppressWarnings({\"rawtypes\"})");
            file.writeln(1, "private Predicate handleJoinValue(Root<T> root, CriteriaBuilder builder) {");
            file.writeln(2, "Join<?, ?> join = root.join(filter.getJoinEntity());");
            file.writeln(2, "if (filter.getOperator() == SearchOperator.BETWEEN) {");
            file.writeln(3, "return buildBetweenPredicate(join, filter.getValue(), builder, filter.getKey());");
            file.writeln(2, "}");
            file.writeln(2, "if (filter.getOperator() == SearchOperator.IN) {");
            file.writeln(3, "Root<T> joinRoot = new JoinRoot<>(root.join(filter.getJoinEntity()));");
            file.writeln(3, "return handleCollectionValue(joinRoot, builder);");
            file.writeln(2, "}");
            file.writeln(2, "return buildPredicate(join, (Comparable) filter.getValue(), builder, filter.getKey());");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "private Predicate handleCollectionValue(Root<T> root, CriteriaBuilder builder) {");
            file.writeln(2, "Collection<?> values = (Collection<?>) filter.getValue();");
            file.writeln(2, "CriteriaBuilder.In<Object> inClause = builder.in(root.get(filter.getKey()));");
            file.writeln(0, "");
            file.writeln(2, "for (Object item : values) {");
            file.writeln(3, "inClause.value(item);");
            file.writeln(2, "}");
            file.writeln(0, "");
            file.writeln(2, "return inClause;");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "private void cleanMap(Map<String, Object> map) {");
            file.writeln(2, "map.entrySet().forEach(e -> {");
            file.writeln(3, "if (e.getValue() != null && LocalDateTimeParser.matches(e.getValue().toString()))");
            file.writeln(4, "e.setValue(LocalDateTimeParser.parse(e.getValue().toString()));");
            file.writeln(2, "});");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "@SuppressWarnings({\"unchecked\", \"rawtypes\"})");
            file.writeln(1, "private Predicate buildPredicate(Path<?> root, Comparable value, CriteriaBuilder builder, String key) {");
            file.writeln(2, "switch (filter.getOperator()) {");
            file.writeln(3, "case EQUAL:");
            file.writeln(4, "return builder.equal(root.get(key), value);");
            file.writeln(3, "case GREATER_THAN:");
            file.writeln(4, "return builder.greaterThan(root.get(key), value);");
            file.writeln(3, "case GREATER_THAN_OR_EQUAL:");
            file.writeln(4, "return builder.greaterThanOrEqualTo(root.get(key), value);");
            file.writeln(3, "case LESS_THAN:");
            file.writeln(4, "return builder.lessThan(root.get(key), value);");
            file.writeln(3, "case LESS_THAN_OR_EQUAL:");
            file.writeln(4, "return builder.lessThanOrEqualTo(root.get(key), value);");
            file.writeln(3, "case NOT_EQUAL:");
            file.writeln(4, "return builder.notEqual(root.get(key), value);");
            file.writeln(3, "case LIKE:");
            file.writeln(4, "return builder.like(root.get(key).as(String.class), (String) value);");
            file.writeln(3, "case NOT_LIKE:");
            file.writeln(4, "return builder.notLike(root.get(key).as(String.class), (String) value);");
            file.writeln(3, "default:");
            file.writeln(4, "return builder.equal(root.get(key), value);");
            file.writeln(2, "}");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "private Predicate buildBetweenPredicate(Path<?> root, Object value, CriteriaBuilder builder, String key) {");
            file.writeln(2, "List<?> list = (List<?>) value;");
            file.writeln(2, "return builder.between(root.get(key), (Comparable) list.get(0), (Comparable) list.get(1));");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(0, "}");
        }
    }
}
