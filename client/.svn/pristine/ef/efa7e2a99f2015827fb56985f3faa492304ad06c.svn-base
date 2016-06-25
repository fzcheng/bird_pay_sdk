package com.legame.paysdk.network.resultdata;

import java.io.InputStream;

import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.text.TextUtils;

import com.legame.paysdk.models.UpgradeInfo;

public class CheckUpgradeResultData extends BaseResultData {
	private static final String CMD = "upgrade";

	private static final String NS = "";
	private static final String RESP_INFO_TAG = "info";
	private static final String RESP_UPGRADE_TAG = "upgrade";
	private static final String RESP_FORCE_TAG = "force";
	private static final String RESP_STATUS_TAG = "status";
	private static final String RESP_DOWNLOAD_URL_TAG = "download_url";
	private static final String RESP_PHONE_TAG = "phone";
	private static final String RESP_UPPORT_TAG = "upport";
	private static final String RESP_VERSIONCODE_TAG = "versionCode";
	
	private UpgradeInfo mUpgradeInfo;

	public UpgradeInfo getUpgradeInfo() {
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
					if (TextUtils.equals(tag, RESP_INFO_TAG)) {
						if (!parseInfoTag(getXmlAttributes(parser))) {
							return false;
						}
					} else if (TextUtils.equals(tag, RESP_UPGRADE_TAG)) {
						String force = parser.getAttributeValue(NS,
								RESP_FORCE_TAG);
						String status = parser.getAttributeValue(NS,
								RESP_STATUS_TAG);
						String downloadUrl = parser.getAttributeValue(NS,
								RESP_DOWNLOAD_URL_TAG);
						String phone = parser.getAttributeValue(NS,
								RESP_PHONE_TAG);
						String upport = parser.getAttributeValue(NS,
								RESP_UPPORT_TAG);
						String versionCodeStr = parser.getAttributeValue(NS, RESP_VERSIONCODE_TAG);
						mUpgradeInfo = new UpgradeInfo();
						if (force != null) {
							mUpgradeInfo.setForce(Boolean.valueOf(force));
						} else {
							mUpgradeInfo.setForce(false);
						}
						if (status != null) {
							mUpgradeInfo.setStatus(Boolean.valueOf(status));
						} else {
							mUpgradeInfo.setStatus(false);
						}
						int versionCode = 0;
						try{
							versionCode = Integer.valueOf(versionCodeStr);
						}catch(NumberFormatException e){
							versionCode = 0;
							e.printStackTrace();
						}
						mUpgradeInfo.setVersioncode(versionCode);
						mUpgradeInfo.setDownloadUrl(downloadUrl);
						mUpgradeInfo.setPhone(phone);
						mUpgradeInfo.setUpport(upport);
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
