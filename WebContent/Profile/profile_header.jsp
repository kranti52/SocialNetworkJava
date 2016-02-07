<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Profile</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/profile_style.css">
</head>
<body>
<%
String email=(String)session.getAttribute("user");
%>

	<!-- Navigation Bar -->
	<div class="container-fluid text-right">Welcome<%= email%></div>
	<!--  <div class="navbar navbar-inverse navbar-twitch" role="navigation">

		<div class="container">

			<div class="navbar-header">
				<a class="navbar-brand" href="#"> <span class="small-nav">
						<span class="logo"> W</span>
				</span> <span class="full-nav"> Webster </span>
				</a>
			</div>

			<div class="">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#"> <span class="small-nav"
							data-toggle="tooltip" data-placement="right" title="Home">
								<span class="glyphicon glyphicon-home"></span>
						</span> <span class="full-nav"> Home </span>
					</a></li>
					<li><a href="#about-us"> <span class="small-nav"
							data-toggle="tooltip" data-placement="right" title="About Us">
								<span class="fa fa-users"></span>
						</span> <span class="full-nav"> About Us </span>
					</a></li>
					<li><a href="#contact-us"> <span class="small-nav"
							data-toggle="tooltip" data-placement="right" title="Contact Us">
								<span class="glyphicon glyphicon-comment"></span>
						</span> <span class="full-nav"> Contact Us </span>
					</a></li>
					<li><a href="#logout"> <span class="small-nav"
							data-toggle="tooltip" data-placement="right" title="Logout">
								<span class="glyphicon glyphicon-log-out"></span>
						</span> <span class="full-nav"> Logout </span>
					</a></li>
				</ul>
			</div>
		
		</div>
	</div>
	<button type="button"
		class="btn btn-default btn-xs navbar-twitch-toggle">
		<span class="glyphicon glyphicon-chevron-right nav-open"></span> <span
			class="glyphicon glyphicon-chevron-left nav-close"></span>
	</button>-->