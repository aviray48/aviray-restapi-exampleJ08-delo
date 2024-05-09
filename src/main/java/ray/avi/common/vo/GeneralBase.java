package ray.avi.common.vo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class GeneralBase implements Serializable {

	private long id;
	private long generalId;
	private String generalFlag;
	private String generalMessage;
	private String operationalCountryCode;

}
