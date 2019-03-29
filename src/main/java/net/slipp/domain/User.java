package net.slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id //primary key 指定
	@GeneratedValue // 自動で1ずつ増加するように（sequency）
	private Long id;
	
	@Column(nullable=false, length=20, unique=true)//null値が入れないように
	private String userId; //primary key
	private String password;
	private String name;
	private String email;
	
	
	public boolean matchId(Long newId) {
		if (newId == null) {
			return false; 
		}
		return newId.equals(id);
	}
	
	
	public Long getId() {
		return id;
	}
	public String getUserId() {
		return userId;
	}
	
    public boolean matchPassword(String newPassword) {
    	if (newPassword == null) {
    		return false;
    	}
    	return newPassword.equals(password);
    }
	
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void update(User newUser) {
		this.userId = newUser.userId;
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	
}
