package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Patient;
import com.service.PatientService;


@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Registration() {
        super();
        
    }  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");

		String uname = request.getParameter("uname");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String NIC = request.getParameter("NIC");
		String email = request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));
		String add = request.getParameter("address");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		
		//System.out.println(uname+name+pass+age+phone+add+email+NIC);
		
		Patient patient = new Patient();
	
		patient.setUname(uname);
		patient.setPass(pass);
		patient.setName(name);
		patient.setNIC(NIC);
		patient.setEmail(email);
		patient.setAge(age);
		patient.setAdd(add);
		patient.setCont1(phone1);
		patient.setCont2(phone2);
		
		PatientService PS = new PatientService();
		
		int i = PS.check(patient);
		if(i == 0) {
			
			PS.addPatient(patient);
			
			response.sendRedirect("regsuc.jsp");
		}
		else
		{
			response.sendRedirect("regerror1.jsp");
			System.out.println("dji");
		}
		
		
		
		
	}


	
}


