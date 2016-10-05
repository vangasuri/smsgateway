package com.mv.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mv.common.CustomerVO;
import com.mv.common.LoginUser;
import com.mv.constants.CoreConstants;


public class DBUtils {
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/smsgateway";
	   static final String USER = "root";
	   static final String PASS = "admin";
	   static final String SCHEMA_NAME = "smsgateway.";
	   
	   private  Connection getConnection() throws ClassNotFoundException, SQLException {
		  Connection conn = null;
		  Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      return conn;
	   }
	   
	   public void addUser(String userName,String pwd,String userType) throws ClassNotFoundException, SQLException{
		   
		   Connection conn = getConnection();
		   
		   Statement stmnt = conn.createStatement();
		   String sql = "INSERT INTO " + SCHEMA_NAME + "SMS_ADMIN_USERS  ( USER_NAME , PWD , USER_STATUS , USER_TYPE) VALUES ('"+ userName +"','"+ pwd +"','1','"+ userType+"')";
		   System.out.println(sql);
		   stmnt.executeUpdate(sql);
		   
	   }
	   
	   public List<LoginUser> getUsers() throws ClassNotFoundException, SQLException{
		   Connection conn = getConnection();
		   Statement stmnt = conn.createStatement();
		   String sql = "SELECT ID, USER_NAME, PWD, USER_STATUS , USER_TYPE FROM "+SCHEMA_NAME+"SMS_ADMIN_USERS";
		   ResultSet rs = stmnt.executeQuery(sql);
		   List<LoginUser> usersList = new ArrayList<LoginUser>();
		   while(rs.next()){
			   LoginUser user = new LoginUser();
			   user.setId(rs.getInt("ID"));
			   user.setUserName(rs.getString("USER_NAME"));   
			   user.setPwd(rs.getString("PWD"));   
			   if(rs.getString("USER_STATUS").equalsIgnoreCase("1"))
				   user.setUserStatus(CoreConstants.ACTIVE);  
			   else
				   user.setUserStatus(CoreConstants.IN_ACTIVE);
			   
			   if(rs.getString("USER_TYPE").equalsIgnoreCase("1"))
				   user.setUserType(CoreConstants.SWEETS);  
			   else if(rs.getString("USER_TYPE").equalsIgnoreCase("2"))
				   user.setUserType(CoreConstants.HAIR_AND_BEAUTY);
			   else if(rs.getString("USER_TYPE").equalsIgnoreCase("3"))
				   user.setUserType(CoreConstants.LBNAGAR_PAPADAMS);
			   else if(rs.getString("USER_TYPE").equalsIgnoreCase("4"))
				   user.setUserType(CoreConstants.PAPADAMS);
			   else if(rs.getString("USER_TYPE").equalsIgnoreCase("5"))
				   user.setUserType(CoreConstants.ADMIN);
			   
			   usersList.add(user);
		   }
		   return usersList;
	   
	   } 
	   
	   public LoginUser validateLoginUser(String userName,String pwd) throws ClassNotFoundException, SQLException{
		   Connection conn = getConnection();
		   Statement stmnt = conn.createStatement();
		   String sql = "SELECT ID, USER_NAME, PWD, USER_STATUS , USER_TYPE FROM "+SCHEMA_NAME+"SMS_ADMIN_USERS WHERE USER_NAME = '"+ userName.trim() +"' AND  pwd = '"+pwd.trim()+"'";
		   System.out.println(sql);
		   ResultSet rs = stmnt.executeQuery(sql);
		   LoginUser loginUser = null;
		   while(rs.next()){
			   loginUser = new LoginUser();
			   loginUser.setId(rs.getInt("ID"));
			   loginUser.setUserName(rs.getString("USER_NAME"));   
			   loginUser.setPwd(rs.getString("PWD"));   
			   if(rs.getString("USER_STATUS").equalsIgnoreCase("1"))
				   loginUser.setUserStatus(CoreConstants.ACTIVE);  
			   else
				   loginUser.setUserStatus(CoreConstants.IN_ACTIVE);
			   
			   if(rs.getString("USER_TYPE").equalsIgnoreCase("1"))
				   loginUser.setUserType(CoreConstants.SWEETS);  
			   else if(rs.getString("USER_TYPE").equalsIgnoreCase("2"))
				   loginUser.setUserType(CoreConstants.HAIR_AND_BEAUTY);
			   else if(rs.getString("USER_TYPE").equalsIgnoreCase("3"))
				   loginUser.setUserType(CoreConstants.LBNAGAR_PAPADAMS);
			   else if(rs.getString("USER_TYPE").equalsIgnoreCase("4"))
				   loginUser.setUserType(CoreConstants.PAPADAMS);
			   else if(rs.getString("USER_TYPE").equalsIgnoreCase("5"))
				   loginUser.setUserType(CoreConstants.ADMIN);
		   }
		   
		   return loginUser;
	   } 
	   
	   
	   public void addPapadamsUsers(CustomerVO custVO) throws ClassNotFoundException, SQLException{
		   
		   Connection conn = getConnection();
		   
		   String sql = "INSERT INTO " + SCHEMA_NAME + "sms_papadams_cust  ( CUST_NAME, PHNO, BILL_AMNT, DISCOUNT, SMS_TRIGGERED, PROMOTION_EXPIRY_DATE, CREATED_BY ) VALUES (?,?,?,?,?,?,?)";
		   PreparedStatement  pstmnt = conn.prepareStatement(sql);
		   pstmnt.setString(1,custVO.getCustName());
		   pstmnt.setString(2,custVO.getCustPhNo());
		   pstmnt.setString(3,custVO.getCustBillAmnt());
		   pstmnt.setString(4,custVO.getDiscount());
		   pstmnt.setString(5,custVO.getSmsTriggered());
		   if(custVO.getPromotionExpiryDate() != null){
		   java.sql.Date sqlDate = new java.sql.Date(custVO.getPromotionExpiryDate().getTime());
		   pstmnt.setDate(6,sqlDate);
		   }else{
			   pstmnt.setDate(6,null);
		   }
		   pstmnt.setString(7,custVO.getCreatedBy());
		   
		   pstmnt.executeUpdate();
	   }
	   
	   public List<CustomerVO> searchAllNonDiscountedUsers() throws ClassNotFoundException, SQLException{
		   
		   Connection conn = getConnection();
		   Statement stmnt = conn.createStatement();
		   String sql = "SELECT ID, CUST_NAME, PHNO, BILL_AMNT FROM "+SCHEMA_NAME+"sms_papadams_cust WHERE SMS_TRIGGERED = 'NO' AND DISCOUNT IS NULL OR DISCOUNT = '' " ;
		   System.out.println(sql);
		   ResultSet rs = stmnt.executeQuery(sql);
		   CustomerVO custVO = null;
		   List<CustomerVO> nonDiscountedCustomersList = new ArrayList<CustomerVO>();
		   while(rs.next()){
			   custVO = new CustomerVO();
			   custVO.setId(rs.getInt("ID"));
			   custVO.setCustName(rs.getString("CUST_NAME"));   
			   custVO.setCustPhNo(rs.getString("PHNO")); 
			   custVO.setCustBillAmnt(rs.getString("BILL_AMNT"));   
			   nonDiscountedCustomersList.add(custVO);
		   }
		   return nonDiscountedCustomersList;
	   }
	   
}
