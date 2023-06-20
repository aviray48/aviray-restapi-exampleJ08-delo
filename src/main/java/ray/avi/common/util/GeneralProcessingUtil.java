package ray.avi.common.util;

import java.text.MessageFormat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneralProcessingUtil {

	private GeneralProcessingUtil(){}
	
	public static void logIsErrorEnabledWrapper(String template, Object... vals ) {
		if(log.isErrorEnabled()){
			log.error(MessageFormat.format(template, vals));
		}
	}

	public static void logIsErrorEnabledWrapper(Throwable t, String template, Object... vals ) {
		if(log.isErrorEnabled()){
			log.error(MessageFormat.format(template, vals), t);
		}
	}
	
}
