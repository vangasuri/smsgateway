package com.mv.common;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.mv.db.DBUtils;

@ManagedBean(name="adminBean")
@SessionScoped
public class AdminBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Date startDate = null;
	
	Date endDate = null;
	
	Integer []dicountIndex = new Integer[1000];
	
	private String orientation = "horizontal";
	
	List<CustomerVO> nonDiscountedCustomersList= new ArrayList<CustomerVO>();

	public Integer[] getDicountIndex() {
		return dicountIndex;
	}

	public void setDicountIndex(Integer[] dicountIndex) {
		this.dicountIndex = dicountIndex;
	}
	
    public List<CustomerVO> getNonDiscountedCustomersList() {
		return nonDiscountedCustomersList;
	}

	public void setNonDiscountedCustomersList(List<CustomerVO> nonDiscountedCustomersList) {
		this.nonDiscountedCustomersList = nonDiscountedCustomersList;
	}

	public String getOrientation() {
        return orientation;
    }
 
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void searchAllNonDiscountedUsers() throws ClassNotFoundException, SQLException{
    	
    	DBUtils dbUtils = new DBUtils();
    	
    	System.out.println(getStartDate() + ".." +getEndDate());
    	nonDiscountedCustomersList = dbUtils.searchAllNonDiscountedUsers();
    	
    	System.out.println(nonDiscountedCustomersList);
    }
    
    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}