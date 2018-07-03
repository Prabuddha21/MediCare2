package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.AppService;


@WebServlet("/AddAppointment")
public class AddAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddAppointment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		AppService AS = new AppService();
		
		//checking the appointment date
		String dt = request.getParameter("date");//selected date
		int docDay = Integer.parseInt(request.getParameter("day"));//doctors day
		
		int doW = AS.getDay(dt);//date of week
		
		long diff = AS.getDateDiff(null, dt);//time difference between now and appointment date
		diff = diff / (1000*60*60*24);//conversion
		
		
		/*checking if the selected day is correct 
		  example 
		  if the doctor is available in MM/Monday the selected day should be MM/Monday*/
		
		if((doW == docDay) && (diff < 0)) {
			
			//if user is not logged in
			if(session.getAttribute("uType") != "patient"){
				
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				
			}
			else { //if user is logged in
				
				int docID = Integer.parseInt(request.getParameter("docID"));
				int day = Integer.parseInt(request.getParameter("day"));
				
				session.setAttribute("docID", docID);
				session.setAttribute("hosp", request.getParameter("hosp"));
				session.setAttribute("time", request.getParameter("time"));
				session.setAttribute("day", day);
				session.setAttribute("date", dt);
				
				RequestDispatcher rd = request.getRequestDispatcher("payment.jsp");
				rd.forward(request, response);
				
			}
		}
		else {
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Selected date is not available!')");
			out.println("location='appointment.jsp';");
			out.println("</script>");
		}
		
	}

}
