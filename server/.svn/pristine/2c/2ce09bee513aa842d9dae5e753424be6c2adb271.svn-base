package jeecg.ext.online.entity;
import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "index_content")
@SuppressWarnings("serial")
public class IndexContent implements java.io.Serializable{
	private Integer id;
	private String keyss;
	
	@Id  
	@GeneratedValue(generator = "paymentableGenerator")       
	@GenericGenerator(name = "paymentableGenerator", strategy = "identity")
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="keyss")
	public String getKeys() {
		return keyss;
	}
	public void setKeys(String keys) {
		this.keyss = keys;
	}
	
	
	
	
	
}

