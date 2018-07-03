package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.Email;

@WebServlet("/GetCode")
public class GetCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetCode() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email = request.getParameter("email");
		
		int rand = (int) (Math.random() * 100000) + 5;
		
		System.out.println(rand);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("rand", rand);
		session.setAttribute("email", email);
		
		String body = "Enter this code to reset you password : " + rand;
		
		Email e = new Email(email, "Password Reset Code", body);
		
		RequestDispatcher rd = request.getRequestDispatcher("code.jsp");
		rd.forward(request, response);
		
	}

}
