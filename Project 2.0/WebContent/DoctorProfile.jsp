<%@ page import ="com.service.DoctorService" %>
<%@ page import = "com.model.Doctor"%>
<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE HTML>
<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Doctor Profile | MediCare</title>
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
	<!--Logo--><center>
									<div class = "logo">
										
										<img class="logo" src="logo.png" style="margin-top:20px; margin-bottom:1px" >
										
										<div>
												<form method ="POST" action="user.jsp">
													<button action="#"><%= session.getAttribute("uname") %></button>
												</form>
												<form method ="POST" action="Logout">
													<input type="submit" name="lout" value="Logout">
												</form>
											</div>
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
									<li class="active"><a href="serviced.jsp">Services</a></li>
									<li class="has-dropdown">
										<a href="aboutd.jsp">About Us</a>
										
									</li>	
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</nav>
	
	<aside id="colorlib-hero" class="hero">
		<div class="flexslider">
			<ul class="slides">
		   	<li style="background-image: url(images/img2.jpg);">
		   		<div class="overlay"></div>
		   		<div class="container">
		   			<div class="row">
			   			<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12 text-center slider-text">
			   				<div class="slider-text-inner">
			   					<h1>Doctor Profile</h1>
								
			   				</div>
			   			</div>
			   		</div>
		   		</div>
		   	</li>
		  	</ul>
	  	</div>
	</aside>
	<br><br>
	
		<%
	
	Doctor doc = new Doctor();
	
	String uname,MID, Name, NIC, Address, Specialization, Email ;
	int id;
	if(session.isNew()==false) 
	{
		DoctorService ds = new DoctorService();
		uname = session.getAttribute("uname").toString();		
		
		doc = ds.getDoctorByUId(uname);
		id = doc.getUserID();
		MID = doc.getMID();
		Name =	doc.getName();
		NIC = doc.getNIC();
		Address = doc.getAdd();
		Specialization = doc.getSpec();
		Email = doc.getEmail();
		ArrayList<String> hArr = ds.getHosp(id);
		ArrayList<String> cArr = ds.getCont(id);
		
	%>
	<center>
		<table>
			<form action="updateDoctor" method="GET">
				<tr>
					<td>MID	:</td><td><%= MID %></td>
				</tr>	
				<tr>
					<td>Name : </td><td><input type="text" name="dName" value="<%= Name%>"></td>
				</tr>
				<tr>	
					<td>NIC 	:   </td><td><%= NIC%></td>
				</tr>
				<tr>
					<td>Address 	: </td><td><input type="text" name="dAdd" value="<%= Address%>"></td>
				</tr>
				<tr>
					<td>Specialization	: </td><td><%= Specialization%></td>
				</tr>
				<tr>	
					<td>Hospitals	: </td>
				
										<td><%for(String s: hArr){%>		
															<p><%= s %></p>
														<% }%></td>
				</tr>
				<tr>
					<td>Email	: </td><td><%= Email%></td>
				</tr>
				<tr>
					<td>Contacts	:</td><td><%for(String s: cArr){%>		
															<p><%= s %></p>
														<% }%></td>
				<tr>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Update"/></td>
				</tr>	
			</form>	
		</table>
			
	<br>
	<br>	  
	<% 
		}
	%>
	
	</center>
	
<center>
	<table>	
	<tr>
			<th>
			<a href="indexaddmh.jsp"><img src="images/medb.png">
			<p>  Create Medical History   </p>
		     </th>
			
			<th>|</th>
		     <th>|</th>
			<th>|</th>
			<th>|</th>
		     
		     <th>
			<a href="myAppointments.jsp"><img class="cn" src="images/hos.png">
			<p>   My Appointments   </p>
		     </th>
		     <th>|</th>
		     <th>|</th>
			<th>|</th>
		     <th>|</th>
		     <th>
			<a href="Viewmh.jsp"><img class="cn" src="images/steth2.png">
			<p>   View Medical History   </p>
		     </th>
		     
		     </tr>
	</table>		
	</center>	
	
	<br><br>		
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

