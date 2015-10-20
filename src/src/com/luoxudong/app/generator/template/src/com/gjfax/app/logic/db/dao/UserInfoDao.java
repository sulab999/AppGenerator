/**
 * Title: UserInfoDao.java
 * Description:
 * Copyright: Copyright (c) 2013 luoxudong.com
 * Company:个人
 * Author: 罗旭东 (hi@luoxudong.com)
 * Date: 2014-9-15 上午10:16:16
 * Version: 1.0
 */
package com.gjfax.app.logic.db.dao;

import java.util.List;

import android.text.TextUtils;

import com.gjfax.app.logic.db.dao.interfaces.IUserInfoDao;
import com.gjfax.app.logic.db.model.UserInfo;
import com.luoxudong.app.database.manager.BaseDao;

/**
 * ClassName: UserInfoDao Description: 用户信息Dao层 Create by: 罗旭东 Date: 2014-9-15
 * 上午10:16:16
 */
public class UserInfoDao extends BaseDao<UserInfo, Long> implements
		IUserInfoDao {
	@Override
	public UserInfo findUserInfoByUserId(String userId) {
		if (TextUtils.isEmpty(userId)) {
			return null;
		}

		UserInfo bean = new UserInfo();
		//bean.setPhoneNumber(userId);
		List<UserInfo> userInfos = queryByCondition(bean);
		

		if (userInfos != null && userInfos.size() > 0) {
			return userInfos.get(0);
		}

		return null;
	}
}
