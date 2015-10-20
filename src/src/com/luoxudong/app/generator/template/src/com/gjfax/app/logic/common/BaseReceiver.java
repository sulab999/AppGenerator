/**
 * Title: BaseReceiver.java
 * Description:
 * Copyright: Copyright (c) 2013 luoxudong.com
 * Company:个人
 * Author 罗旭东 (hi@luoxudong.com)
 * Date 2014-3-4 下午3:57:44
 * Version 1.0
 */
package com.gjfax.app.logic.common;

import java.io.File;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.gjfax.app.R;
import com.gjfax.app.ui.utils.ToastUtil;
import com.luoxudong.app.asynchttp.AsyncHttpUtil;
import com.luoxudong.app.asynchttp.callable.DownloadRequestCallable;
import com.luoxudong.app.utils.LogUtil;
import com.luoxudong.app.utils.MD5;

/**
 * ClassName: BaseReceiver Description:基础广播类 Create by 罗旭东 Date 2014-3-4
 * 下午3:57:44
 */
public class BaseReceiver extends BroadcastReceiver {
	private NotificationManager nm;
	private Notification notification;
	private int download_precent = 0;
	private RemoteViews views;
	private final int notificationId = 1234;
	private String mURL = null;

	@Override
	public void onReceive(final Context context, Intent intent) {
		String action = intent.getAction();

		if ("#{AppPackage}.ACTION_DOWNLOAD_APP".equals(action)) {
			nm = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
			notification = new Notification();
			notification.icon = android.R.drawable.stat_sys_download;
			// notification.icon=android.R.drawable.stat_sys_download_done;
			notification.tickerText = context.getString(R.string.app_name)
					+ "更新";
			notification.when = System.currentTimeMillis();
			notification.defaults = Notification.DEFAULT_LIGHTS;

			// 设置任务栏中下载进程显示的views
			views = new RemoteViews(context.getPackageName(),
					R.layout.layout_upgrade);
			notification.contentView = views;

			PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
					intent, PendingIntent.FLAG_UPDATE_CURRENT);
			notification.setLatestEventInfo(context, "", "", contentIntent);

			// 将下载任务添加到任务栏中
			nm.notify(notificationId, notification);

			// 启动线程开始执行下载任务
			mURL = intent.getStringExtra("url");

			if (!mURL.startsWith("http")) {
				String mStr = mURL;
				mURL = "http://" + mStr;
				//mURL = "https://app.gjfax.com/android/gjfax_V1.3.7.apk";
				LogUtil.d(this.toString(), "====mURL = " + mURL);
			}
			final String fileName = MD5.hexdigest(mURL) + ".apk";
			final File file = new File(BaseConstant.UPGRADE_DIR, fileName);
			if (file.exists()) {
				nm.cancel(notificationId);
				Instanll(file, context);
			} else {
				AsyncHttpUtil.download(mURL, BaseConstant.UPGRADE_DIR,
						fileName, new DownloadRequestCallable() {

							@Override
							public void onFailed(int arg0, String arg1) {
								// TODO Auto-generated method stub
								nm.cancel(notificationId);
								LogUtil.d(this.toString(), "====arg0 = " + arg0
										+ " ,arg1 = " + arg1);
								ToastUtil.showToast(context,
										R.string.failed_to_download_apk_tip);
							}

							@Override
							public void onTransfering(long totalLength,
									long transferedLength) {
								// TODO Auto-generated method stub
								super.onTransfering(totalLength,
										transferedLength);
								LogUtil.d(this.toString(), "====totalLength = "
										+ totalLength + " ,transferedLength = "
										+ transferedLength);
								// 更新状态栏上的下载进度信息
								download_precent = (int) (((float) transferedLength / totalLength) * 100);
								LogUtil.d(this.toString(), "====download_precent = "
										+ download_precent);
								views.setTextViewText(R.id.tv_process, "已下载"
										+ download_precent + "%");
								views.setProgressBar(R.id.pb_download, 100,
										download_precent, false);
								notification.contentView = views;
								nm.notify(notificationId, notification);
							}

							@Override
							public void onSuccess(String responseInfo) {
								// TODO Auto-generated method stub
								super.onSuccess(responseInfo);
								// 下载完成后清除所有下载信息，执行安装提示
								download_precent = 0;
								nm.cancel(notificationId);
								Instanll(file, context);
							}
						});
			}
		}
	}

	// 安装下载后的apk文件
	private void Instanll(File file, Context context) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
}
