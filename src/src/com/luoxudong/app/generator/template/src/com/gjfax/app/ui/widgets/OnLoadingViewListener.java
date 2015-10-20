/**
 * Title: OnLoadingViewListener.java
 * Description: 
 * Copyright: Copyright (c) 2013-2015 luoxudong.com
 * Company: 个人
 * Author: 罗旭东 (hi@luoxudong.com)
 * Date: 2015年8月24日 下午3:03:30
 * Version: 1.0
 */
package com.gjfax.app.ui.widgets;

/** 
 * <pre>
 * ClassName: OnLoadingViewListener
 * Description:TODO(这里用一句话描述这个类的作用)
 * Create by: 罗旭东
 * Date: 2015年8月24日 下午3:03:30
 * </pre>
 */
public abstract class OnLoadingViewListener {
	/**
	 * 点击登录按钮
	 */
	public abstract void onRefresh();
	
	/***
	 * 加载框消失
	 */
	public void onDismiss(){};
}
