package ray.avi.common.exception;

import org.springframework.http.HttpStatus;

public class ApiError {

    private HttpStatus status;
    private String defaultMessage;
    private String[] errors;
	public static final String GENERIC_ERROR = "88888888";

    public ApiError() {
    }
    
    public ApiError(HttpStatus status, String defaultMessage, String[] errors) {
        super();
        this.status = status;
        this.defaultMessage = defaultMessage;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String defaultMessage, String error) {
        super();
        this.status = status;
        this.defaultMessage = defaultMessage;
        this.errors = new String[1];
        this.errors[0]=error;
    }

	public ApiError(HttpStatus status, String defaultMessage) {
		super();
		this.status = status;
		this.defaultMessage = defaultMessage;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	public String[] getErrors() {
		return errors;
	}

	public void setErrors(String[] errors) {
		this.errors = errors;
	}
}