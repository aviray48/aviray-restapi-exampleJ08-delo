package ray.avi.common.security;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import ray.avi.common.exception.ApiError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String BASIC_AUTHORIZATION_HEADER_PREFIX = "Basic ";

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException ex) throws IOException, ServletException {

		String authHeader = request.getHeader(AUTHORIZATION_HEADER_KEY);
		if(!authHeader.isEmpty()) {
			String authToken = authHeader.replaceFirst(BASIC_AUTHORIZATION_HEADER_PREFIX, "");
			String decodedAuthToken = new String(Base64Utils.decodeFromString(authToken));
			String username = decodedAuthToken.split(":")[0];
			log.error("User Failed to Authenticate with username [{}]", username);

			String errorMessage = ex.getMessage() == null ? ex.getClass().getName() : ex.getMessage();
		    ApiError apiError = new ApiError( HttpStatus.UNAUTHORIZED, "Error occurred", errorMessage);

		    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			OutputStream out = response.getOutputStream();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, apiError);
			out.flush();
		}
	}
}