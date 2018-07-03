<%@page import="com.model.Doctor"%>
<%@page import="com.service.DoctorService"%>
<%@page import = "com.service.AppService" %>
<%@page import = "java.util.ArrayList" %>
<%@page import = "com.model.Appointment" %>
<%@page import = "java.text.ParseException" %>
<%@page import = "java.text.SimpleDateFormat" %>
<%@page import = "java.util.Date" %>
<%@page import = "java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Channel History | MediCare</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="author" content="" />

  

	<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<!-- Magnific Popup -->
	<link rel="stylesheet" href="css/magnific-popup.css">
	<!-- Owl Carousel  -->
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/owl.theme.default.min.css">
	<!-- Flexslider  -->
	<link rel="stylesheet" href="css/flexslider.css">
	<!-- Flaticons  -->
	<link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">
	<!-- Theme style  -->
	<link rel="stylesheet" href="css/style.css">

	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
		
		<%
	
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			
			int userID = 0;
		
			if(session.getAttribute("uType") != "patient"){
				
				response.sendRedirect("login.jsp");
				
			}
			
			if( session.getAttribute("userID") != null){
				
				userID = (int) session.getAttribute("userID");
			}
			
			AppService app = new AppService();
			ArrayList <Appointment> appAL = new ArrayList<>();
			
			appAL = app.getApp(userID);
		
		%>
		
		
	<div class="colorlib-loader"></div>
	<!--Logo--><center>
									<div class = "logo">
										
										<img class="logo" src="logo.png" style="margin-top:20px; margin-bottom:1px" >
										
										<%
											if(session != null){
										
												String utype = (String) session.getAttribute("uType");
												String type = "patient";
												
												if((utype != null) && (utype.equals(type))){
										%>
										
												<div>
												<form method ="POST" action="user.jsp">
													<button action="#"><%= session.getAttribute("email") %></button>
												</form>
												<form method ="POST" action="Logout">
													<input type="submit" name="lout" value="Logout">
												</form>
											</div>
										
										<%	
												}
											}
										%>
										
									</div>
									</center>
									
									
	<div id="page">
	
	<nav class="colorlib-nav" role="navigation">
		<div class="top-menu">
			
			<div class="menu-wrap">
				<div class="container">
					<div class="row">
						<div class="col-xs-8">
							<div class="menu-1">
								<ul>
									<li ><a href="index.jsp">Home</a></li>
									<li class="active" class="has-dropdown">
										<a href="channelhis.jsp">Channel History</a>
										
									</li>
									<li><a href="service.jsp">Services</a></li>
									<li class="has-dropdown">
										<a href="about.jsp">About Us</a>
										
									</li>
									<% 
										if(session.getAttribute("uname") == null){
									%>
									
									<li class="has-dropdown">
										<a href="register.jsp">Register</a>
										
									</li>
									<li><a href="login.jsp">Login</a></li>
									<%
										}
									%>
								</ul>
							</div>
						</div>
						<div class="col-md-4">
							<p class="btn-cta"><a href="appointment.jsp"><span>Make an Appointment <i class="icon-calendar3"></i></span></a></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</nav>
	
	<aside id="colorlib-hero" class="hero">
		<div class="flexslider">
			<ul class="slides">
		   	<li style="background-image: url(images/img3.jpg);">
		   		<div class="overlay"></div>
		   		<div class="container">
		   			<div class="row">
			   			<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12 text-center slider-text">
			   				<div class="slider-text-inner">
			   					<h1>Channel History</h1>
									
			   				</div>
			   			</div>
			   		</div>
		   		</div>
		   	</li>
		  	</ul>
	  	</div>
	</aside>

	
	<div id="colorlib-contact">
		<div class="container">
			<div class="row">
				
				<div class="col-md-10 col-md-offset-1 animate-box">
					<h3>Channel History</h3>
					
					<table style="width:100%">
						<tr>
							<th>Appointment ID</th>
							<th>Date & time</th>
							<th>Hospital</th>
							<th>Doctor</th>
							<th></th>
						</tr>
					<%
						DoctorService ds = new DoctorService();
						Doctor doc = new Doctor();
						
					
						for(Appointment a : appAL){
					%>
						
							<tr>
								<td> <%=a.getAppID() %> </td>
								<td> <%=app.getDate(a.getDay()) + "   " + a.getTime()%> </td>
								<td> <%=a.getHosp() %> </td>
								<%
									//System.out.println(a.getPid());
									
									doc = ds.getdoctorbyId(a.getDid());
								
								%>
								<td> <%= doc.getName() %> </td>
								<td>
									<%
										//checking the date differences in app and now so the user can request to cancel app
										Date d = new Date();
										Date dt = a.getSelectedDate();
										
										long diff = app.getDateDiff(dt, null);
									    //System.out.println ((diff / (1000*60*60*24)));
									    
									    diff = diff / (1000*60*60*24);
									    
									    /*Calendar c = Calendar.getInstance();
									    c.setTime(dt);
									    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
										
									    System.out.println (dayOfWeek);*/
									    
									    if(diff < 0){
									%>
										
										<form method="Post" action="DeleteAppointment">
											<input type="hidden" name="appID" value="<%=a.getAppID() %>">
											<input type="hidden" name="pID" value="<%=a.getPid() %>">
											<input type="submit" value="Remove Appointment">
										</form> 
									<%
									    }	
									%>
								</td>
							</tr>
						
						
						
					<%
						}
					%>
					
					</table>
					
				</div>
			</div>
		</div>
	</div>

	
	
