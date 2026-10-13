package com.rentalproperty.model;
import java.sql.Date;
public class RentalRequest { private int id; private String title,tenantName,phone,status; private Date date;
 public RentalRequest(int id,String title,String tenantName,String phone,Date date,String status){this.id=id;this.title=title;this.tenantName=tenantName;this.phone=phone;this.date=date;this.status=status;}
 public int getId(){return id;} public String getTitle(){return title;} public String getTenantName(){return tenantName;} public String getPhone(){return phone;} public Date getDate(){return date;} public String getStatus(){return status;}}
