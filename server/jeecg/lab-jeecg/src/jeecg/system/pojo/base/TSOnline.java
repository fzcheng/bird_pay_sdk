package jeecg.system.pojo.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 在线用户管理
 * @author duanqilu
 * @date 2013-06-01 16:44:15
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_s_online")
public class TSOnline implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**ip*/
	private java.lang.String ip;
	/**logindatetime*/
	private java.util.Date logindatetime;
	/**loginname*/
	private java.lang.String loginname;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ip
	 */
	@Column(name ="IP",nullable=false,length=50)
	public java.lang.String getIp(){
		return this.ip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ip
	 */
	public void setIp(java.lang.String ip){
		this.ip = ip;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  logindatetime
	 */
	@Column(name ="LOGINDATETIME",nullable=false)
	public java.util.Date getLogindatetime(){
		return this.logindatetime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  logindatetime
	 */
	public void setLogindatetime(java.util.Date logindatetime){
		this.logindatetime = logindatetime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  loginname
	 */
	@Column(name ="LOGINNAME",nullable=false,length=100)
	public java.lang.String getLoginname(){
		return this.loginname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  loginname
	 */
	public void setLoginname(java.lang.String loginname){
		this.loginname = loginname;
	}
}
