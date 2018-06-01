package com.tamguo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * The persistent class for the tiku_ad database table.
 * 
 */
@Entity
@Table(name="tiku_member")
@NamedQuery(name="MemberEntity.findAll", query="SELECT m FROM MemberEntity m")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String uid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="nick_name")
	private String nickName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="avatar")
	private String avatar;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="email")
	private String email;
	
	@Column(name="point")
	private Integer point;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	@Column(name="last_login_time")
	private Long lastLoginTime;
	
	@Column(name="paper_num")
	private Integer paperNum;
	
	@Column(name="question_num")
	private Integer questionNum;
	
	@Column(name="down_num")
	private Integer downNum;
	
	@Column(name="hits_num")
	private Integer hitsNum;
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getPaperNum() {
		return paperNum;
	}

	public void setPaperNum(Integer paperNum) {
		this.paperNum = paperNum;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}

	public Integer getHitsNum() {
		return hitsNum;
	}

	public void setHitsNum(Integer hitsNum) {
		this.hitsNum = hitsNum;
	}

	public Integer getDownNum() {
		return downNum;
	}

	public void setDownNum(Integer downNum) {
		this.downNum = downNum;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}