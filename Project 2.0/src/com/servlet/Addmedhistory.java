package com.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.mHistory;
import com.service.MServices;


@WebServlet("/Addmedhistory")
public class Addmedhistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Addmedhistory() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	
		mHistory mh= new mHistory();
	
		mh.setpid(request.getParameter("pUName"));
		mh.setdName(request.getParameter("doctor"));
		mh.setDesc(request.getParameter("desc"));
		
		MServices service = new MServices();
		service.addMedhistory(mh);
		HttpSession session = request.getSession();	
		session.setAttribute("pUName",request.getParameter("pUName") );
		System.out.println(session.getAttribute("pUName"));
		RequestDispatcher re = request.getRequestDispatcher("Viewmh.jsp");
		re.forward(request, response);
		
    }

}
