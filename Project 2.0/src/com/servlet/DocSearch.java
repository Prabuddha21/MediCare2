package com.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.AppService;

@WebServlet("/DocSearch")
public class DocSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DocSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String spec = request.getParameter("spec");
		String hosp = request.getParameter("hosp");
		String date = request.getParameter("date");
		
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
		
		Date d1 = null;
		try {
			if(!date.isEmpty()) {
			
				d1 = f1.parse(date);
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		request.setAttribute("date", d1);
		
		RequestDispatcher RD = request.getRequestDispatcher("appointment.jsp");
		RD.forward(request, response);
		
	}

}
