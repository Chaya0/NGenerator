package com.generator.writer.java.config.quartz;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaQuartzConfigurationWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaQuartzConfigurationWriter javaQuartzConfigurationWriter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		// Setup default package
		Properties properties = new Properties();
		properties.setProperty("importDefaultPackage", "com.myApp");

		// Mock static methods from WriterUtils
		try {
			when(WriterUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(mockGeneratorOutputFile);
		} catch (Exception e) {
			fail("Failed to mock WriterUtils.getOutputResource method");
		}
	}

	@Test
    public void testCreate() throws Exception {
        // Given
        when(mockGeneratorOutputFile.hasAlreadyExisted()).thenReturn(false);
        doNothing().when(mockGeneratorOutputFile).writeln(anyInt(), anyString());

        // When
        javaQuartzConfigurationWriter.create();

        // Then
        verify(mockGeneratorOutputFile).writeln(0, "package com.myApp.config.quartz;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import java.io.IOException;");
        verify(mockGeneratorOutputFile).writeln(0, "import java.util.Properties;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import org.apache.commons.lang3.ArrayUtils;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.quartz.Trigger;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.beans.factory.config.PropertiesFactoryBean;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.context.ApplicationContext;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.context.annotation.Bean;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.context.annotation.Configuration;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.core.io.ClassPathResource;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.scheduling.quartz.SchedulerFactoryBean;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.scheduling.quartz.SpringBeanJobFactory;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "@Configuration");
        verify(mockGeneratorOutputFile).writeln(0, "public class QuartzConfiguration {");
        verify(mockGeneratorOutputFile).writeln(1, "");
        verify(mockGeneratorOutputFile).writeln(1, "private final ApplicationContext applicationContext;");
        verify(mockGeneratorOutputFile).writeln(1, "");
        verify(mockGeneratorOutputFile).writeln(1, "public QuartzConfiguration(ApplicationContext applicationContext) {");
        verify(mockGeneratorOutputFile).writeln(2, "this.applicationContext = applicationContext;");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        // Verify Quartz properties bean
        verify(mockGeneratorOutputFile).writeln(1, "@Bean");
        verify(mockGeneratorOutputFile).writeln(1, "public Properties quartzProperties() throws IOException {");
        verify(mockGeneratorOutputFile).writeln(2, "PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();");
        verify(mockGeneratorOutputFile).writeln(2, "propertiesFactoryBean.setLocation(new ClassPathResource(\"/quartz.properties\"));");
        verify(mockGeneratorOutputFile).writeln(2, "propertiesFactoryBean.afterPropertiesSet();");
        verify(mockGeneratorOutputFile).writeln(2, "return propertiesFactoryBean.getObject();");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        // Verify Scheduler bean
        verify(mockGeneratorOutputFile).writeln(1, "@Bean");
        verify(mockGeneratorOutputFile).writeln(1, "public SchedulerFactoryBean scheduler(Trigger... triggers) throws IOException {");
        verify(mockGeneratorOutputFile).writeln(2, "SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();");
        verify(mockGeneratorOutputFile).writeln(2, "");
        verify(mockGeneratorOutputFile).writeln(2, "schedulerFactory.setOverwriteExistingJobs(true);");
        verify(mockGeneratorOutputFile).writeln(2, "schedulerFactory.setAutoStartup(true);");
        verify(mockGeneratorOutputFile).writeln(2, "schedulerFactory.setQuartzProperties(quartzProperties());");
        verify(mockGeneratorOutputFile).writeln(2, "schedulerFactory.setJobFactory(springBeanJobFactory());");
        verify(mockGeneratorOutputFile).writeln(2, "schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);");
        verify(mockGeneratorOutputFile).writeln(2, "");
        verify(mockGeneratorOutputFile).writeln(2, "if (ArrayUtils.isNotEmpty(triggers)) {");
        verify(mockGeneratorOutputFile).writeln(3, "schedulerFactory.setTriggers(triggers);");
        verify(mockGeneratorOutputFile).writeln(2, "}");
        verify(mockGeneratorOutputFile).writeln(2, "return schedulerFactory;");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        // Verify SpringBeanJobFactory bean
        verify(mockGeneratorOutputFile).writeln(1, "@Bean");
        verify(mockGeneratorOutputFile).writeln(1, "public SpringBeanJobFactory springBeanJobFactory() {");
        verify(mockGeneratorOutputFile).writeln(2, "AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();");
        verify(mockGeneratorOutputFile).writeln(2, "jobFactory.setApplicationContext(applicationContext);");
        verify(mockGeneratorOutputFile).writeln(2, "return jobFactory;");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "}");

        verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
    }
}