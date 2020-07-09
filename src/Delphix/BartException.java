package Delphix;

import java.util.HashMap;
import java.util.Map;

/*
 * BartException - Exception information
 * Detail exception information
 */
public class BartException extends RuntimeException {
	private static final long serialVersionUID = -5617325434197351058L;
	private Integer errorCode;
	private String message;

	private Map<Integer, String> errors = new HashMap<Integer, String>(0);

	public BartException() {
		super();
	}

	public BartException(Integer code, String message) {
		super(code.toString().concat(" : " + message));
		this.errorCode = code;
		this.message = message;
		errors.put(code, message);
	}

	public BartException(ExceptionCodes exception) {
		super(exception.getCode().toString().concat(" : " + exception.getMessage()));
		errors.put(exception.getCode(), exception.getMessage());
	}

	public void addError(Integer code, String message) {
		errors.put(code, message);
	}

	public void addError(ExceptionCodes exception) {
		errors.put(exception.getCode(), exception.getMessage());
	}

	public Map<Integer, String> getErrors() {
		return errors;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setErrors(Map<Integer, String> errors) {
		this.errors = errors;
	}

}
