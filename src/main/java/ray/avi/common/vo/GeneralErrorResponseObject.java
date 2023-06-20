package ray.avi.common.vo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
@SuppressWarnings("serial")
@Getter
@Setter
public class GeneralErrorResponseObject implements Serializable {

	private int code;
	private String message;
    private String generalNumber;
    private GeneralBase generalBase;
    
    public GeneralErrorResponseObject(){}
    
	public GeneralErrorResponseObject(int code, String message, String generalNumber, GeneralBase generalBase) {
        this.code = code;
        this.message = message;
        this.generalNumber = generalNumber;
        this.generalBase = generalBase;
    }
	
	public GeneralErrorResponseObject(GeneralErrorResponseObject generalErrorResponseObject) {
		this(generalErrorResponseObject.getCode(),  generalErrorResponseObject.getMessage(), generalErrorResponseObject.getGeneralNumber(), generalErrorResponseObject.getGeneralBase());
    }

	
	public GeneralErrorResponseObject(int code, String message, String generalNumber) {
        this(code, message, generalNumber, null);
    }
	
	public GeneralErrorResponseObject(int code, String message) {
        this(code, message, null);
    }

}
