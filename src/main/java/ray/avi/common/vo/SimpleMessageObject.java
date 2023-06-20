package ray.avi.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleMessageObject {

	private boolean result;
	private String testName;
	private String exceptionMessage;
	private String additionalInfo;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalInfo == null) ? 0 : additionalInfo.hashCode());
		result = prime * result + ((exceptionMessage == null) ? 0 : exceptionMessage.hashCode());
		result = prime * result + (this.result ? 1231 : 1237);
		result = prime * result + ((testName == null) ? 0 : testName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SimpleMessageObject)) {
			return false;
		}
		SimpleMessageObject other = (SimpleMessageObject) obj;
		if (additionalInfo == null) {
			if (other.additionalInfo != null) {
				return false;
			}
		} else if (!additionalInfo.equals(other.additionalInfo)) {
			return false;
		}
		if (exceptionMessage == null) {
			if (other.exceptionMessage != null) {
				return false;
			}
		} else if (!exceptionMessage.equals(other.exceptionMessage)) {
			return false;
		}
		if (result != other.result) {
			return false;
		}
		if (testName == null) {
			if (other.testName != null) {
				return false;
			}
		} else if (!testName.equals(other.testName)) {
			return false;
		}
		return true;
	}
	
}