<footer id="colorlib-footer" role="contentinfo">
		<div class="overlay"></div>
		<div class="container">
			<div class="row row-pb-md">
				<div class="col-md-3 colorlib-widget">
					<h3>Medicare</h3>
					<p>MediCare is a service that offers convenient booking facilities for Doctor Appointments at leading hospitals in Sri Lanka.</p>
				</div>
				<div class="col-md-3 col-md-push-1 colorlib-widget">
					<h3>Navigation</h3>
					<ul class="colorlib-footer-links">
						<li><a href="#"><i class="icon-check"></i> Channel History</a></li>
						<li><a href="#"><i class="icon-check"></i> Hospitals</a></li>
						<li><a href="#"><i class="icon-check"></i> Donations</a></li>
						<li><a href="#"><i class="icon-check"></i> Services</a></li>
						<li><a href="#"><i class="icon-check"></i> About Us</a></li>
					</ul>
				</div>

				<div class="col-md-3 colorlib-widget">
					<h3>Our Services</h3>
					<ul class="colorlib-footer-links">
						<li><a href="#"><i class="icon-check"></i> Heart Surgery</a></li>
						<li><a href="#"><i class="icon-check"></i> Surgical Treatment</a></li>
						<li><a href="#"><i class="icon-check"></i> General Treatment</a></li>
						<li><a href="#"><i class="icon-check"></i> Medical Treatment</a></li>
						<li><a href="#"><i class="icon-check"></i> Help Desk</a></li>
					</ul>
				</div>

				<div class="col-md-3 colorlib-widget">
					<h3>Leave Us Message</h3>
					<form class="contact-form">
						<div class="form-group">
							<label for="name" class="sr-only">Name</label>
							<input type="name" class="form-control" id="name" placeholder="Name">
						</div>
						<div class="form-group">
							<label for="email" class="sr-only">Email</label>
							<input type="email" class="form-control" id="email" placeholder="Email">
						</div>
						<div class="form-group">
							<label for="message" class="sr-only">Message</label>
							<textarea class="form-control" id="message" rows="3" placeholder="Message"></textarea>
						</div>
						<div class="form-group">
							<input type="submit" id="btn-submit" class="btn btn-primary btn-send-message btn-md" value="Send Message">
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="row copyright">
			<div class="col-md-12 text-center">
				<p>
					<small class="block"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> Tech Ninjas</i><a href="https://colorlib.com" target="_blank"></a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
</small> 
				
				</p>
			</div>
		</div>
	</footer>
	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Stellar Parallax -->
	<script src="js/jquery.stellar.min.js"></script>
	<!-- Carousel -->
	<script src="js/owl.carousel.min.js"></script>
	<!-- Flexslider -->
	<script src="js/jquery.flexslider-min.js"></script>
	<!-- countTo -->
	<script src="js/jquery.countTo.js"></script>
	<!-- Magnific Popup -->
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/magnific-popup-options.js"></script>
	<!-- Sticky Kit -->
	<script src="js/sticky-kit.min.js"></script>
	<!-- Main -->
	<script src="js/main.js"></script>

	</body>
</html>

