package com.generator.reader;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generator.model.AppModel;
import com.generator.validator.Validator;
import com.generator.validator.model.AppModelValidator;

public class JSONModelReader {

	public static AppModel readModel() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			File model = new File("./model.json");
			AppModel appModel = objectMapper.readValue(model, AppModel.class);
			Validator<AppModel> validator = new AppModelValidator();
			validator.validate(appModel);
			return appModel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
