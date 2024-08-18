package com.generator.model.properties;

import com.generator.model.enums.ApplicationStructureType;
import com.generator.model.enums.AuthorisationType;
import com.generator.model.enums.DatabaseType;
import com.generator.model.enums.FrontendType;
import com.generator.model.enums.ModelFileType;
import com.generator.reader.adapter.GenerationTypeXmlAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
//	@XmlAttribute(name = "name", required = true)
	private boolean generateQuartzComponents = false;
//	@XmlAttribute(name = "name", required = true)
	private boolean generateFrontend = false;
//	@XmlAttribute(name = "generationType")
//	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private DatabaseType databaseType = DatabaseType.POSTGRES;
//	@XmlAttribute(name = "generationType")
//	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private AuthorisationType authorisationType = AuthorisationType.JWT;
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
	private boolean generateGenericRepository = true;
	private boolean generateBasicService = true;
	private boolean generateGenericService = true;
	private boolean generateBasicController = true;
	private boolean generateGenericController = true;
	private boolean generateGenericEntity = true;
	private boolean generateGenericEnums = true;
	private boolean generateDTOs = true;
	
}
