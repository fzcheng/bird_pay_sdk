package com.legame.paysdk.network.resultdata;

import java.io.InputStream;

import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.text.TextUtils;

import com.legame.paysdk.models.UpgradeJarInfo;

public class UpgradeJarResultData extends BaseResultData {
	private static final String CMD = "Upgrade_Jar";

	private static final String NS = "";
	private static final String RESP_INFO_TAG = "info";
	private static final String RESP_UPGRADE_JAR_TAG = "item";
	private static final String RESP_IFDOWNLOAD_TAG = "ifdownload";
	private static final String RESP_VERSION_CODE_JAR_TAG = "version_code_jar";
	private static final String RESP_DOWNLOAD_URL_TAG = "down_url";
	
	private UpgradeJarInfo mUpgradeInfo;

	public UpgradeJarInfo getUpgradeInfo() {
		return mUpgradeInfo;
	}

	@Override
	public String getExpectPageType() {
		return CMD;
	}

	@Override
	public boolean parseXml(InputStream dateInput) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(dateInput, HTTP.UTF_8);
			int eventType = parser.getEventType();
			String tag = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				// every time, check if user canceled this op
				if (isParseDataCanceled) {
					return false;
				}
				if (eventType == XmlPullParser.START_TAG) {
					tag = parser.getName();
//					Log.i(TAG,"tag--->"+tag);
					if (TextUtils.equals(tag, RESP_INFO_TAG)) {
						if (!parseInfoTag(getXmlAttributes(parser))) {
							return false;
						}
					} else if (TextUtils.equals(tag, RESP_UPGRADE_JAR_TAG)) {
						String ifDownload = parser.getAttributeValue(NS,
								RESP_IFDOWNLOAD_TAG);
						String ver = parser.getAttributeValue(NS,
								RESP_VERSION_CODE_JAR_TAG);
						String downloadUrl = parser.getAttributeValue(NS,
								RESP_DOWNLOAD_URL_TAG);
						mUpgradeInfo = new UpgradeJarInfo();
//						Log.i(TAG,"ifDownload-->"+ifDownload);
//						Log.i(TAG,"ver-->"+ver);
//						Log.i(TAG,"downloadUrl-->"+downloadUrl);
						if (ifDownload != null) {
							mUpgradeInfo.setIfdownload(Boolean.valueOf(ifDownload));
						} else {
							mUpgradeInfo.setIfdownload(false);
						}
						mUpgradeInfo.setDown_url(downloadUrl);
						mUpgradeInfo.setVersion_code_jar(ver);
						break;
					}
				}
				eventType = parser.next();
			}// ?end while
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
