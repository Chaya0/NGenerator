package com.generator.reader;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.generator.model.AppModel;
import com.generator.validator.Validator;
import com.generator.validator.model.AppModelValidator;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

public class XMLModelReader {
	private static final Logger logger = LogManager.getLogger(XMLModelReader.class);
	public static AppModel readModel() {
		try {
			logger.info("Starting model reading...");
			JAXBContext jaxbContext = JAXBContext.newInstance(AppModel.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File model = new File("./model.xml");
			AppModel appModel = (AppModel) jaxbUnmarshaller.unmarshal(model);
			Validator<AppModel> validator = new AppModelValidator();
			validator.validate(appModel);
			logger.info("Model read successfuly");
			return appModel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
