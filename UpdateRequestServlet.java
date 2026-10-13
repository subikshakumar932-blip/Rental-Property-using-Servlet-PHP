package com.rentalproperty.servlet;
import com.rentalproperty.util.DBConnection; import javax.servlet.*; import javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import java.io.*; import java.sql.*;
@WebServlet("/updateRequest") public class UpdateRequestServlet extends HttpServlet {
 protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{HttpSession s=req.getSession(false);if(s==null||s.getAttribute("userId")==null){res.sendRedirect("login.jsp");return;}try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("UPDATE rental_requests r JOIN properties p ON r.property_id=p.property_id SET r.status=? WHERE r.request_id=? AND p.owner_id=?")){p.setString(1,req.getParameter("status"));p.setInt(2,Integer.parseInt(req.getParameter("requestId")));p.setInt(3,(Integer)s.getAttribute("userId"));p.executeUpdate();res.sendRedirect("requests");}catch(Exception e){throw new ServletException(e);}}
}
