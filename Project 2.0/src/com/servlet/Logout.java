package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.LogoutService;


@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		LogoutService lgs = new LogoutService();
		
		if(session.getAttribute("mID") != null) {
		
			lgs.statusUpdate((String) session.getAttribute("mID"));
		}
		
		session.removeAttribute("uname");
		session.removeAttribute("email");
		session.removeAttribute("userID");
		session.removeAttribute("uType");
		session.removeAttribute("NIC");
		session.removeAttribute("mID");
		session.removeAttribute("hosp");
		session.removeAttribute("time");
		session.removeAttribute("day");
		session.invalidate();
		
		response.sendRedirect("index.jsp");
		
	}

}
