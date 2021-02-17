package com.tcs.bancs.swagger.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "T_APIHUB_USER")
public class User {
	public User()
	{
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "USERID")
	private String userid;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "MOBILE_NO")
	private String mobileno;
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedDate;
	
	/******ADDDED FOR JOIN TABLE START*******/
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "T_USER_API_MAPPING",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "API_ID") })
    private Set<ApiDocs> apiDocs = new HashSet<>();

	/******ADDDED FOR JOIN TABLE END*******/
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public java.util.Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Set<ApiDocs> getApiDocs() {
		return apiDocs;
	}
	public void setApiDocs(Set<ApiDocs> apiDocs) {
		this.apiDocs = apiDocs;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	 
	 

	

}

