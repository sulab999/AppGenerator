/**
 * Title: GjfaxExceptionCode.java
 * Description:
 * Copyright: Copyright (c) 2013 luoxudong.com
 * Company:个人
 * Author 罗旭东 (hi@luoxudong.com)
 * Date 2013-8-5 下午12:00:09
 * Version 1.0 
 */
package com.gjfax.app.logic.exception; 	



/** 
 * ClassName: GjfaxExceptionCode 
 * Description:自定义异常错误码
 * Create by 罗旭东
 * Date 2013-8-5 下午12:00:09 
 */
public enum GjfaxExceptionCode {
	success(0),//正常情况
	unknown(1),//未知错误
	userExist(2),//用户已存在
	sessionTimeOut(3),//登录超时
	serviceError(4),//服务器端错误
	loginPwdError(5);//登录密码不正确
	
	private GjfaxExceptionCode(int errorCode)
	{
		mErrorCode = errorCode;
	}
	
	/** 错误码 */
	private int mErrorCode = -1;

	public int getErrorCode() {
		return mErrorCode;
	}

	public void setErrorCode(int errorCode) {
		mErrorCode = errorCode;
	}
	
}
