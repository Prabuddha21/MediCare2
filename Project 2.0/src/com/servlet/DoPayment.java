package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Appointment;
import com.model.Payment;
import com.service.AppService;
import com.service.DoctorService;
import com.service.PayService;
import com.util.Email;

@WebServlet("/DoPayment")
public class DoPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DoPayment() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PayService ps = new PayService();
		
		
		int docID = (int) session.getAttribute("docID");
		int day = (int) session.getAttribute("day");
		int pid = (int) session.getAttribute("userID");
		
		String date = (String) session.getAttribute("date");
		String time = (String) session.getAttribute("time");
		String hospital = (String) session.getAttribute("hosp");
		
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
		
		Date appDate = null;
		
		try {
			appDate = f1.parse(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//card details
		String cardname = request.getParameter("cardname");
		String cardnum = request.getParameter("cardnum");
		String expdate = request.getParameter("expdate");
		String csv = request.getParameter("csv");
		
		PrintWriter out = response.getWriter();
		
		System.out.println(docID + day + date + time + hospital + pid);
		
		
		if(!ps.checkCard(cardname, cardnum, expdate)) {
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Invalid Card Details !')");
			out.println("location='payment.jsp';");
			out.println("</script>");
			
			//RequestDispatcher rd = request.getRequestDispatcher("payment.jsp");
			//rd.forward(request, response);
		}
		else {
			
			DoctorService ds = new DoctorService();
			
			//getting the amount for the currently seleted appoinment
			double amount = ps.getAmount(ds.getDocRate(docID, hospital, day, time));
					
			//payment record
			Payment pay = new Payment();
			
			pay.setAmount(amount); 
			pay.setPid(pid);
			
			//appointment details
			Appointment app = new Appointment();
			
			app.setDate(appDate);
			app.setDay(day);
			app.setDid(docID);
			app.setPid(pid);
			app.setTime(time);
			app.setHosp(hospital);
			
			AppService as = new AppService();
			
			if(as.addApp(app, pay) == true){
				
				session.removeAttribute("docID");
				session.removeAttribute("date");
				session.removeAttribute("day");
				session.removeAttribute("time");
				session.removeAttribute("hosp");
				
				System.out.println(session.getAttribute("uType"));
				
				//email variables 
				String email = (String) session.getAttribute("email");
				String body = "Your appointment has been successfully added and the payment transaction has been successful ! \n\n\t\t\t Amount : Rs."+ amount +"\n\n**(Rs. 250 tax has beed added to the amount)";
				
				
				Email e = new Email(email, "Medicare Appointment & Payment Details", body);
				
				request.setAttribute("amount", amount);
				
				RequestDispatcher rd = request.getRequestDispatcher("paymentsuc.jsp");
				rd.forward(request, response);
				
				//response.sendRedirect("index.jsp");
			}
			else {
				
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Transaction Failed, Try again !')");
				out.println("location='payment.jsp';");
				out.println("</script>");
				
			}
		}
			
			
		
	}

}
