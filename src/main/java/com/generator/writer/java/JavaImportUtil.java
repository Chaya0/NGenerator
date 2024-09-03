package com.generator.writer.java;

import com.generator.util.StringUtils;
import com.generator.writer.utils.WriterUtils;

public class JavaImportUtil {

	public static String getPackageImport(String packageName) {
        return "package " + packageName + ";";
    }

    public static String getModelImport(String upperCaseName) {
        return "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";";
    }

    public static String getGenericServiceImport() {
        return "import " + WriterUtils.getImportServicePackageName(false) + ".GenericService;";
    }

    public static String getApplicationExceptionImport() {
        return WriterUtils.getApplicationExceptionImport();
    }

    public static String getRepositoryImport(String entityName, String upperCaseName) {
        return "import " + WriterUtils.getImportRepositoryPackageName(false, entityName) + "." + upperCaseName + "Repository;";
    }

    public static String getJavaUtilImport() {
        return "import java.util.*;";
    }

    public static String getJavaTimeImport() {
        return "import java.time.*;";
    }

    public static String getEntityNotFoundExceptionImport() {
        return "import jakarta.persistence.EntityNotFoundException;";
    }

    public static String getEnumImport(String enumName) {
        return "import " + WriterUtils.getImportModelEnumsPackageName() + "." + StringUtils.uppercaseFirst(enumName) + ";";
    }

    public static String getControllerImport(String entityName, String upperCaseName) {
        return "import " + WriterUtils.getImportControllerPackageName(false, entityName) + "." + upperCaseName + "Controller;";
    }

    public static String getSpringResponseEntityImport() {
        return "import org.springframework.http.ResponseEntity;";
    }

    public static String getSearchDtoImport() {
        return "import com.mds.merv.specification.SearchDTO;";
    }

    public static String getSecurityUtilsImport() {
        return "import com.mds.merv.utils.SecurityUtils;";
    }

    public static String getSpringNoRepositoryBeanImport() {
        return "import org.springframework.data.repository.NoRepositoryBean;";
    }

    public static String getSpringDataDomainImport() {
        return "import org.springframework.data.domain.*;";
    }

    public static String getSpringDataJpaDomainImport() {
        return "import org.springframework.data.jpa.domain.*;";
    }

    public static String getLombokDataImport() {
        return "import lombok.Data;";
    }

    public static String getLombokAllArgsConstructorImport() {
        return "import lombok.AllArgsConstructor;";
    }

    public static String getLombokNoArgsConstructorImport() {
        return "import lombok.NoArgsConstructor;";
    }

    public static String getJacksonJsonIgnoreImport() {
        return "import com.fasterxml.jackson.annotation.JsonIgnore;";
    }

    public static String getGrantedAuthorityImport() {
        return "import org.springframework.security.core.GrantedAuthority;";
    }

    public static String getSpringWebImport() {
        return "import org.springframework.web.bind.annotation.*;";
    }

    public static String getSpringContextAnnotationImport() {
        return "import org.springframework.context.annotation.*;";
    }

    public static String getSwaggerSecurityRequirementImport() {
        return "import io.swagger.v3.oas.annotations.security.SecurityRequirement;";
    }

    public static String getSpringServiceImport() {
        return "import org.springframework.stereotype.*;";
    }

    public static String getQuartzTriggerFiredBundleImport() {
        return "import org.quartz.spi.TriggerFiredBundle;";
    }

    public static String getSpringAutowiredImport() {
        return "import org.springframework.beans.factory.annotation.Autowired;";
    }

    public static String getSpringAutowireCapableBeanFactoryImport() {
        return "import org.springframework.beans.factory.config.AutowireCapableBeanFactory;";
    }

    public static String getSpringApplicationContextImport() {
        return "import org.springframework.context.ApplicationContext;";
    }

    public static String getSpringApplicationContextAwareImport() {
        return "import org.springframework.context.ApplicationContextAware;";
    }

    public static String getSpringBeanJobFactoryImport() {
        return "import org.springframework.scheduling.quartz.SpringBeanJobFactory;";
    }

    public static String getJavaIoIOExceptionImport() {
        return "import java.io.IOException;";
    }

    public static String getApacheCommonsArrayUtilsImport() {
        return "import org.apache.commons.lang3.ArrayUtils;";
    }

    public static String getSpringPropertiesFactoryBeanImport() {
        return "import org.springframework.beans.factory.config.PropertiesFactoryBean;";
    }

    public static String getSpringCoreClassPathResourceImport() {
        return "import org.springframework.core.io.ClassPathResource;";
    }

    public static String getSpringQuartzSchedulerFactoryBeanImport() {
        return "import org.springframework.scheduling.quartz.SchedulerFactoryBean;";
    }

    public static String getSpringSecurityUsernamePasswordAuthenticationTokenImport() {
        return "import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;";
    }

    public static String getSpringSecurityContextHolderImport() {
        return "import org.springframework.security.core.context.SecurityContextHolder;";
    }

    public static String getSpringSecurityUserDetailsImport() {
        return "import org.springframework.security.core.userdetails.UserDetails;";
    }

    public static String getSpringSecurityWebAuthenticationDetailsSourceImport() {
        return "import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;";
    }

    public static String getSpringComponentImport() {
        return "import org.springframework.stereotype.Component;";
    }

    public static String getSpringWebFilterOncePerRequestFilterImport() {
        return "import org.springframework.web.filter.OncePerRequestFilter;";
    }

    public static String getUserServiceImport() {
        return "import " + WriterUtils.getImportDefaultPackage() + ".service.UserService;";
    }

    public static String getJwtUtilImport() {
        return "import " + WriterUtils.getImportDefaultPackage() + ".config.security.JwtUtil;";
    }

    public static String getJakartaServletFilterChainImport() {
        return "import jakarta.servlet.FilterChain;";
    }

    public static String getJakartaServletServletExceptionImport() {
        return "import jakarta.servlet.ServletException;";
    }

    public static String getJakartaServletHttpServletRequestImport() {
        return "import jakarta.servlet.http.HttpServletRequest;";
    }

    public static String getJakartaServletHttpServletResponseImport() {
        return "import jakarta.servlet.http.HttpServletResponse;";
    }
}
