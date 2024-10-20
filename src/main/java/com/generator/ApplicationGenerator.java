package com.generator;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.generator.model.AppModel;
import com.generator.model.enums.properties.DependencyCode;
import com.generator.model.enums.properties.SpringInitializerDependencyName;
import com.generator.reader.XMLModelReader;
import com.generator.util.PomEditorUtil;
import com.generator.writer.SpringStartExtractor;
import com.generator.writer.Writer;
import com.generator.writer.java.JavaApplicationPropertiesWriter;
import com.generator.writer.java.components.JavaControllerWriter;
import com.generator.writer.java.components.JavaRepositoryWriter;
import com.generator.writer.java.components.JavaServiceWriter;
import com.generator.writer.java.components.basic.JavaBasicControllerWriter;
import com.generator.writer.java.components.basic.JavaBasicEntityAttributeWriter;
import com.generator.writer.java.components.basic.JavaBasicRepositoryWriter;
import com.generator.writer.java.components.basic.JavaBasicServiceWriter;
import com.generator.writer.java.components.generic.JavaEntityAttributeWriter;
import com.generator.writer.java.components.generic.JavaEntityWriter;
import com.generator.writer.java.components.generic.JavaEnumWriter;
import com.generator.writer.java.components.generic.JavaGenericControllerWriter;
import com.generator.writer.java.components.generic.JavaGenericRepositoryWriter;
import com.generator.writer.java.components.generic.JavaGenericServiceWriter;
import com.generator.writer.java.components.tests.integration.JavaControllerIntegrationTestWriter;
import com.generator.writer.java.components.tests.integration.JavaRepositoryIntegrationTestWriter;
import com.generator.writer.java.components.tests.integration.JavaServiceIntegrationTestWriter;
import com.generator.writer.java.components.tests.unit.JavaControllerUnitTestWriter;
import com.generator.writer.java.components.tests.unit.JavaRepositoryUnitTestWriter;
import com.generator.writer.java.components.tests.unit.JavaServiceUnitTestWriter;
import com.generator.writer.java.config.JavaConfigWriter;
import com.generator.writer.java.config.quartz.JavaAutowiringSpringBeanJobFactoryWriter;
import com.generator.writer.java.config.quartz.JavaQuartzConfigurationWriter;
import com.generator.writer.java.config.security.JavaPermissionsWriter;
import com.generator.writer.java.config.security.JavaSecurityWriter;
import com.generator.writer.java.dto.JavaDTOWriter;
import com.generator.writer.java.dto.JavaErrorResponseDTOWriter;
import com.generator.writer.java.dto.JavaGenericResponseWriter;
import com.generator.writer.java.exceptions.JavaApplicationExceptionWriter;
import com.generator.writer.java.exceptions.JavaExceptionHandlerWriter;
import com.generator.writer.java.exceptions.JavaExceptionWriter;
import com.generator.writer.java.exceptions.JavaOperationNotSupprotedExceptionWriter;
import com.generator.writer.java.placeholders.ComponentAffix;
import com.generator.writer.java.placeholders.JavaComponentType;
import com.generator.writer.java.placeholders.JavaImportType;
import com.generator.writer.java.placeholders.JavaPackageType;
import com.generator.writer.java.specification.JavaSpecificationWriter;
import com.generator.writer.java.utils.JavaUtilWriter;
import com.generator.writer.utils.WriterUtils;

public class ApplicationGenerator {
	private static final Logger logger = LogManager.getLogger(ApplicationGenerator.class);
	private static List<Writer> writers = initJavaWriters();
	private static AppModel appModel = XMLModelReader.readModel();

