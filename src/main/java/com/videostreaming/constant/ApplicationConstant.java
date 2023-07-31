package com.videostreaming.constant;

import org.springframework.http.HttpStatus;

public class ApplicationConstant {
	public static final int SUCCESS_CODE_200 = HttpStatus.OK.value();
	public static final String SUCCESS_GET = "success.message";
	public static final int SUCCESS_CODE_201 = HttpStatus.CREATED.value();
	public static final int ERROR_CODE_400 = HttpStatus.BAD_REQUEST.value();
	public static final int ERROR_CODE_404 = HttpStatus.NOT_FOUND.value();
	public static final int ERROR_CODE_500 = HttpStatus.INTERNAL_SERVER_ERROR.value();
	public static final int ERROR_CODE_409 = HttpStatus.CONFLICT.value();

}
