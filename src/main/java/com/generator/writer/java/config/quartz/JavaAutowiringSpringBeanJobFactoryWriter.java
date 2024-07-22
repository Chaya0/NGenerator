package com.generator.writer.java.config.quartz;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaAutowiringSpringBeanJobFactoryWriter {
	public void create() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getConfigPackagePath() + "/quartz", "AutowiringSpringBeanJobFactory.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + WriterUtils.getImportDefaultPackage() + ".config.quartz;");
			file.writeln(0, "");
			file.writeln(0, "import org.quartz.spi.TriggerFiredBundle;");
			file.writeln(0, "import org.springframework.beans.factory.annotation.Autowired;");
			file.writeln(0, "import org.springframework.beans.factory.config.AutowireCapableBeanFactory;");
			file.writeln(0, "import org.springframework.context.ApplicationContext;");
			file.writeln(0, "import org.springframework.context.ApplicationContextAware;");
			file.writeln(0, "import org.springframework.scheduling.quartz.SpringBeanJobFactory;");
			file.writeln(0, "");
			file.writeln(0, "public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {");
			file.writeln(1, "");
			file.writeln(1, "private AutowireCapableBeanFactory beanFactory;");
			file.writeln(1, "");
			file.writeln(1, "@Autowired");
			file.writeln(1, "public void setApplicationContext(final ApplicationContext context) {");
			file.writeln(2, "beanFactory = context.getAutowireCapableBeanFactory();");
			file.writeln(1, "}");
			file.writeln(1, "");
			file.writeln(1, "@Override");
			file.writeln(1, "protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {");
			file.writeln(2, "final Object job = super.createJobInstance(bundle);");
			file.writeln(2, "beanFactory.autowireBean(job);");
			file.writeln(2, "return job;");
			file.writeln(1, "}");
			file.writeln(0, "}");

		}
	}
}
