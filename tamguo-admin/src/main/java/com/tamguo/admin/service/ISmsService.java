package com.tamguo.admin.service;

import com.aliyuncs.exceptions.ClientException;
import com.tamguo.admin.util.Result;

public interface ISmsService {

	public Result sendFindPasswordSms(String mobile) throws ClientException;
	
	public Result sendPassJoinusSms(String mobile) throws ClientException;
	
}
