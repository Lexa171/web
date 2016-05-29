package com.epam.spring.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageProperties {

	public MessageProperties() {
	}

	@Autowired
	@Qualifier("messageSource")
	private MessageSource messageSource;

	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}

}
