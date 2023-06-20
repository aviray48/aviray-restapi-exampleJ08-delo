package ray.avi.common.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ray.avi.common.exception.ApiError;
import org.springframework.http.HttpHeaders;

@SuppressWarnings("unchecked")
public class UtilMethods {

	private UtilMethods(){}
	
	private static Logger logger = LoggerFactory.getLogger(UtilMethods.class.getName());
	public static final String AUTHORIZATION = "Authorization";
	protected static final ObjectMapper objectMapper = new ObjectMapper();
	protected static final XmlMapper xmlMapper = new XmlMapper();

	protected static final ObjectMapper objectMapperIgnoreUnknownProperties = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	private static XmlMapper newXmlMapperAllowingUnknownProperties() {
		final JacksonXmlModule module = new JacksonXmlModule();
		module.setDefaultUseWrapper(false);
		XmlMapper mapper = new XmlMapper(module);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}

	protected static final XmlMapper xmlMapperIgnoreUnknownProperties = newXmlMapperAllowingUnknownProperties();

	public static String getMethodName(){
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	public static Method getClassMethod(Class<?> clazz, String methodName){
		Method classMethod = null;
		Method[] allMethods = clazz.getDeclaredMethods();
		for (Method m : allMethods) {
			if(m.getName().equals(methodName)){
				classMethod = m;
				break;
			}
		}
		return classMethod;
	}

	public static String generateShortErrorMessage(Throwable b){
		return b.getClass().getName() + (b.getMessage() != null ? ": " + b.getMessage().trim() : "");
	}

	public static HttpHeaders removeAuthHeader(HttpHeaders headers) {
		headers.set(AUTHORIZATION, null);
		return headers;
	}

	public static <T> T copyViaString(T model, Class<T> tClass) throws IOException, JsonProcessingException {
		return JSONStringToObject(objectMapper.writeValueAsString(model), tClass);
	}

	public static <T> T copyViaStringNoExceptions(Object... oba) {
		try {
			return copyViaString((T)oba[0], (Class<T>)oba[1]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static <T> T JSONStringToObject(String jsonString, Class<T> tClass) throws IOException, JsonProcessingException {
		return objectMapper.readValue(jsonString, tClass);
	} 

	public static <T> T JSONStringToObjectNoExceptions(Object... oba) {
		try {
			return JSONStringToObject((String)oba[0], (Class<T>)oba[1]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static <T> T convertObjectToAnotherType(Object model, Class<T> tClass) throws IOException, JsonProcessingException {
		return JSONStringToObject(UtilMethods.ObjectToJSONString(model), tClass);
	}

	public static <T> T convertObjectToAnotherTypeNoExceptions(Object... oba) {
		try {
			return convertObjectToAnotherType(oba[0], (Class<T>)oba[1]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static String ObjectToJSONString(Object model) throws JsonProcessingException {
		return objectMapper.writeValueAsString(model);
	}

	public static String ObjectToJSONStringNoExceptions(Object... oba) {
		try {
			return ObjectToJSONString(oba[0]);
		} catch (JsonProcessingException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static String ObjectToXMLString(Object model) throws JsonProcessingException {
		return xmlMapper.writeValueAsString(model);
	}

	public static String ObjectToXMLStringNoExceptions(Object... oba) {
		try {
			return ObjectToXMLString(oba[0]);
		} catch (JsonProcessingException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static <T> T XMLStringToObject(String xmlString, Class<T> tClass) throws IOException, JsonProcessingException {
		return xmlMapper.readValue(xmlString, tClass);
	} 

	public static <T> T XMLStringToObjectNoExceptions(Object... oba) {
		try {
			return XMLStringToObject((String)oba[0], (Class<T>)oba[1]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static <T> T XMLStringToObjectIgnoreUnknownProperties(String jsonString, Class<T> tClass) throws IOException, JsonProcessingException {
		return xmlMapperIgnoreUnknownProperties.readValue(jsonString, tClass);
	}

	public static <T> T XMLStringToObjectIgnoreUnknownPropertiesNoExceptions(Object... oba) {
		try {
			return XMLStringToObjectIgnoreUnknownProperties((String)oba[0], (Class<T>)oba[1]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static <T> T convertObjectToAnotherTypeWithXML(Object model, Class<T> tClass) throws IOException, JsonProcessingException {
		return XMLStringToObject(UtilMethods.ObjectToXMLString(model), tClass);
	}

	public static <T> T convertObjectToAnotherTypeWithXMLNoExceptions(Object... oba) {
		try {
			return convertObjectToAnotherTypeWithXML(oba[0], (Class<T>)oba[1]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static <T> T convertObjectToAnotherTypeWithXMLIgnoreUnknownProperties(Object model, Class<T> tClass) throws IOException, JsonProcessingException {
		return XMLStringToObjectIgnoreUnknownProperties(UtilMethods.ObjectToXMLString(model), tClass);
	}

	public static <T> T convertObjectToAnotherTypeWithXMLIgnoreUnknownPropertiesNoExceptions(Object... oba) {
		try {
			return convertObjectToAnotherTypeWithXMLIgnoreUnknownProperties(oba[0], (Class<T>)oba[1]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static Map<String, Object> JSONStringToMap(String jsonString) throws IOException {
		return objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
	}

	public static Map<String, Object> JSONStringToMapNoExceptions(Object... oba) {
		try {
			return JSONStringToMap((String)oba[0]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static Map<String, Object> ObjectToMap(Object model) throws IOException, JsonProcessingException {
		return objectMapper.readValue(objectMapper.writeValueAsString(model), new TypeReference<Map<String, Object>>(){});
	}

	public static Map<String, Object> ObjectToMapNoExceptions(Object... oba) {
		try {
			return ObjectToMap(oba[0]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static ResponseEntity<String> generateResponseEntityOK(String jsonString){
		return generateResponseEntity(jsonString, HttpStatus.OK);
	}

	public static ResponseEntity<String> generateResponseEntity(String jsonString, HttpStatus httpStatus){
		return new ResponseEntity<>(jsonString, httpStatus);
	}

	public static ResponseEntity<Object> generateResponseEntity(Object model, HttpStatus httpStatus){
		return new ResponseEntity<>(model, httpStatus);
	}

	public static <T> ResponseEntity<T> generateResponseEntity(T model, HttpStatus httpStatus, Class<T> tClass) throws IOException, JsonProcessingException{
		return new ResponseEntity<>(copyViaString(model, tClass), httpStatus);
	}

	public static <T> ResponseEntity<T> generateResponseEntityNoExceptions(Object... oba) {
		try {
			return generateResponseEntity((T)oba[0], (HttpStatus)oba[1], (Class<T>)oba[2]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static <T> ResponseEntity<T> generateResponseEntityNoExceptionsNew(Object... oba) {
		try {
			return generateResponseEntity((T)oba[0], (HttpStatus)oba[1], (Class<T>)oba[2]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}
	
	public static ResponseEntity<Object> generateResponseEntityObjectOK(Object model){
		return new ResponseEntity<>(model, HttpStatus.OK);
	}

	public static <T> T JSONStringToObjectIgnoreUnknownProperties(String jsonString, Class<T> tClass) throws IOException, JsonProcessingException {
		return objectMapperIgnoreUnknownProperties.readValue(jsonString, tClass);
	}

	public static <T> T convertObjectToAnotherTypeIgnoreUnknownProperties(Object model, Class<T> tClass) throws IOException, JsonProcessingException {
		return JSONStringToObjectIgnoreUnknownProperties(UtilMethods.ObjectToJSONString(model), tClass);
	}

	public static <T> T copyViaStringIgnoreUnknownProperties(T model, Class<T> tClass) throws IOException, JsonProcessingException {
		return JSONStringToObjectIgnoreUnknownProperties(objectMapperIgnoreUnknownProperties.writeValueAsString(model), tClass);
	}

	public static <T> ResponseEntity<T> generateResponseEntityIgnoreUnknownProperties(T model, HttpStatus httpStatus, Class<T> tClass) throws IOException, JsonProcessingException{
		return new ResponseEntity<>(copyViaStringIgnoreUnknownProperties(model, tClass), httpStatus);
	}

	public static <T> T JSONStringToObjectIgnoreUnknownPropertiesNoExceptions(Object... oba) {
		try {
			return JSONStringToObjectIgnoreUnknownProperties((String)oba[0], (Class<T>)oba[1]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static <T> T convertObjectToAnotherTypeIgnoreUnknownPropertiesNoExceptions(Object... oba) {
		try {
			return convertObjectToAnotherTypeIgnoreUnknownProperties(oba[0], (Class<T>)oba[1]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static <T> T copyViaStringIgnoreUnknownPropertiesNoExceptions(Object... oba) {
		try {
			return copyViaStringIgnoreUnknownProperties((T)oba[0], (Class<T>)oba[1]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}

	public static <T> ResponseEntity<T> generateResponseEntityNoExceptionsIgnoreUnknownProperties(Object... oba) {
		try {
			return generateResponseEntityIgnoreUnknownProperties((T)oba[0], (HttpStatus)oba[1], (Class<T>)oba[2]);
		} catch (IOException e) {
			logger.error("{}|Exception occurred while transforming Object, error is {}", getMethodName(), generateShortErrorMessage(e), e);
			return null;
		}
	}


	public static <T> T copyViaString(T model, Class<T> tClass, boolean ignoreUnknownProperties) throws IOException, JsonProcessingException {
		return ignoreUnknownProperties ? JSONStringToObject(objectMapperIgnoreUnknownProperties.writeValueAsString(model), tClass) : JSONStringToObject(objectMapper.writeValueAsString(model), tClass);
	}

	public static <T> ResponseEntity<T> generateResponseEntity(T model, HttpStatus httpStatus, Class<T> tClass, boolean ignoreUnknownProperties) throws IOException, JsonProcessingException{
		return new ResponseEntity<>(copyViaString(model, tClass, ignoreUnknownProperties), httpStatus);
	}

	public static  void copyBeanProperties(final Object source, final Object target, final String[] properties) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		final BeanWrapper trg = new BeanWrapperImpl(target);
		for (final String propertyName : properties) {
			trg.setPropertyValue(propertyName, src.getPropertyValue(propertyName));
		}
	}

	public static HttpHeaders fixHeadersViaAttemptedCopy(HttpHeaders headers){
		HttpHeaders updatedHeaders = (HttpHeaders)UtilMethods.copyViaStringNoExceptions(headers, HttpHeaders.class);
		if(updatedHeaders == null){
			updatedHeaders = headers;
		}
		updatedHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		return updatedHeaders;
	}

	public static HttpHeaders fixHeaders(HttpHeaders headers){
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		return headers;
	}

	public static HttpHeaders setMissingContentTypeHeaderToJSON(HttpHeaders headers){
		if(headers.getContentType() == null) {
			headers.set("content-type", MediaType.APPLICATION_JSON_VALUE);
		}
		return headers;
	}
	
	public static HttpHeaders setMissingContentTypeHeaderToXML(HttpHeaders headers){
		if(headers.getContentType() == null) {
			headers.set("content-type", MediaType.APPLICATION_XML_VALUE);
		}
		return headers;
	}
	
	public static HttpHeaders setMissingContentTypeHeader(HttpHeaders headers, MediaType mediaType){
		if(headers.getContentType() == null) {
			headers.set("content-type", mediaType.getType());
		}
		return headers;
	}
	
	public static String getLanguageCode(HttpHeaders headers, String defaultLanguageCode) {
		String requestLanguageCode = null;
		List<String> headerLanguageCodeList = headers.get("languageCode");
		if(headerLanguageCodeList != null && !headerLanguageCodeList.isEmpty()){
			requestLanguageCode = headerLanguageCodeList.iterator().next();
		}
		if(requestLanguageCode == null){
			requestLanguageCode = defaultLanguageCode;
		}
		return requestLanguageCode;
	}

	public static String getErrors(ApiError apiErrorFromResponse) {
		return ((apiErrorFromResponse.getErrors() != null && apiErrorFromResponse.getErrors().length > 0) ? String.join(", ", Arrays.asList(apiErrorFromResponse.getErrors())) : "");
	}

}
