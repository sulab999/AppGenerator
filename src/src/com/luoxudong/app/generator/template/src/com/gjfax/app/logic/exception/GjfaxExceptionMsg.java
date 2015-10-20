/**
 * Title: GjfaxExceptionMsg.java
 * Description: 
 * Copyright: Copyright (c) 2013 luoxudong.com
 * Company: 深圳市彩讯科技有限公司
 * Author: 罗旭东
 * Date: 2015年3月16日 上午11:33:07
 * Version: 1.0
 */
package com.gjfax.app.logic.exception;

/** 
 * ClassName: GjfaxExceptionMsg
 * Description:TODO(这里用一句话描述这个类的作用)
 * Create by: 
 * Date: 2015年3月16日 上午11:33:07
 */
public enum GjfaxExceptionMsg {
	success("成功"),
	unknown("未知异常"),
	userExist("用户已存在");
	
	private GjfaxExceptionMsg(String errorMsg)
	{
		mErrorMsg = errorMsg;
	}
	
	/** 错误信息 */
	private String mErrorMsg = null;

	public String getmErrorMsg() {
		return mErrorMsg;
	}

	public void setmErrorMsg(String mErrorMsg) {
		this.mErrorMsg = mErrorMsg;
	}

}
