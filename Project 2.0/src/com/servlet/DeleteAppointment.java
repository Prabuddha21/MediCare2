package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.Email;

@WebServlet("/DeleteAppointment")
public class DeleteAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DeleteAppointment() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		int appID = Integer.parseInt(request.getParameter("appID"));
		int pID = Integer.parseInt(request.getParameter("pID"));
		
		String email = "ruzaikmohomad@gmail.com";
		String body = "Patient ID : "+ pID + " has reuqested a removel of the appointmentID : " + appID + ". Take necessary actions and inform the user about it.";
		
		Email e = new Email(email, "Appointment deletion request", body);
		
		
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Your appointment removel request has been received, we will nofify you about the status of your request !')");
		out.println("location='channelhis.jsp';");
		out.println("</script>");
		
		//response.sendRedirect("channelhis.jsp");
		
	}

}
