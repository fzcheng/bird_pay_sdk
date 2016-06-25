package com.cheyooh.service.sdk.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.tools.log.Logger;

public class GameSession {	
	static Logger logger=new Logger(GameSession.class);
	
	private final static byte V=0x01;
	private final static int  M="tsc9526".hashCode();	
	
	private String sid;
	
	private int uid;
	private int gameId;
	private Date time;
	
	private int version;
	private int server;
	
	/**
	 * 有效时间, 单位毫秒
	 */
	private long validTime;
	
	public GameSession(int uid,int gameId){
		this.time=new Date();		
		this.uid=uid;
		this.gameId=gameId;
		
		
		byte[] b0=BytesTool.long2Bytes(time.getTime());
		byte[] b1=BytesTool.int2Bytes(uid^M);
		byte[] b2=BytesTool.int2Bytes(gameId^M);
		b0[0]=V;
		b0[1]=(byte)(GenerateTool.WEB_SERVER_ID&0xFF);
		
		StringBuilder sb=new StringBuilder();
		sb.append(Hex.encodeHexString(b0));
		sb.append(Hex.encodeHexString(b1));
		sb.append(Hex.encodeHexString(b2));
		
		String sk=Cfg.cfg.getString("sdk.session.secretkey");
		String md5=DigestUtils.md5Hex(sb.toString()+sk);
		sb.append(md5);
		
		this.version=b0[0];
		this.server =b0[1];
		
		this.sid=sb.toString();				 
	}
	
	public GameSession(String sid){
		this.sid=sid;
		
		if(sid!=null && sid.length()==64){
			String sk=Cfg.cfg.getString("sdk.session.secretkey");
			
			String md5=sid.substring(32);
			String v=DigestUtils.md5Hex(sid.substring(0,32)+sk);
			if(md5.equals(v)){
				try{
					byte[] b0=Hex.decodeHex(sid.substring(0,16).toCharArray());
					byte[] b1=Hex.decodeHex(sid.substring(16,24).toCharArray());
					byte[] b2=Hex.decodeHex(sid.substring(24,32).toCharArray());
					this.version=b0[0];
					this.server =b0[1];					
					b0[0]=0x00;
					b0[1]=0x00;
					
					this.time  =new Date(BytesTool.bytes2Long(b0));
					this.uid   =BytesTool.bytes2Int(b1)^M;
					this.gameId=BytesTool.bytes2Int(b2)^M;
				}catch(DecoderException e){
					logger.error("Decode session fail: "+sid,e);
				}
			}
		}
	}
	
	public long getValidTime(){
		if(validTime==0){
			String exp=Cfg.cfg.getString("sdk.session.expired","7d");
			  
			validTime=StringTool.getTimeMillis(exp);
		}
		return validTime;
	}
	
	/**
	 * 检查session是否过期
	 * 
	 * @return
	 */
	public boolean isExpired(){
		if(time!=null){								
			Date now=new Date();
			long diff=now.getTime()-time.getTime();
			return diff>getValidTime();
		}
		return true;
	}
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("sid: ").append(sid).append("[");
		
		sb.append("version: ").append(version);
		sb.append(", server: ").append(server);
		
		sb.append(", time: ");		
		if(time!=null){
			sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
		}else{
			sb.append("-");
		}
		sb.append(", uid: ").append(uid);
		sb.append(", gameid: ").append(gameId);		
		sb.append("]");
		return sb.toString();
	}
	
	public String getSid(){
		return sid;
	}

	public int getUid() {
		return uid;
	}

	public int getGameId() {
		return gameId;
	}

	public Date getTime() {
		return time;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getServer() {
		return server;
	}

	public void setServer(int server) {
		this.server = server;
	}
}
