package com.blog.util;

import java.util.UUID;

public class GetUuid {

	public static String get(){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		
		return uuid;
	}
	
	
}
