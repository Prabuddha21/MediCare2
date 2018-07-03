package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CheckCode")
public class CheckCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CheckCode() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		int rand = (int) session.getAttribute("rand");
		
		int code = Integer.parseInt(request.getParameter("code"));
		
		if(rand == code) {
			
			RequestDispatcher rd = request.getRequestDispatcher("changepass.jsp");
			rd.forward(request, response);
			
		}
		else {
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Invalid code, Try again !')");
			out.println("location='forgot.jsp';");
			out.println("</script>");
		}
	}

}
