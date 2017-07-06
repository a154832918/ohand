package com.ohand.spring01.common;

import java.util.UUID;

public class UUIDUtil {

	public static final String generateUUID() {
		return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
	}

}
