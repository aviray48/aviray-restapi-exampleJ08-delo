package ray.avi.common.exception;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
public class GeneralErrorObject {
	
	private String message;
	private String code;
	private String messageText;

	public GeneralErrorObject(String message){
		this.message = message;
		this.messageText = message;
	}
	
	public GeneralErrorObject(String message, String code){
		this.message = message;
		this.messageText = message;
		this.code = code;
	}
	
}
