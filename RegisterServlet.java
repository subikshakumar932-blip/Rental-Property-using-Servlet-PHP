package com.rentalproperty.servlet;
import com.rentalproperty.util.DBConnection; import javax.servlet.*; import javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import java.io.*; import java.sql.*;
@WebServlet("/register") public class RegisterServlet extends HttpServlet {
 protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
  String name=req.getParameter("name"),email=req.getParameter("email"),password=req.getParameter("password"),phone=req.getParameter("phone"),role=req.getParameter("role");
  try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("INSERT INTO users(name,email,password,phone,role) VALUES(?,?,?,?,?)")){p.setString(1,name);p.setString(2,email);p.setString(3,password);p.setString(4,phone);p.setString(5,role);p.executeUpdate();res.sendRedirect("login.jsp?msg=registered");}
  catch(SQLException e){res.sendRedirect("register.jsp?error=Email already exists or database error");}
 }
}
