package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Doctor;
import com.service.DoctorService;

/**
 * Servlet implementation class updateDoctor
 */
@WebServlet("/updateDoctor")
public class updateDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public updateDoctor() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Doctor doc = new Doctor();
		
		HttpSession session = request.getSession();
		int id =(int)session.getAttribute("docID");
		doc.setUserID(id);
		doc.setName(request.getParameter("dName"));
		doc.setAdd(request.getParameter("dAdd"));
		
		DoctorService ds = new DoctorService();
		ds.updateDoc(doc);
		
		RequestDispatcher re = request.getRequestDispatcher("DoctorProfileUpjsp.jsp");
		re.forward(request, response);
	}

	

}
