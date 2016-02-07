<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.myapp.entity.FriendRequestEntity"%>
<%@page import="com.myapp.entity.ProfileEntity"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Profile</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/wall_style.css">
<link rel="stylesheet" href="css/rotating-card.css">
<!--  <link rel="stylesheet" href="css/bootstrap-responsive.min.css">
<link rel="stylesheet" href="../css/bootstrap-responsive.min.css">-->
<link rel="stylesheet" href="../css/wall_style.css">
<link rel="stylesheet" href="../css/rotating-card.css">

</head>
<body>
	<jsp:useBean id="entity" class="com.myapp.entity.ProfileEntity"
		scope="request">
	</jsp:useBean>
	<div id="cover_image">
		<!-- Navigation Bar -->
		<div class="searchbox">
			<input type="search" placeholder="Search......" name="search"
				class="searchbox-input" onkeyup="buttonUp();" required> <input
				type="submit" class="searchbox-submit" value=""> <span
				class="searchbox-icon"><i class="glyphicon glyphicon-search"
				style="margin: 25% auto;"></i></span><br>

		</div>
		<%
		List<HashMap<String, Object>> list_data=null;
		int itemCount=0;
			if (request.getAttribute("request_entity") != null) {
				list_data = (List<HashMap<String, Object>>) request
						.getAttribute("request_entity");
			
				itemCount = list_data.size();
			}
				if (request.getSession().getAttribute("user")
						.equals(entity.getUser_email())) {
		%>
		<div class="dropdown">
			<a id="dLabel" role="button" data-toggle="dropdown" data-target="#"
				href="#" onclick="forceOpen();"> 
				<%if(itemCount!=0){ %>
				<i
				class="glyphicon glyphicon-bell bell1"></i> 
				
				<span class="badge badge-notify">3</span>
				<%} %>
				<i
				class="glyphicon glyphicon-bell bell2"></i>
			</a>

			<ul class="dropdown-menu notifications pull" role="menu"
				aria-labelledby="dLabel">

				<div class="notification-heading">
					<h4 class="menu-title">Notifications</h4>
					<h4 class="menu-title pull-right">
						View all<i class="glyphicon glyphicon-circle-arrow-right"></i>
					</h4>
				</div>
				<li class="divider"></li>
				<div class="notifications-wrapper">
					<%
						if (request.getAttribute("request_entity") != null) {
									//List<HashMap<String, Object>> list_data = (List<HashMap<String, Object>>) request
									//		.getAttribute("request_entity");
									for (Map map : list_data) {
										byte[] bytes = null;
										String picture = null;
										FriendRequestEntity friend_request = (FriendRequestEntity) map
												.get("request");
										ProfileEntity sender = (ProfileEntity) map
												.get("sender");
										bytes = IOUtils.toByteArray(sender.getPicture());
										picture = new String(Base64.encodeBase64(bytes));
					%>
					<a class="content" href="profile/<%=sender.getUsername()%>">

						<div class="notification-item">
							<img src="data:image/jpg;base64,<%=picture%>"
								class="img-circle img-responsive" id="image" alt=""> <span><h2
									class="item-title"><%=sender.getFull_name()%></h2> <a
								class="btn btn-primary"
								href="AddReject?value=confirm&to=<%=request.getSession().getAttribute("user")%>&from=<%=sender.getUser_email()%>"
								type="button">Accept</a> <a class="btn btn-danger"
								href="AddReject?value=reject&to=<%=request.getSession().getAttribute("user")%>&from=<%=sender.getUser_email()%>"
								type="button">Reject</a></span>
							<!--  <p class="item-info">Marketing 101, Video Assignment</p>-->
						</div>

					</a>
					<%
						}
								}
							
					%>
				</div>
				<li class="divider"></li>
				<div class="notification-footer">
					<h4 class="menu-title">
						View all<i class="glyphicon glyphicon-circle-arrow-right"></i>
					</h4>
				</div>
			</ul>

		</div>
		<%
			}
		%>
		<div class="container-fluid text-right">
			<h2>
				Welcome
				<%=request.getSession().getAttribute("user")%></h2>
		</div>

		<div class="appendbox">
			<div id="search"></div>
		</div>

		<div class="navbar navbar-inverse navbar-twitch" id="navigation"
			role="navigation">

			<div class="container">

				<div class="navbar-header">
					<a class="navbar-brand" href="#"> <span class="small-nav">
							<span class="logo"> W</span>
					</span> <span class="full-nav"> Webster </span>
					</a>
				</div>

				<div class="">
					<ul class="nav navbar-nav">
						<li class="active"><a href="profile"> <span
								class="small-nav" data-toggle="tooltip" data-placement="right"
								title="Home"> <span class="glyphicon glyphicon-home"></span>
							</span> <span class="full-nav"> Home </span>
						</a></li>
						<li><a href="#about-us"> <span class="small-nav"
								data-toggle="tooltip" data-placement="right" title="About Us">
									<span class="fa fa-users"></span>
							</span> <span class="full-nav"> About Us </span>
						</a></li>

						<li><a href="message"> <span class="small-nav"
								data-toggle="tooltip" data-placement="right" title="Message">
									<span class="glyphicon glyphicon-envelope"></span>
							</span> <span class="full-nav"> Message</span>
						</a></li>
						<li><a href="#contact-us"> <span class="small-nav"
								data-toggle="tooltip" data-placement="right" title="Contact Us">
									<span class="glyphicon glyphicon-comment"></span>
							</span> <span class="full-nav"> Contact Us </span>

						</a></li>
						<li><a href="logout"> <span class="small-nav"
								data-toggle="tooltip" data-placement="right" title="Logout">
									<span class="glyphicon glyphicon-log-out"></span>
							</span> <span class="full-nav"> Logout </span>
						</a></li>

					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
		<button type="button"
			class="btn btn-default btn-xs navbar-twitch-toggle">
			<span class="glyphicon glyphicon-chevron-right nav-open"></span> <span
				class="glyphicon glyphicon-chevron-left nav-close"></span>
		</button>