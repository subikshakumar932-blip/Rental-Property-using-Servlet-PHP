package com.rentalproperty.servlet;
import com.rentalproperty.model.Property; import com.rentalproperty.util.DBConnection; import javax.servlet.*; import javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import java.io.*; import java.sql.*; import java.util.*;
@WebServlet("/properties") public class PropertyServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
  String location=req.getParameter("location"),type=req.getParameter("type"),min=req.getParameter("min"),max=req.getParameter("max"); StringBuilder sql=new StringBuilder("SELECT * FROM properties WHERE status='Available'");
  if(location!=null&&!location.trim().isEmpty())sql.append(" AND location LIKE ?"); if(type!=null&&!type.trim().isEmpty()&&!type.equals("All"))sql.append(" AND type=?"); if(min!=null&&!min.isEmpty())sql.append(" AND rent>=?"); if(max!=null&&!max.isEmpty())sql.append(" AND rent<=?"); sql.append(" ORDER BY property_id DESC");
  List<Property> list=new ArrayList<Property>();
  try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql.toString())){int i=1;if(location!=null&&!location.trim().isEmpty())p.setString(i++,"%"+location+"%");if(type!=null&&!type.trim().isEmpty()&&!type.equals("All"))p.setString(i++,type);if(min!=null&&!min.isEmpty())p.setDouble(i++,Double.parseDouble(min));if(max!=null&&!max.isEmpty())p.setDouble(i++,Double.parseDouble(max));ResultSet r=p.executeQuery();while(r.next())list.add(new Property(r.getInt("property_id"),r.getString("title"),r.getString("location"),r.getString("type"),r.getDouble("rent"),r.getInt("bedrooms"),r.getString("description"),r.getString("status"),r.getString("image")));}
  catch(Exception e){throw new ServletException(e);} req.setAttribute("properties",list);req.getRequestDispatcher("properties.jsp").forward(req,res);
 }
}
