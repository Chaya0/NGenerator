package com.generator.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;


public class ReflectionUtil {

	/**
	 * Retrieves classes that implement a specified interface.
	 *
	 * @param <T>            the type of the interface
	 * @param interfaceClass the interface class to search for implementations
	 * @return a list of classes that implement the specified interface
	 */
	public static <T> List<Class<? extends T>> getFilesThatIplement(Class<T> interfaceClass) {
		List<Class<? extends T>> instances = new ArrayList<>();
		Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("")).setScanners(Scanners.SubTypes));

		Set<Class<? extends T>> subTypes = reflections.getSubTypesOf(interfaceClass);
		for (Class<? extends T> subType : subTypes) {
			instances.add(subType);
		}
		return instances;
	}

	/**
	 * Retrieves classes that implement a specified interface.
	 *
	 * @param interfaceClass the interface class to search for implementations
	 * @return a list of classes that implement the specified interface
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<?> getClassesThatImplement(Class<?> interfaceClass) {
		List<Class<? extends T>> instances = new ArrayList<>();
		Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("")).setScanners(Scanners.SubTypes));

		reflections.getSubTypesOf(interfaceClass).forEach(subType -> {
			if (!subType.isInterface()) {
				subType.getClass();
				instances.add((Class<? extends T>) subType);
			}
		});
		System.out.println(instances);
		System.out.println(getInstances(instances));
		return instances;
	}

	/**
	 * Instantiates objects for the provided classes.
	 *
	 * @param classes a list of Class objects for which instances need to be created
	 * @return a list of instances created for the provided classes
	 */
	public static List<?> getInstances(List<?> classes) {
		List<Object> instances = new ArrayList<>();
		for (Object clazz : classes) {
			try {
				instances.add(((Class<?>) clazz).getDeclaredConstructor().newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instances;
	}


	/**
	 * Finds classes that are annotated with a specific annotation.
	 *
	 * @param annotationClass the annotation class to search for
	 * @return a set of classes that are annotated with the specified annotation
	 */
	public static Set<Class<?>> findClassesWithAnnotation(Class<? extends Annotation> annotationClass) {
		Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("")).setScanners(Scanners.SubTypes, Scanners.TypesAnnotated));
		Set<Class<?>> configurationClasses = reflections.getTypesAnnotatedWith(annotationClass);
		return configurationClasses;
	}

	/**
	 * Finds classes that are annotated with all specified annotations.
	 *
	 * @param annotationClasses a list of annotation classes to search for
	 * @return a set of classes that are annotated with all specified annotations
	 */
	public static Set<Class<?>> findClassesWithAnnotations(List<Class<? extends Annotation>> annotationClasses) {
		Set<Class<?>> annotatedClasses = findClassesWithAnnotation(annotationClasses.get(0));
		Set<Class<?>> filteredClasses = new HashSet<>();
		for (Class<?> clazz : annotatedClasses) {
			if (hasAllAnnotations(clazz, annotationClasses)) {
				filteredClasses.add(clazz);
			}
		}
		return filteredClasses;
	}

	/**
	 * Checks if a class has all specified annotations.
	 *
	 * @param clazz             the class to check for annotations
	 * @param annotationClasses a list of annotation classes to check for
	 * @return true if the class has all specified annotations, false otherwise
	 */
	private static boolean hasAllAnnotations(Class<?> clazz, List<Class<? extends Annotation>> annotationClasses) {
		for (Class<? extends Annotation> annotationClass : annotationClasses) {
			if (!clazz.isAnnotationPresent(annotationClass)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Retrieves instances of classes that implement a given interface.
	 *
	 * @param <T>            the type of the interface
	 * @param interfaceClass the class object representing the interface
	 * @return a list of instances implementing the specified interface found in the
	 *         classpath
	 */
	public static <T> List<T> getInstancesForInterface(Class<T> interfaceClass) {
		Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("")).setScanners(Scanners.SubTypes));
		Set<Class<? extends T>> subTypes = reflections.getSubTypesOf(interfaceClass);
		List<T> instances = new ArrayList<>();
		for (Class<? extends T> subType : subTypes) {
			try {
				instances.add(subType.getDeclaredConstructor().newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instances;
	}

}
