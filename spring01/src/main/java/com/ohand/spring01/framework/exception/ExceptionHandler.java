package com.ohand.spring01.framework.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohand.spring01.framework.Configuration;
import com.ohand.spring01.framework.response.BaseResponse;

@Component("exceptionResolver")
public class ExceptionHandler extends DefaultHandlerExceptionResolver {

	private static Log log = LogFactory.getLog(ExceptionHandler.class);

	@Value("${exception.response.filter:false}")
	private boolean filterErrorMsg;
	@Value("${exception.response.messasge:errors occurs}")
	private String defaultErrorMsg;
	@Value("${exception.response.contentType:application/json}")
	private String contentType;

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		log.warn("<<< exception occurs >>>", ex);

		ObjectMapper mapper = new ObjectMapper();
		
		mapper.setSerializationInclusion(Include.NON_NULL);

		BaseResponse responseBean = new BaseResponse(Configuration.Status.STATUS_FAIL,
				filterErrorMsg ? defaultErrorMsg : ex.getMessage());
		try {
			String message = mapper.writeValueAsString(responseBean);
			response.reset();
			response.setContentType(contentType);
			response.getOutputStream().write(message.getBytes());
			response.getOutputStream().flush();
		} catch (Exception e) {
			log.error(e);
		}
		return new ModelAndView();
	}

}
