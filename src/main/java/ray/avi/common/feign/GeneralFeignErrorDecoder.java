package ray.avi.common.feign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.stream.Collectors;
import ray.avi.common.exception.GeneralErrorObject;
import ray.avi.common.exception.GeneralRuntimeException;
import ray.avi.common.util.UtilMethods;
import org.springframework.http.HttpStatus;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import ray.avi.common.exception.ApiError;

@Slf4j
public class GeneralFeignErrorDecoder implements ErrorDecoder {

	private final String unknownErrorOccurredMessage = "Unknown error occurred";
	
	@Override
	public Exception decode(String methodKey, Response response) {
		HttpStatus httpStatus = HttpStatus.valueOf(response.status());
		String jsonString = null;
		if(response.body() != null) {
			try {
				jsonString = new BufferedReader(new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
			} catch (IOException e) {
				log.error(MessageFormat.format("{0}|Exception occured while converting feign response to jsonString: {1}", UtilMethods.getMethodName(), e.getMessage()), e);
			}
		}
		GeneralErrorObject geneo = jsonString != null ? UtilMethods.JSONStringToObjectIgnoreUnknownPropertiesNoExceptions(jsonString, GeneralErrorObject.class) : null;
		if(geneo != null) {
			return new GeneralRuntimeException(geneo.getMessage(), geneo.getCode(), httpStatus);
		} else {
			return new GeneralRuntimeException(unknownErrorOccurredMessage, ApiError.GENERIC_ERROR, httpStatus);
		}
	}

}