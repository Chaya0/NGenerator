package com.generator.model;

import com.generator.model.enums.ApplicationStructureType;
import com.generator.model.enums.AuthorisationType;
import com.generator.model.enums.DatabaseType;
import com.generator.model.enums.FrontendType;
import com.generator.model.enums.ModelFileType;

import lombok.Data;

@Data
public class GeneratorProperties {
	private boolean generateSwagger;
	private boolean generateAuthorisation;
	private boolean generateQuartz;
	private boolean generateFrontend;
	private DatabaseType databaseType;
	private AuthorisationType authorisationType;
	private FrontendType frontendType;
	private ModelFileType modelFileType;
	private ApplicationStructureType applicationStructureType;
}
