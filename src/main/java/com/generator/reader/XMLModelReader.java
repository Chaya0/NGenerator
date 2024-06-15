package com.generator.reader;

import java.io.File;

import com.generator.model.AppModel;
import com.generator.validator.Validator;
import com.generator.validator.model.AppModelValidator;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

public class XMLModelReader {

	public static AppModel readModel() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AppModel.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File model = new File("./model.xml");
			AppModel appModel = (AppModel) jaxbUnmarshaller.unmarshal(model);
			Validator<AppModel> validator = new AppModelValidator();
			validator.validate(appModel);
			return appModel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
