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

import com.model.Patient;
import com.service.PatientService;

@WebServlet("/UpdatePatient")
public class UpdatePatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UpdatePatient() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		int userID = (int) session.getAttribute("userID");
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String add = request.getParameter("add");
		String cont1 = request.getParameter("c1");
		String cont2 = request.getParameter("c2");
		
		//for cont update
		String cont1o = request.getParameter("c1o");
		String cont2o = request.getParameter("c2o");
		
		//System.out.println(cont1 + " " + cont2 + " " + cont1o + " " + cont2o);
		
		Patient p = new Patient();
		
		p.setAdd(add);
		p.setCont1(cont1);
		p.setCont2(cont2);
		p.setAge(age);
		p.setUserID(userID);
		p.setName(name);
		p.setCont1o(cont1o);
		p.setCont2o(cont2o);
		
		PatientService ps = new PatientService();
		
		if(ps.updatePatient(p)) {
		
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Update Successful !');");
			out.println("</script>");
			
			RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
			rd.forward(request, response);
		}
		else {
			System.out.println("Error");
		}

	}

}
