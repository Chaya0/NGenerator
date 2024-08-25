package com.generator.writer.java.specification;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaSearchDTOWriter {
	 public void create() throws Exception {
	        try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getSpecificationPackagePath(), "GenericSpecification.java", false)) {
	            if (file.hasAlreadyExisted()) {
	                return;
	            }
	            
	            file.writeln(0, "package " + WriterUtils.getSpecificationPackageImportPath() + ";");
	            file.writeln(0, "");
	            file.writeln(0, "import java.lang.reflect.Field;");
	            file.writeln(0, "import java.text.ParseException;");
	            file.writeln(0, "import java.text.SimpleDateFormat;");
	            file.writeln(0, "import java.time.LocalDate;");
	            file.writeln(0, "import java.time.LocalDateTime;");
	            file.writeln(0, "import java.util.Arrays;");
	            file.writeln(0, "import java.util.Date;");
	            file.writeln(0, "import java.util.List;");
	            file.writeln(0, "import java.util.Locale;");
	            file.writeln(0, "");
	            file.writeln(0, "import org.springframework.data.domain.PageRequest;");
	            file.writeln(0, "import org.springframework.data.domain.Pageable;");
	            file.writeln(0, "import org.springframework.data.domain.Sort;");
	            file.writeln(0, "import org.springframework.stereotype.Component;");
	            file.writeln(0, "");
	            file.writeln(0, "import com.fasterxml.jackson.annotation.JsonIgnore;");
	            file.writeln(0, "import com.mds.merv.exceptions.FieldNotFoundException;");
	            file.writeln(0, "import com.mds.merv.utils.ApiUtil;");
	            file.writeln(0, "");
	            file.writeln(0, "import lombok.AllArgsConstructor;");
	            file.writeln(0, "import lombok.Getter;");
	            file.writeln(0, "import lombok.NoArgsConstructor;");
	            file.writeln(0, "import lombok.Setter;");
	            file.writeln(0, "");
	            file.writeln(0, "");
	            file.writeln(0, "@Getter");
	            file.writeln(0, "@Setter");
	            file.writeln(0, "@NoArgsConstructor");
	            file.writeln(0, "@AllArgsConstructor");
	            file.writeln(0, "@Component");
	            file.writeln(0, "public class SearchDTO {");
	            file.writeln(1, "private Integer pageNumber;");
	            file.writeln(1, "private Integer pageSize;");
	            file.writeln(1, "private Sort sort;");
	            file.writeln(1, "private FilterGroup filterGroup;");
	            file.writeln(1, "@JsonIgnore");
	            file.writeln(1, "private Class<?> clazz;");
	            file.writeln(1, "");
	            file.writeln(1, "/**");
	            file.writeln(1, "* @return Pageable object for pagination and sorting based on the request.");
	            file.writeln(1, "*/");
	            file.writeln(1, "public Pageable createPageable() {");
	            file.writeln(2, "int number = (getPageNumber() != null) ? getPageNumber() : Integer.parseInt(ApiUtil.DEFAULT_PAGE);");
	            file.writeln(2, "int size = (getPageSize() != null) ? getPageSize() : Integer.parseInt(ApiUtil.DEFAULT_SIZE);");
	            file.writeln(2, "Sort sort = (getSort() != null && !getSort().isEmpty()) ? getSort() : Sort.unsorted();");
	            file.writeln(2, "return PageRequest.of(number, size, sort);");
	            file.writeln(1, "}");
	            file.writeln(1, "");
	            file.writeln(1, "/**");
	            file.writeln(1, "* Builds a Specification object for filtering data based on the request.");
	            file.writeln(1, "*");
	            file.writeln(1, "* @param clazz the entity class for which the search request is made");
	            file.writeln(1, "* @return a wrapped Specification object for filtering data based on the");
	            file.writeln(1, "* request");
	            file.writeln(1, "* @throws FieldNotFoundException if keys of the filter request do not");
	            file.writeln(0, "* correspond to fields of the {@code clazz}");
	            file.writeln(0, "* entity");
	            file.writeln(0, "*/");
	            file.writeln(0, "public <T> SpecificationBasic<T> buildSpecification(Class<T> clazz) throws FieldNotFoundException {");
	            file.writeln(1, "this.clazz = clazz;");
	            file.writeln(1, "SpecificationBasic<T> specification = SpecificationBasic.get();");
	            file.writeln(1, "checkFilters(clazz);");
	            file.writeln(1, "parseFilters(filterGroup.getFilters(), specification, filterGroup.getLogicalOperator());");
	            file.writeln(1, "return specification;");
	            file.writeln(0, "}");
	            file.writeln(0, "");
	            file.writeln(0, "/**");
	            file.writeln(0, "* Builds a specification structure with possible nested filters and");
	            file.writeln(0, "* combinations of logical operators.");
	            file.writeln(0, "*/");
	            file.writeln(0, "private void parseFilters(List<SearchFilter> filters, SpecificationBasic<?> specification, LogicalOperator logicalOperator) {");
	            file.writeln(1, "for (SearchFilter filter : filters) {");
	            file.writeln(2, "if (filter.getFilters() != null && !filter.getFilters().isEmpty()) {");
	            file.writeln(3, "parseFilters(filter.getFilters(), specification, filter.getLogicalOperator());");
	            file.writeln(3, "} else {");
	            file.writeln(4, "if (logicalOperator.equals(LogicalOperator.OR)) {");
	            file.writeln(5, "specification.or(filter.getKey(), filter.getSearchOperator(), convertFilterValue(filter));");
	            file.writeln(4, "} else if (logicalOperator.equals(LogicalOperator.AND)) {");
	            file.writeln(5, "specification.and(filter.getKey(), filter.getSearchOperator(), convertFilterValue(filter));");
	            file.writeln(4, "}");
	            file.writeln(3, "}");
	            file.writeln(2, "}");
	            file.writeln(1, "}");
	            file.writeln(0, "");
	            file.writeln(1, "/**");
	            file.writeln(1, "* Checks if the filter key is a field of the model entity.");
	            file.writeln(1, "*");
	            file.writeln(1, "* @param clazz Model entity");
	            file.writeln(1, "* @throws FieldNotFoundException if the field is not found in the model entity.");
	            file.writeln(1, "*/");
	            file.writeln(1, "protected void checkFilters(Class<?> clazz) throws FieldNotFoundException {");
	            file.writeln(2, "List<String> fields = Arrays.stream(clazz.getDeclaredFields()).map(Field::getName).toList();");
	            file.writeln(2, "for (SearchFilter filter : filterGroup.getFilters()) {");
	            file.writeln(3, "if ((filter.getFilters() == null || filter.getFilters().isEmpty()) && !fields.contains(filter.getKey())) throw new FieldNotFoundException(filter.getKey());");
	            file.writeln(2, "}");
	            file.writeln(1, "}");
	            file.writeln(0, "");
	            file.writeln(1, "public Object convertFilterValue(SearchFilter filter) {");
	            file.writeln(2, "try {");
	            file.writeln(3, "String key = filter.getKey();");
	            file.writeln(3, "Object value = filter.getValue();");
	            file.writeln(0, "");
	            file.writeln(3, "Field field = clazz.getDeclaredField(key);");
	            file.writeln(3, "Class<?> fieldType = field.getType();");
	            file.writeln(0, "");
	            file.writeln(3, "return castValueToType(value, fieldType);");
	            file.writeln(2, "} catch (NoSuchFieldException | SecurityException e) {");
	            file.writeln(3, "e.printStackTrace();");
	            file.writeln(2, "}");
	            file.writeln(2, "return filter.getValue();");
	            file.writeln(1, "}");
	            file.writeln(0, "");
	            file.writeln(1, "private Object castValueToType(Object value, Class<?> type) {");
	            file.writeln(2, "if (type.equals(LocalDateTime.class)) {");
	            file.writeln(3, "return LocalDateTime.parse((String) value);");
	            file.writeln(2, "} else if (type.equals(LocalDate.class)) {");
	            file.writeln(3, "return LocalDate.parse((String) value);");
	            file.writeln(2, "} else if (type.equals(String.class)) {");
	            file.writeln(3, "return value.toString();");
	            file.writeln(2, "} else if (type.equals(Integer.class)) {");
	            file.writeln(3, "return Integer.valueOf((String) value);");
	            file.writeln(2, "} else if (type.equals(Long.class)) {");
	            file.writeln(3, "return Long.valueOf((String) value);");
	            file.writeln(2, "}else if (type.equals(Date.class)) {");
	            file.writeln(3, "return parseDate(value.toString());");
	            file.writeln(2, "}");
	            file.writeln(2, "return value;");
	            file.writeln(1, "}");
	            file.writeln(0, "");
	            file.writeln(1, "private Date parseDate(String dateString) {");
	            file.writeln(2, "try {");
	            file.writeln(3, "SimpleDateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd'T'HH:mm:ss.SSSSSS\", Locale.ENGLISH);");
	            file.writeln(3, "return dateFormat.parse(dateString);");
	            file.writeln(2, "} catch (ParseException e) {");
	            file.writeln(3, "e.printStackTrace();");
	            file.writeln(2, "}");
	            file.writeln(2, "return null;");
	            file.writeln(1, "}");
	            file.writeln(0, "}");
	        }
	 }
}