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
    private GeneralBase generalBase;
    
    public GeneralErrorResponseObject(){}
    
	public GeneralErrorResponseObject(int code, String message, GeneralBase generalBase) {
        this.code = code;
        this.message = message;
        this.generalBase = generalBase;
    }
	
	public GeneralErrorResponseObject(GeneralErrorResponseObject generalErrorResponseObject) {
		this(generalErrorResponseObject.getCode(),  generalErrorResponseObject.getMessage(), generalErrorResponseObject.getGeneralBase());
    }

	
	public GeneralErrorResponseObject(int code, String message) {
        this(code, message, null);
    }

}