	public static void generateApp() {
		JavaImportType.loadProjectImports();
		JavaPackageType.loadProjectPackages();
		/**
		 * Order of loading ComponntAffix enums and JavaComponentType enums is important
		 * ComponentAffix enum needs to be loaded first so that JavaComponentType could
		 * be initialized in the right way
		 */
		ComponentAffix.loadComponentAffixes();
		JavaComponentType.loadProjectComponents();

		extractAppIfItDoesntExists();

		if (Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
			AppModelUtil.addAdditionalEntitiesAndAttributes(appModel);
		}
		JavaAutowiringSpringBeanJobFactoryWriter autowiringSpringBeanJobFactoryWriter = new JavaAutowiringSpringBeanJobFactoryWriter();
		JavaQuartzConfigurationWriter quartzWriter = new JavaQuartzConfigurationWriter();

//		JavaJwtFilterWriter jwtFilterWriter = new JavaJwtFilterWriter();
//		JavaJwtUtilWriter jwtUtilWriter = new JavaJwtUtilWriter();
//		JavaSpringSecurityConfigWriter javaSpringSecurityConfigWriter = new JavaSpringSecurityConfigWriter();
//		JavaSwaggerWriter swaggerWriter = new JavaSwaggerWriter();

		JavaErrorResponseDTOWriter javaErrorResponseDTOWriter = new JavaErrorResponseDTOWriter();
		JavaGenericResponseWriter javaGenericResponseWriter = new JavaGenericResponseWriter();
		JavaExceptionHandlerWriter javaExceptionHandlerWriter = new JavaExceptionHandlerWriter();
		JavaApplicationExceptionWriter javaApplicationExceptionWriter = new JavaApplicationExceptionWriter();
		JavaOperationNotSupprotedExceptionWriter javaOperationNotSupprotedExceptionWriter = new JavaOperationNotSupprotedExceptionWriter();
		JavaSpecificationWriter javaSpecificationWriter = new JavaSpecificationWriter();
		JavaApplicationPropertiesWriter javaPropertiesWriter = new JavaApplicationPropertiesWriter();
		JavaUtilWriter javaUtilWriter = new JavaUtilWriter();
		JavaExceptionWriter javaExceptionWriter = new JavaExceptionWriter();
		JavaSecurityWriter javaSecurityWriter = new JavaSecurityWriter();
		JavaConfigWriter javaConfigWriter = new JavaConfigWriter();
		JavaDTOWriter javaDTOWriter = new JavaDTOWriter();
		for (Writer writer : writers) {
			try {
				writer.create(appModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			autowiringSpringBeanJobFactoryWriter.create();
			quartzWriter.create();
//			jwtFilterWriter.create();
//			jwtUtilWriter.create();
//			javaSpringSecurityConfigWriter.create();
//			swaggerWriter.create();
			javaErrorResponseDTOWriter.create();
			javaGenericResponseWriter.create();
			javaExceptionHandlerWriter.create();
			javaOperationNotSupprotedExceptionWriter.create();
			javaApplicationExceptionWriter.create();
			javaSpecificationWriter.create();
			javaUtilWriter.create();
			javaPropertiesWriter.create();
			javaExceptionWriter.create();
			javaSecurityWriter.create();
			javaConfigWriter.create();
			javaDTOWriter.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Splendid.");
	}

	private static List<Writer> initJavaWriters() {
		Writer entityWriter = new JavaEntityWriter();
		Writer enumWriter = new JavaEnumWriter();
		Writer genControllerWriter = new JavaGenericControllerWriter();
		Writer basicControllerWriter = new JavaBasicControllerWriter();
		Writer controllerWriter = new JavaControllerWriter();
		Writer genRepositoryWriter = new JavaGenericRepositoryWriter();
		Writer basicRepositoryWriter = new JavaBasicRepositoryWriter();
		Writer repositoryWriter = new JavaRepositoryWriter();
		Writer genServiceWriter = new JavaGenericServiceWriter();
		Writer basicServiceWriter = new JavaBasicServiceWriter();
		Writer serviceWriter = new JavaServiceWriter();
		Writer javaEntityAttributeWriter = new JavaEntityAttributeWriter();
		Writer javaBasicAttributeWriter = new JavaBasicEntityAttributeWriter();
		Writer javaPermissionsWriter = new JavaPermissionsWriter();
		Writer javaRepositoryIntegrationTestWriter = new JavaRepositoryIntegrationTestWriter();
		Writer javaRepositoryUnitTestWriter = new JavaRepositoryUnitTestWriter();
		Writer javaServiceIntegrationTestWriter = new JavaServiceIntegrationTestWriter();
		Writer javaServiceUnitTestWriter = new JavaServiceUnitTestWriter();
		Writer javaControllerIntegrationTestWriter = new JavaControllerIntegrationTestWriter();
		Writer javaControllerUnitTestWriter = new JavaControllerUnitTestWriter();
		return Arrays.asList(entityWriter, genControllerWriter, genRepositoryWriter, genServiceWriter, controllerWriter, repositoryWriter, serviceWriter, enumWriter, basicControllerWriter,
				basicRepositoryWriter, basicServiceWriter, javaEntityAttributeWriter, javaBasicAttributeWriter, javaPermissionsWriter, javaRepositoryIntegrationTestWriter,
				javaRepositoryUnitTestWriter, javaServiceIntegrationTestWriter, javaServiceUnitTestWriter, javaControllerIntegrationTestWriter, javaControllerUnitTestWriter);
	}

	private static void extractAppIfItDoesntExists() {

		String path = Application.getSpringProperties().getProjectPath() + Application.getSpringProperties().getBaseDir();
		File file = new File(path);
		if (!file.exists()) {
			logger.info("Application folder is not detected. New insatance of application is being created on path : " + path);
			addMissingDependencies();
			SpringStartExtractor.extractApp();

			try {
				System.out.println("Adding missing dependencies");
				if (Application.getGeneratorProperties().isGenerateSwaggerComponent())
					PomEditorUtil.addDependencyToPom(WriterUtils.getProjectPath() + "/pom.xml", DependencyCode.SWAGGER.getCode("2.3.0"));
				if (Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
					PomEditorUtil.addDependencyToPom(WriterUtils.getProjectPath() + "/pom.xml", DependencyCode.JWT.getCode("0.9.1"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			logger.info("Application already exsists on path : " + path + ". Generic files will be overwritten!");
		}
	}

	private static void addMissingDependencies() {
		if (Application.getGeneratorProperties().isGenerateQuartzComponents()) {
			Application.getSpringProperties().addDependency(SpringInitializerDependencyName.QUARTZ.getCode());
		}
	}
}
