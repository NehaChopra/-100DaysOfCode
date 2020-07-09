package Delphix;

/*
 * HTTPS CODES ENUM
 * SUCCESS - 200 - OK
 * NO_CONTENT - 204
 * BAD_REQUEST - 400 
 * NOT_FOUND - 404
 * INTERNAL_SERVER_ERROR - 500
 * SERVICE_UNAVAILABLE - 503
 * BAD_GATEWAY - 502
 * 
 */
public enum HttpCodes {
	SUCCESS("SUCCESS", 200), NO_CONTENT("NO_CONTENT", 204), BAD_REQUEST("BAD_REQUEST", 400),
	NOT_FOUND("NOT_FOUND", 404), INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", 500),
	SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE", 503), BAD_GATEWAY("BAD_GATEWAY", 502),;

	String value;
	int id;

	HttpCodes(String value, int id) {
		this.value = value;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public static HttpCodes getHttpCodes(String entityType) {
		for (HttpCodes e : values()) {
			if (e.getValue().equalsIgnoreCase(entityType)) {
				return e;
			}
		}
		return null;
	}

	public static HttpCodes getHttpCodes(int id) {
		for (HttpCodes e : values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}
}
