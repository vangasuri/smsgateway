package com.mv.common;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mv.constants.CoreConstants;
import com.mv.db.DBUtils;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String pwd;
	CustomerVO custVO = new CustomerVO();
	public CustomerVO getCustVO() {
		return custVO;
	}

	public void setCustVO(CustomerVO custVO) {
		this.custVO = custVO;
	}

	public void saveCustomer() {
		
	}
	
	public String validateLoginUser() throws ClassNotFoundException, SQLException {
		// validate Users
		DBUtils dbUtils = new DBUtils();
		LoginUser loginUser = dbUtils.validateLoginUser(userName, pwd);
		System.out.println(loginUser);
		if(loginUser == null){
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Entered user credentials are wrong !! Please try again"));
			return "";
		}else{
			if(loginUser.getUserStatus().equalsIgnoreCase(CoreConstants.IN_ACTIVE)){
				FacesContext.getCurrentInstance().addMessage(null,
		                new FacesMessage("User account is deactivated. Please contact admin for enable"));
			}else if(loginUser.getUserType().equalsIgnoreCase(CoreConstants.SWEETS)) {
				return "sweets";
			}else if(loginUser.getUserType().equalsIgnoreCase(CoreConstants.HAIR_AND_BEAUTY)) {
				return "handb";
			}else if(loginUser.getUserType().equalsIgnoreCase(CoreConstants.LBNAGAR_PAPADAMS)) {
				return "lbnagarpapadams";
			}else if(loginUser.getUserType().equalsIgnoreCase(CoreConstants.PAPADAMS)) {
				return "papadams";
			}else if(loginUser.getUserType().equalsIgnoreCase(CoreConstants.ADMIN)) {
				return "admin";
			}
		}
		return "";
    }
	
	public void savePapadamsCustomers() throws ClassNotFoundException, SQLException{
		
		DBUtils dbUtils = new DBUtils();
		custVO.setSmsTriggered(CoreConstants.SMS_TRIGGERED_NO);
		custVO.setCreatedBy(CoreConstants.PAPADAMS_USER);
		dbUtils.addPapadamsUsers(custVO);
		
		FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("User has ben added successfully"));
		custVO=new CustomerVO();
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

}