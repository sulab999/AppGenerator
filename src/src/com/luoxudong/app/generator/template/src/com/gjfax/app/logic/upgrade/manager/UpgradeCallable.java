/**
 * Title: UpgradeCallable.java
 * Description: 
 * Copyright: Copyright (c) 2013-2015 luoxudong.com
 * Company: 个人
 * Author: 罗旭东 (hi@luoxudong.com)
 * Date: 2015年8月4日 下午5:59:48
 * Version: 1.0
 */
package com.gjfax.app.logic.upgrade.manager;

import com.gjfax.app.logic.common.BaseCallable;

/** 
 * <pre>
 * ClassName: UpgradeCallable
 * Description:TODO(这里用一句话描述这个类的作用)
 * Create by: 罗旭东
 * Date: 2015年8月4日 下午5:59:48
 * </pre>
 */
public abstract class UpgradeCallable extends BaseCallable {
	/**
	 * 有新版本升级
	 * @param url
	 * @param versionName
	 * @param appName
	 * @param desc
	 * @param md5
	 * @param upgradeLevel
	 */
	public abstract void onFindNewVersion(String url, String versionName, String appName, String desc, String md5, int upgradeLevel, boolean isForce);
	
	/**
	 * 不需要升级
	 */
	public abstract void onUnFindNewVersion();
}
