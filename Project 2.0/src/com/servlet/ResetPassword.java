package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.PatientService;

@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResetPassword() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String email = (String) session.getAttribute("email");
		
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		
		System.out.println(email+pass1+pass2);
		
		if(pass1.equals(pass2)) {
			
			PatientService ps = new PatientService();
			
			ps.updatePass(email, pass1);
			
			session.removeAttribute("rand");
			session.removeAttribute("email");
			session.invalidate();
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Password Successfully Changed !')");
			out.println("location='login.jsp';");
			out.println("</script>");
			
		}
		else {
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Passwords do not match !')");
			out.println("location='changepass.jsp';");
			out.println("</script>");
		}
		
	}

}
