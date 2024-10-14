package com.generator.writer.java.config.quartz;

import java.io.IOException;

import com.generator.writer.java.JavaImportUtil;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaQuartzConfigurationWriter {
	public void create() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getConfigPackagePath() + "/quartz", "QuartzConfiguration.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + WriterUtils.getImportDefaultPackage() + ".config.quartz;");
			file.writeln(0, "");
			file.writeln(0, JavaImportUtil.getJavaIoIOExceptionImport());
			file.writeln(0, JavaImportUtil.getJavaUtilImport());
			file.writeln(0, "");
			file.writeln(0, JavaImportUtil.getApacheCommonsArrayUtilsImport());
			file.writeln(0, JavaImportUtil.getQuartzTriggerImport());
			file.writeln(0, JavaImportUtil.getSpringPropertiesFactoryBeanImport());
			file.writeln(0, JavaImportUtil.getSpringApplicationContextImport());
			file.writeln(0, JavaImportUtil.getSpringBeanImport());
			file.writeln(0, JavaImportUtil.getSpringConfigurationImport());
			file.writeln(0, JavaImportUtil.getSpringCoreClassPathResourceImport());
			file.writeln(0, JavaImportUtil.getSpringQuartzSchedulerFactoryBeanImport());
			file.writeln(0, JavaImportUtil.getSpringBeanJobFactoryImport());
			file.writeln(0, "");
			file.writeln(0, "@Configuration");
			file.writeln(0, "public class QuartzConfiguration {");
			file.writeln(1, "");
			file.writeln(1, "private final ApplicationContext applicationContext;");
			file.writeln(1, "");
			file.writeln(1, "public QuartzConfiguration(ApplicationContext applicationContext) {");
			file.writeln(2, "this.applicationContext = applicationContext;");
			file.writeln(1, "}");
			file.writeln(0, "");

			writeSpringBeanJobFactory(file);

			writeSchedulerBean(file);

			writeQuartzPropertiesBean(file);

			file.writeln(0, "}");
		}
	}

	private void writeQuartzPropertiesBean(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@Bean");
		file.writeln(1, "public Properties quartzProperties() throws IOException {");
		file.writeln(2, "PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();");
		file.writeln(2, "propertiesFactoryBean.setLocation(new ClassPathResource(\"/quartz.properties\"));");
		file.writeln(2, "propertiesFactoryBean.afterPropertiesSet();");
		file.writeln(2, "return propertiesFactoryBean.getObject();");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeSchedulerBean(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@Bean");
		file.writeln(1, "public SchedulerFactoryBean scheduler(Trigger... triggers) throws IOException {");
		file.writeln(2, "SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();");
		file.writeln(2, "");
		file.writeln(2, "schedulerFactory.setOverwriteExistingJobs(true);");
		file.writeln(2, "schedulerFactory.setAutoStartup(true);");
		file.writeln(2, "schedulerFactory.setQuartzProperties(quartzProperties());");
		file.writeln(2, "schedulerFactory.setJobFactory(springBeanJobFactory());");
		file.writeln(2, "schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);");
		file.writeln(2, "");
		file.writeln(2, "if (ArrayUtils.isNotEmpty(triggers)) {");
		file.writeln(3, "schedulerFactory.setTriggers(triggers);");
		file.writeln(2, "}");
		file.writeln(2, "return schedulerFactory;");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeSpringBeanJobFactory(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@Bean");
		file.writeln(1, "public SpringBeanJobFactory springBeanJobFactory() {");
		file.writeln(2, "AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();");
		file.writeln(2, "jobFactory.setApplicationContext(applicationContext);");
		file.writeln(2, "return jobFactory;");
		file.writeln(1, "}");
		file.writeln(0, "");
	}
}
