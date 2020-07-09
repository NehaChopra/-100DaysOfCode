package Delphix;

/*
 * BART exception description and codes 
 */
public enum BartExceptionCodes implements ExceptionCodes {
	INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"), PARSING_ERROR(501, "PARSING_ERROR"),;

	private Integer code;
	private String message;

	BartExceptionCodes(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}