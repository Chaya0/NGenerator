package com.generator.model.properties;

import com.generator.model.enums.FrontendType;
import com.generator.model.enums.properties.ApplicationStructureType;
import com.generator.model.enums.properties.AuthenticationType;
import com.generator.model.enums.properties.DatabaseType;
import com.generator.model.enums.properties.ModelFileType;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GeneratorProperties {
//	@XmlAttribute(name = "name", required = true)
	private boolean generateSwaggerComponent = false;
//	@XmlAttribute(name = "name", required = true)
	private boolean generateAuthorisationComponents = false;
//	@XmlAttribute(name = "name", required = true)
	/**
	 * Only works if {@link generateAuthorisationComponents} is true.
	 */
	private boolean generatePermissionsOnEndpoints = false;
	
	private boolean generatePermissionsAndRoles = false;
//	@XmlAttribute(name = "name", required = true)
	private boolean generateQuartzComponents = false;
//	@XmlAttribute(name = "name", required = true)
	private boolean generateFrontend = false;
//	@XmlAttribute(name = "generationType")
//	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private DatabaseType databaseType = DatabaseType.POSTGRES;
//	@XmlAttribute(name = "generationType")
//	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private AuthenticationType authenticationType = AuthenticationType.JWT;
//	@XmlAttribute(name = "generationType")
//	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private FrontendType frontendType = FrontendType.ANGULAR;
//	@XmlAttribute(name = "generationType")
//	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private ModelFileType modelFileType = ModelFileType.XML;
//	@XmlAttribute(name = "generationType")
//	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private ApplicationStructureType applicationStructureType = ApplicationStructureType.MONOLITHIC;
	
	private boolean generateBasicRepository = true;
	private boolean generateBasicService = true;
	private boolean generateBasicController = true;
	private boolean generateGenericRepository = true;
	private boolean generateGenericService = true;
	private boolean generateGenericController = true;
	private boolean generateGenericEntity = true;
	private boolean generateGenericEnums = true;
	private boolean generateDTOs = true;
	private boolean generateUserFrontSettings = true;
	
	
	public boolean generatePermissionsOnEndpoints() {
		return generateAuthorisationComponents && generatePermissionsAndRoles && generatePermissionsOnEndpoints;
	}
}
