package com.generator.writer.frontend.angular.utils;

public enum DefaultLocalizationType {

	RETURN_HOME("return_home", "Return to dashboard"),
	NEW("new", "New item"),
	UPLOAD("upload", "Upload"),
	SEARCH("search", "Search"),
	DASHBOARD("dashboard", "Dashboard"),
	ADMINISTRATION("administration", "Administration"),
	PROFILE("profile", "Profile"),
	CREATE("create", "Create"),
	UPDATE("update", "Update"),
	ROLES("roles", "Roles"),
	PERMISSIONS("permissions", "Permissions"),
	DATE("date", "Date"),
	ACTION("action", "Actions"),
	CLEAR("clear", "Clear"),
	SAVE("save", "Save"),
	CANCEL("cancel", "Cancel"),
	TOTAL("total", "Total"),
	SUCCESS("success", "Success"),
	ERROR("error", "Error"),
	INFO("info", "Info"),
	WARNING("warning", "Warning"),
	SETTINGS("settings", "Settings"),
	LANGUAGE("language", "Language"),
	MANAGE_PROFILE("manage_profile", "Profile"),
	LOGOUT("logout", "Sign out"),
	SIGN_IN("signIn", "Sign in"),
	EDIT("edit", "Edit"),
	DELETE("delete", "Delete"),
	DOWNLOAD_PDF("download_pdf", "Download PDF"),
	BACK("back", "Back"),
	NEXT("next", "Next"),
	RANGE("range", "Range"),
	NO_DATA("noData", "No data"),
	USERNAME("username", "Username"),
	PASSWORD("password", "Password"),
	CONFIRM_PASSWORD("confirm_password", "Confirm Password"),
	OVERVIEW("overview", "Overview"),
	CONFIRMATION("confirmation", "Confirmation"),
	YES("yes", "Yes"),
	NO("no", "No"),
	TIME("time", "Time"),
	SEARCH_BY("search_by", "Search by"),
	AVAILABLE("available", "Available"),
	ADDED("added", "Added"),
	PASSWORD_STRENGTH("password_strength", "Password strength"),
	TOO_SIMPLE("too_simple", "Too simple"),
	AVERAGE_COMPLEXITY("average_complexity", "Average complexity"),
	COMPLEX_PASSWORD("complex_password", "Complex password"),
	CHOOSE_A_PASSWORD("choose_a_password", "Choose a password"),
	SUGGESTIONS("suggestions", "Suggestions"),
	AT_LEAST_ONE_LOWERCASE("at_least_one_lowercase", "At least one lowercase"),
	AT_LEAST_ONE_UPPERCASE("at_least_one_uppercase", "At least one uppercase"),
	AT_LEAST_ONE_NUMERIC("at_least_one_numeric", "At least one numeric"),
	MINIMUM_8_CHARACTERS("minimum_8_characters", "Minimum 8 characters"),
	DATE_JOINED("date_joined", "Date joined"),
	LAST_EDITED("last_edited", "Last updated"),
	USERS("users", "Users"),
	LOADING_DATA("loadingData", "Loading data. Please wait."),
	SCALE("scale", "Scale"),
	MENU_TYPE("menu_type", "Menu type"),
	INPUT_TYPE("input_type", "Input type"),
	DARK_MODE("dark_mode", "Dark mode"),
	STATIC("static", "Static"),
	OVERLAY("overlay", "Overlay"),
	OUTLINED("outlined", "Outlined"),
	FILLED("filled", "Filled"),
	IMPORT("import", "Import"),
	TODAY("today", "Today"),
	CREATED_SUCCESSFULLY("created_successfully", "Created successfully!"),
	UPDATED_SUCCESSFULLY("updated_successfully", "Updated successfully!"),
	DELETED_SUCCESSFULLY("deleted_successfully", "Deleted successfully!"),
	CHOOSE_DATE_RANGE_ERROR("choose_date_range_error", "Please choose a date range!"),
	ARE_YOU_SURE_DELETE("are_you_sure_delete", "Are you sure that you want to delete ${}?"),
	CHANGE_PASSWORD("change_password", "Change password"),
	PASSWORD_CHANGE("password_change", "Password change"),
	EXPIRED_SESSION("expired_session", "Your session has expired. Please log in again."),
	ACCESS_DENIED("access_denied", "You do not have permission to access this resource."),
	UNIQUE_VALUE_CONSTRAINT("unique_value_constraint", "must be unique. The entered value already exists."),
	PAGE_NOT_FOUND("page_not_found", "The page you’re looking for can’t be found."),
	BAD_CREDENTIALS("bad_credentials", "Invalid username or password.");

	private String localizationKey;
	private String localizationValue;

	private DefaultLocalizationType(String localizationKey, String localizationValue) {
		this.localizationKey = localizationKey;
		this.localizationValue = localizationValue;
	}

	public String getLocalizationKey() {
		return localizationKey;
	}

	public String getLocalizationValue() {
		return localizationValue;
	}
	
	public String getJsonLocalizationValue() {
		return "\"" + localizationKey + "\": " + "\"" + localizationValue + "\",";
	}
}
