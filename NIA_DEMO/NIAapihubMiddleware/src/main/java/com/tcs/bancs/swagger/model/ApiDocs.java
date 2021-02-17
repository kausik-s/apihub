package com.tcs.bancs.swagger.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_API_DOCS")
public class ApiDocs {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;
    private String apiName;
    
    /*******many to many mapping start*****/
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "apiDocs")
    private Set<User> users = new HashSet<>();
    
    /*******many to many mapping end*****/

    public ApiDocs() {

    }

    public ApiDocs(String fileName, String fileType, byte[] data,String apiName) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.apiName=apiName;
    }
    
    
    public ApiDocs(String fileName, String fileType, byte[] data,String apiName,String id) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.apiName=apiName;
        this.id=id;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
