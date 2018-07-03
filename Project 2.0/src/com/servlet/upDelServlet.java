package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.mHistory;
import com.service.MServices;

/**
 * Servlet implementation class upDelServlet
 */
@WebServlet("/upDelServlet")
public class upDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upDelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		System.out.println(action);

		if (request.getParameter("update_button") !=null) {
			
			mHistory mh= new mHistory();
			
			
			mh.setpid(request.getParameter("pid"));
			
			mh.setDesc(request.getParameter("desc"));
		
			MServices service = new MServices();
			service.updateMedhistory(mh);
			
			
			RequestDispatcher re = request.getRequestDispatcher("Viewmh.jsp");
			re.forward(request, response);
			
		    
		} else if (request.getParameter("delete_button") !=null) {
			
			response.setContentType("text/html");
			
			mHistory mh= new mHistory();
				
		
			mh.setpid(request.getParameter("pid"));
			mh.setdName(request.getParameter("did"));
			MServices service = new MServices();
			service.deleteMedHistory(mh);
			
			
			RequestDispatcher re = request.getRequestDispatcher("deleted.jsp");
			re.forward(request, response);
		    
		}
	}

}
