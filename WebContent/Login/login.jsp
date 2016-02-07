<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="login_header.jsp" />
<div class="container" id="login_form">

	<!--  <div class="row" style="margin-top: 20px">
		<div
			class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<form role="form" action="Register" method="POST">
				<fieldset>
					<h2>Please Sign In</h2>
					<hr class="colorgraph">
					<div class="form-group">
						<%if (request.getParameter("exist") != null) {%>
						<h5 style="text-color: Red;">Already exist</h5>
						<%} else {
			}%>

						<input type="email" name="email" id="email"
							class="form-control input-lg" placeholder="Email Address">
					</div>
					<div class="form-group">
						<input type="password" name="password" id="password"
							class="form-control input-lg" placeholder="Password">
					</div>

					<span class="button-checkbox"> 
						<a href="" class="btn btn-link pull-right" data-target="#pwdModal"
						data-toggle="modal">Forgot Password?</a>
					</span> <br>
					<hr class="colorgraph">
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<input type="submit" class="btn btn-lg btn-success btn-block"
								value="Sign In">
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6">
							<a href="" class="btn btn-lg btn-primary btn-block"
								data-target="#registerModal" data-toggle="modal">Register</a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>-->

	<ul class="nav nav-tabs text-center" id="list" role="tablist">
		<li class="active "><a href="#login" role="tab" data-toggle="tab">
				<icon class="fa fa-home"></icon> Login
		</a></li>
		<li><a href="#signup" role="tab" data-toggle="tab"> <i
				class="fa fa-user"></i> Sign Up
		</a></li>

	</ul>

	<div class="tab-content">
		<div class="tab-pane fade active in" id="login">
			<div
				class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<form role="form" name="login_form" action="Login" method="POST" onsubmit="event.preventDefault(); return validate();">
					<fieldset>
						<h2>Please Sign In</h2>
						<hr class="colorgraph">
						<div id="username_error" style="color:red;">Username doesn't exist</div>
						<div id="validate_error" style="color:red;">Your username and Password doesn't match</div>
						<div class="form-group">
							<%
								if (request.getParameter("exist") != null) {
							%>
							<h5 style="text-color: Red;">Already exist</h5>
							<%
								} else {
								}
							%>

							<input type="email" name="email_login" id="email_login"
								class="form-control input-lg" placeholder="Email Address" onchange="emailCheck('login');">
						</div>
						<div class="form-group">
							<input type="password" name="password_login" id="password_login"
								class="form-control input-lg" placeholder="Password">
						</div>

						<span class=""> <!--<button type="button" class="btn" data-color="info">Remember Me</button> 
							<input type="checkbox" name="remember_me" id="remember_me" checked="checked" class="hidden"> -->
							<a href="" class="btn btn-link pull-left" onclick="forcePopUp()"
							data-target="#pwdModal" data-toggle="modal">Forgot Password?</a>

						</span> <br>
						<hr class="colorgraph">
						<div class="row">
							<div class="col-xs-6" id="login_button">
								<input type="submit" class="btn btn-lg btn-success btn-block"
									value="Sign In" >
							</div>
							<!--  <div class="col-xs-6 col-sm-6 col-md-6">
							<a href="" class="btn btn-lg btn-primary btn-block"
								data-target="#registerModal" data-toggle="modal">Register</a>
						</div>-->
						</div>
					</fieldset>
				 </form>
			</div>
		</div>
		<div class="tab-pane fade " id="signup">
			<div
				class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
				<form role="form" action="Register" method="POST">
					<fieldset>
						<h2>Please Sign up</h2>
						<hr class="colorgraph">
						<div id="username_signup_error" style="color:red;">Username already exist</div>
						<div class="form-group">
							<%
								if (request.getParameter("exist") != null) {
							%>
							<h5 style="text-color: Red;">Already exist</h5>
							<%
								} else {
								}
							%>

							<input type="email" name="email" id="email_signup"
								class="form-control input-lg" placeholder="Email Address" onchange="emailCheck('signup');">
						</div>
						<div class="form-group">
							<input type="password" name="password" id="password"
								class="form-control input-lg" placeholder="Password">
						</div>

						<hr class="colorgraph">

						<div class="row " style="text-align: center">
							<div class="col-xs-6 col-sm-6 col-md-6 " id="signup_button">
								<input type="submit" class="btn btn-lg btn-primary btn-block "
									value="Sign Up">
							</div>
							<!--  <div class="col-xs-6 col-sm-6 col-md-6">
							<a href="" class="btn btn-lg btn-primary btn-block"
								data-target="#registerModal" data-toggle="modal">Register</a>
						</div>-->
						</div>

					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="login_footer.jsp" />