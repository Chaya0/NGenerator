package com.generator.writer.frontend.angular.model;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class AngularStructureWriter {
	public void create() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath(), "structure.ts", true)) {
			file.writeln(0, "import {Attribute} from '../../core/entity-utils/attribute';");
			file.breakLine();
			file.writeln(0, "export interface Structure {");
			file.writeln(1, "entityName: string;");
			file.writeln(1, "title: string;");
			file.writeln(1, "fkSearchAttribute: string[];");
			file.writeln(1, "attributes: Attribute[];");
			file.writeln(0, "}");
		}
	}
}
