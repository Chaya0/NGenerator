package com.generator.writer.frontend.angular;

public enum AngularBashCommand {
	GENERATE_APP_SHEL("ng g app-shell "),
	GENERATE_APPLICATION("ng g app "),
	GENERATE_CLASS("ng g class "),
	GENERATE_COMPONENT("ng g c "),
	GENERATE_CONFIG("ng g config "),
	GENERATE_DIRECTIVE("ng g d "),
	GENERATE_ENUM("ng g e "),
	GENERATE_ENVIRONMENTS("ng g environments "),
	GENERATE_GUARD("ng g g "),
	GENERATE_INTERCEPTOR("ng g interceptor "),
	GENERATE_INTERFACE("ng g i "),
	GENERATE_LIBRARY("ng g lib "),
	GENERATE_MODULE("ng g m "),
	GENERATE_PIPE("ng g p "),
	GENERATE_RESOLVER("ng g r "),
	GENERATE_SERVICE("ng g s "),
	GENERATE_SERVICE_WORKER("ng g service-worker "),
	GENERATE_WEB_WORKER("ng g web-worker "),
	NEW_APP("ng new ");
	
	private String command;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	private AngularBashCommand(String command) {
		this.command = command;
	}
	
}
