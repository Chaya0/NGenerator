package com.generator.writer.java.config.quartz;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaAutowiringSpringBeanJobFactoryWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaAutowiringSpringBeanJobFactoryWriter javaAutowiringSpringBeanJobFactoryWriter;

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
        javaAutowiringSpringBeanJobFactoryWriter.create();

        // Then
        verify(mockGeneratorOutputFile).writeln(0, "package com.myApp.config.quartz;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import org.quartz.spi.TriggerFiredBundle;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.beans.factory.annotation.Autowired;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.beans.factory.config.AutowireCapableBeanFactory;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.context.ApplicationContext;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.context.ApplicationContextAware;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.scheduling.quartz.SpringBeanJobFactory;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {");
        verify(mockGeneratorOutputFile).writeln(1, "");
        verify(mockGeneratorOutputFile).writeln(1, "private AutowireCapableBeanFactory beanFactory;");
        verify(mockGeneratorOutputFile).writeln(1, "");
        verify(mockGeneratorOutputFile).writeln(1, "@Autowired");
        verify(mockGeneratorOutputFile).writeln(1, "public void setApplicationContext(final ApplicationContext context) {");
        verify(mockGeneratorOutputFile).writeln(2, "beanFactory = context.getAutowireCapableBeanFactory();");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(1, "");
        verify(mockGeneratorOutputFile).writeln(1, "@Override");
        verify(mockGeneratorOutputFile).writeln(1, "protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {");
        verify(mockGeneratorOutputFile).writeln(2, "final Object job = super.createJobInstance(bundle);");
        verify(mockGeneratorOutputFile).writeln(2, "beanFactory.autowireBean(job);");
        verify(mockGeneratorOutputFile).writeln(2, "return job;");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "}");

        verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
    }
}