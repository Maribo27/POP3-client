package by.maribo.pop3client.util;

import static by.maribo.pop3client.controller.StringConstants.INVALID_INPUT_DATA;

public final class Validator {
	private static final String DIGIT = "[1-9]\\d*";

	private Validator() {
	}

	public static void validateCommandWithoutParameters(String command) throws InvalidInputException {
		if (command != null && command.length() != 0) {
			throw new InvalidInputException(INVALID_INPUT_DATA + "input parameters aren't supported");
		}
	}

	public static void validateDigitParameter(String command) throws InvalidInputException {
		if (command == null || command.length() == 0) {
			throw new InvalidInputException(INVALID_INPUT_DATA + "input parameters are required");
		}
		if (!command.matches(DIGIT)) {
			throw new InvalidInputException(INVALID_INPUT_DATA + "integer number are required");
		}
	}

	public static void validateDigitParameters(String command) throws InvalidInputException {
		if (command == null || command.length() == 0) {
			throw new InvalidInputException(INVALID_INPUT_DATA + "input parameters are required");
		}
		String [] parameters = command.split(" ", 2);
		if (parameters.length != 2) {
			throw new InvalidInputException(INVALID_INPUT_DATA + "2 integer numbers are required");
		}
		for (String parameter : parameters) {
			if (parameter == null || parameter.length() == 0 || !parameter.matches(DIGIT)) {
				throw new InvalidInputException(INVALID_INPUT_DATA + "integer number are required");
			}
		}
	}

	public static void validateCommandWithParameter(String command) throws InvalidInputException {
		if (command == null || command.length() == 0) {
			throw new InvalidInputException(INVALID_INPUT_DATA + "input parameters are required");
		}
	}
}