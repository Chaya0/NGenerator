package com.generator.validator;

public interface Validator<T> {
	 /**
     * Validates the given object.
     *
     * @param object The object to validate.
     * Throws Exception if validation fails, otherwise nothing happens.
     */
	void validate(T Object) throws Exception;
}
