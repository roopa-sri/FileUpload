package com.videostreaming.util;

import java.util.Locale;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class CommonUtil {

	private CommonUtil() {
	}

	public static String populateMessage(String message) {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/i18n/messages");
		MessageSourceAccessor accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
		return accessor.getMessage(message);
	}

}
