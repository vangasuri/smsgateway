package com.mv.common;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mv.db.DBUtils;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	
	private String pwd;
	
	private String userType ="1";

	List<LoginUser> usersList = new ArrayList<LoginUser>();
	
	public String getUserType() {
		return userType;
	} 

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
 
	public List<LoginUser> getUsersList() {
		return usersList;
	}
 
	public void setUsersList(List<LoginUser> usersList) {
		this.usersList = usersList;
	}
 
	public String save() throws ClassNotFoundException, SQLException {
		
		// Save Users
		if(userName == null || pwd == null){
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Entered user credentials are wrong !! Please try again"));
			return "";
		}
		
		DBUtils dbUtils = new DBUtils();
		dbUtils.addUser(userName, pwd, userType);
        usersList = dbUtils.getUsers();
        
        userName=null;
        pwd=null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("User created successfully"));
        return "";
    }

}