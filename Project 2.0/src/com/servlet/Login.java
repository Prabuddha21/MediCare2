package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyStore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Doctor;
import com.model.User;
import com.service.DoctorService;
import com.service.LoginService;
import com.util.DBConnection;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Login() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	login(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	login(request, response);
    }
    
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String ref = request.getHeader("Referer");
		
		System.out.println(ref);
		
		if((!request.getParameter("uname").isEmpty()) && (!request.getParameter("pass").isEmpty())) {
			
			String uname = request.getParameter("uname");
			String pass = request.getParameter("pass");
			
			
			LoginService LS = new LoginService();
			
			if(LS.checkPatient(uname, pass)){
				
				String type = "patient";
				
				User p = LS.getUser(uname, type);
				
				HttpSession session = request.getSession();
				
				//normal login
				session.setAttribute("uname", p.getUname());
				session.setAttribute("email", p.getEmail());
				session.setAttribute("userID", p.getUserID());
				session.setAttribute("uType", type);
				session.setAttribute("NIC", p.getNIC());
				
				
				//if forwarded from appointment page values will be added to the session after the login process
				if(!request.getParameter("docID").equals("null")) {
								
					int docID = Integer.parseInt(request.getParameter("docID"));
					int day = Integer.parseInt(request.getParameter("day"));
					
					session.setAttribute("docID", docID);
					session.setAttribute("hosp", request.getParameter("hosp"));
					session.setAttribute("time", request.getParameter("time"));
					session.setAttribute("day", day);					    
					session.setAttribute("date", request.getParameter("date"));
				    
				    
					RequestDispatcher rd = request.getRequestDispatcher("payment.jsp");
					rd.forward(request, response);
					
				}
				else {
					
					response.sendRedirect("user.jsp");
				}
			}
			else if(LS.checkDoctor(uname, pass)){
				
				try {
					
					System.out.println(uname+pass);
					String sql = "select * from  doctor where pass='" + pass + "' and uname = '" + uname + "'";
					
					Connection con = DBConnection.getDBConnection();	
					PreparedStatement pst = con.prepareStatement(sql);
					
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true) {
						
						LoginService ls = new LoginService();
						String sql1 = "update doctor set status =1 where uname = '"+ uname+"'";
						pst = con.prepareStatement(sql1);
						
						pst.execute();
							
						int did;
						DoctorService ds =new DoctorService();
						Doctor doc = new Doctor();
						doc = ds.getDoctorByUId(uname);
						did = doc.getUserID();
						
						HttpSession session = request.getSession();	
						session.setAttribute("uname", uname);
						session.setAttribute("docID", did);
						session.setAttribute("mID", doc.getMID());
						response.sendRedirect("DoctorProfile.jsp");
						
						//rd.include(request,response);
					}
					else {
						
						System.out.println("Error :(");
						response.sendRedirect("logerror.jsp");
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Credentials invalid, try again !')");
				out.println("location='login.jsp';");
				out.println("</script>");
			}
				
		}
		else {
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Credentials invalid, try again !')");
			out.println("location='login.jsp';");
			out.println("</script>");
			
			//response.sendRedirect("login.jsp");
			
		}
		
		
	}

}
