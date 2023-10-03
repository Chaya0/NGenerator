package com.generator.reader;

import java.io.File;

import com.generator.model.AppModel;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

public class ModelReader {

	public static AppModel readModel() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AppModel.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File model = new File("./model.xml");
			AppModel appModel = (AppModel) jaxbUnmarshaller.unmarshal(model);
			Validator.validateModel(appModel);
			return appModel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
