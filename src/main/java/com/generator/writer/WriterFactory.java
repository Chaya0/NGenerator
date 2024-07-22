package com.generator.writer;

import java.util.HashMap;
import java.util.Map;

import com.generator.annotations.WriterArchitecture;
import com.generator.annotations.WriterVersion;
import com.generator.writer.frontend.FrontendWriter;

public class WriterFactory {
	private static final Map<String, Class<? extends FrontendWriter>> writers = new HashMap<>();


    public static void registerWriter(Class<? extends FrontendWriter> writerClass) {
        WriterVersion versionAnnotation = writerClass.getAnnotation(WriterVersion.class);
        WriterArchitecture architectureAnnotation = writerClass.getAnnotation(WriterArchitecture.class);
        if (versionAnnotation != null && architectureAnnotation != null) {
            String key = versionAnnotation.value() + "-" + architectureAnnotation.value();
            writers.put(key, writerClass);
        }
    }

    public static FrontendWriter getWriter(String version, String architecture) throws Exception {
        String key = version + "-" + architecture;
        Class<? extends FrontendWriter> writerClass = writers.get(key);
        if (writerClass != null) {
            return writerClass.getDeclaredConstructor().newInstance();
        }
        throw new IllegalArgumentException("No writer found for version: " + version + " and architecture: " + architecture);
    }
}
