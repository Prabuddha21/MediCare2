<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>MediCare | Channel Your Doctor Online</title>
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
		
	<div class="colorlib-loader"></div>
	<!--Logo-->
									<center>
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
									<li class="active"><a href="index.jsp">Home</a></li>
									<li class="has-dropdown">
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
	
	<aside id="colorlib-hero">
		<div class="flexslider">
			<ul class="slides">
		   	<li style="background-image: url(images/img_bg_6.jpg);">
		   		<div class="overlay"></div>
		   		<div class="container">
		   			<div class="row">
			   			<div class="col-md-8 col-md-offset-2 col-md-pull-2 slider-text">
			   				<div class="slider-text-inner">
			   					<h1><strong>MediCare</strong></h1>
									<h2 class="doc-holder">Channel Your Doctor Online</h2>
									<h2>MediCare is a service that offers convenient booking facilities for Doctor Appointments at leading hospitals in Sri Lanka.</h2>
									<p><a class="btn btn-primary btn-lg" href="appointment.html">Make an Appointment</a></p>
			   				</div>
			   			</div>
			   		</div>
		   		</div>
		   	</li>
		   	<li style="background-image: url(images/img_bg_5.jpg);">
		   		<div class="overlay"></div>
		   		<div class="container">
		   			<div class="row">
			   			<div class="col-md-8 col-md-offset-2 col-md-pull-2 slider-text">
			   				<div class="slider-text-inner">
			   					<h1><strong>MediCare</strong></h1>
									
									<h2>Now you can attend to your health requirements and channel over 1000 specialists with us.</h2>
									<p><a class="btn btn-primary btn-lg" href="appointment.html">Make an Appointment</a></p>
			   				</div>
			   			</div>
			   		</div>
		   		</div>
		   	</li>
		   	<li style="background-image: url(images/img_bg_1.jpg);">
		   		<div class="overlay"></div>
		   		<div class="container">
		   			<div class="row">
			   			<div class="col-md-8 col-md-offset-2 col-md-pull-2 slider-text">
			   				<div class="slider-text-inner">
			   					<h1>Have an Access to <strong>a Health Professional at any time</strong></h1>
									<h2></h2>
									<p><a class="btn btn-primary btn-lg btn-learn" href="appointment.html">Make an Appointment</a></p>
			   				</div>
			   			</div>
			   		</div>
		   		</div>
		   	</li>
		   	<li style="background-image: url(images/img_bg_2.jpg);">
		   		<div class="overlay"></div>
		   		<div class="container">
		   			<div class="row">
			   			<div class="col-md-8 col-md-offset-2 col-md-pull-2 slider-text">
			   				<div class="slider-text-inner">
			   					<h1><strong>Anytime, Anywhere</strong></h1>
									<h2>Medicare enable users to channel doctors/consultants at anytime, from anywhere.</h2>
									<p><a class="btn btn-primary btn-lg btn-learn" href="appointment.html">Make an Appointment</a></p>
			   				</div>
			   			</div>
			   		</div>
		   		</div>
		   	</li>		   	
		  	</ul>
	  	</div>
	</aside>

	<div id="colorlib-intro">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="intro animate-box">
						<div class="intro-grid color-1">
							<span class="icon"><i class="flaticon-hospital"></i></span>
							<h3>Hospitals</h3>
							<p>Far far away, behind the word mountains, far from the countries Vokalia</p>
							
						</div>
						<div class="intro-grid color-2">
							<span class="icon"><i class="flaticon-healthy-1"></i></span>
							<h3>Qualified Doctors</h3>
							<p>MediCare Offers 100+ Qualified Doctors islandwide</p>
							
						</div>
						<div class="intro-grid color-2">
							<span class="icon"><i class="flaticon-sign"></i></span>
							<h3>Channel History</h3>
							<p>With MediCare you can view your channel history</p>
							
						</div>
						<div class="intro-grid color-3">
							<span class="icon"><i class="flaticon-ambulance"></i></span>
							<h3>Emergency Services</h3>
							<p>MediCare provides efficient ambulance services 24/7</p>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div id="colorlib-services">
		<div class="container">
			<div class="row animate-box">
				<div class="col-md-6 col-md-offset-3 text-center colorlib-heading">
					<h2>Our Services</h2>
					<p>Beyond the ordinary.</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 animate-box">
					<div class="services">
						<span class="icon">
							<i class="flaticon-healthy-1"></i>
						</span>
						<div class="desc">
							<h3><a href="#">Qualified Doctors</a></h3>
							<p>Our medical team comprises of highly Qualified Consultants. Physicians, Surgeons, Anesthetists and well trained nursing staff.</p>
						</div>
					</div>
				</div>
				<div class="col-md-4 animate-box">
					<div class="services">
						<span class="icon">
							<i class="flaticon-hospital"></i>
						</span>
						<div class="desc">
							<h3><a href="#">Easy Booking</a></h3>
							<p>Booking an appointment with your doctor becomes much easier and simpler with MediCare.</p>
						</div>
					</div>
				</div>
				<div class="col-md-4 animate-box">
					<div class="services">
						<span class="icon">
							<i class="flaticon-ambulance"></i>
						</span>
						<div class="desc">
							<h3><a href="#">Emergency Services</a></h3>
							<p>MediCare provides efficient ambulance services and emergency medical care. With staff on call 24 hours a day.</p>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	

	<div id="colorlib-counter" class="colorlib-counters" style="background-image: url(images/img_bg_2.jpg);" data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<div class="row">
						<div class="col-md-3 col-sm-6 text-center animate-box">
							<span class="icon"><i class="flaticon-healthy"></i></span>
							<span class="colorlib-counter js-counter" data-from="0" data-to="3297" data-speed="5000" data-refresh-interval="50"></span>
							<span class="colorlib-counter-label">Satisfied Customer</span>
						</div>
						<div class="col-md-3 col-sm-6 text-center animate-box">
							<span class="icon"><i class="flaticon-hospital"></i></span>
							<span class="colorlib-counter js-counter" data-from="0" data-to="378" data-speed="5000" data-refresh-interval="50"></span>
							<span class="colorlib-counter-label">Hospitals</span>
						</div>
						<div class="col-md-3 col-sm-6 text-center animate-box">
							<span class="icon"><i class="flaticon-healthy-1"></i></span>
							<span class="colorlib-counter js-counter" data-from="0" data-to="400" data-speed="5000" data-refresh-interval="50"></span>
							<span class="colorlib-counter-label">Qualified Doctor</span>
						</div>
						<div class="col-md-3 col-sm-6 text-center animate-box">
							<span class="icon"><i class="flaticon-ambulance"></i></span>
							<span class="colorlib-counter js-counter" data-from="0" data-to="30" data-speed="5000" data-refresh-interval="50"></span>
							<span class="colorlib-counter-label">Ambulances </span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<br>
	
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