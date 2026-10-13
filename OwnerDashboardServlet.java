package com.rentalproperty.servlet;
import com.rentalproperty.util.DBConnection; import javax.servlet.*; import javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import java.io.*; import java.sql.*;
@WebServlet("/OwnerDashboardServlet") public class OwnerDashboardServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
  HttpSession s=req.getSession(false); if(s==null||s.getAttribute("userId")==null){res.sendRedirect("login.jsp");return;} int id=(Integer)s.getAttribute("userId");
  try(Connection c=DBConnection.getConnection()){req.setAttribute("propertyCount",count(c,"SELECT COUNT(*) FROM properties WHERE owner_id=",id));req.setAttribute("pendingCount",count(c,"SELECT COUNT(*) FROM rental_requests r JOIN properties p ON r.property_id=p.property_id WHERE p.owner_id=? AND r.status='Pending'",id));req.setAttribute("approvedCount",count(c,"SELECT COUNT(*) FROM rental_requests r JOIN properties p ON r.property_id=p.property_id WHERE p.owner_id=? AND r.status='Approved'",id));req.setAttribute("rejectedCount",count(c,"SELECT COUNT(*) FROM rental_requests r JOIN properties p ON r.property_id=p.property_id WHERE p.owner_id=? AND r.status='Rejected'",id));req.getRequestDispatcher("ownerDashboard.jsp").forward(req,res);}catch(Exception e){throw new ServletException(e);}
 }
 private int count(Connection c,String q,int id)throws SQLException{String sql=q.endsWith("=")?q+"?":q;try(PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,id);ResultSet r=p.executeQuery();r.next();return r.getInt(1);}}
}
