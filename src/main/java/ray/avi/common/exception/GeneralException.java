package ray.avi.common.exception;

import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@XmlRootElement
@Getter
@Setter
public class GeneralException extends Exception {

	private String messageText;
	private String code;
	private HttpStatus httpStatus;
	
	public GeneralException(){
		
	}
	
	public GeneralException(String message){
		super(message);
		this.messageText = message;
	}
	
	public GeneralException(String message, String code){
		super(message);
		this.messageText = message;
		this.code = code;
	}
	
	public GeneralException(String message, HttpStatus httpStatus){
		super(message);
		this.messageText = message;
		this.httpStatus = httpStatus;
	}
	
	public GeneralException(String message, String code, HttpStatus httpStatus){
		super(message);
		this.messageText = message;
		this.code = code;
		this.httpStatus = httpStatus;
	}
	

}
