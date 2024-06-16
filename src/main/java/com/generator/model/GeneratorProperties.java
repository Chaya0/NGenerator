package com.generator.model;

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
	@XmlAttribute(name = "name", required = true)
	private boolean generateSwagger;
	@XmlAttribute(name = "name", required = true)
	private boolean generateAuthorisation;
	@XmlAttribute(name = "name", required = true)
	private boolean generateQuartz;
	@XmlAttribute(name = "name", required = true)
	private boolean generateFrontend;
	@XmlAttribute(name = "generationType")
	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private DatabaseType databaseType;
	@XmlAttribute(name = "generationType")
	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private AuthorisationType authorisationType;
	@XmlAttribute(name = "generationType")
	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private FrontendType frontendType;
	@XmlAttribute(name = "generationType")
	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private ModelFileType modelFileType;
	@XmlAttribute(name = "generationType")
	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private ApplicationStructureType applicationStructureType;
}
