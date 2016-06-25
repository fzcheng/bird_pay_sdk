package test.com.cheyooh.tools.http;



import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import com.cheyooh.tools.http.HttpResult;
import com.cheyooh.tools.http.HttpUtils;
import com.cheyooh.tools.http.HttpUtils.HttpRequest;

public class RpcJsonTest {

	public static void main(String[] args)throws Exception {
		long tm0=System.currentTimeMillis();
		for(int i=0;i<21;i++){
			HttpRequest request=HttpUtils.newPostRequest("http://192.168.16.233:54321/rpc-dal");
			
			String body="{\"method\":\"misc:MiscBannerMapper.selectOne\",\"params\":[{\"orderByClause\":\"display_order DESC\",\"distinct\":false,\"oredCriteria\":[{\"criteria\":[{\"condition\":\"`location` =\",\"value\":\"loading\",\"secondValue\":null,\"noValue\":false,\"singleValue\":true,\"betweenValue\":false,\"listValue\":false,\"typeHandler\":null}]}]}],\"id\":1}";
			StringEntity entity=new StringEntity(body,ContentType.APPLICATION_JSON);
			
			request.setBodyEntity(entity);
			
			long tm=System.currentTimeMillis();
			HttpResult r=request.sendRequest();
			
			System.out.println("UseTime: "+(System.currentTimeMillis()-tm));
		 
			System.out.println(r.getBody());
		}
		System.out.println("Total-UseTime: "+(System.currentTimeMillis()-tm0));
	}

}
