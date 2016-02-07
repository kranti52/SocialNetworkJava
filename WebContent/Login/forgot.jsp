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


	<div
		class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form role="form" name="password_form" action="save" method="POST" onsubmit="event.preventDefault();password_check();">
			<fieldset>
				<h2>Reset Your Password</h2>
				<hr class="colorgraph">
				<div id="match_error" style="color: red;">Your Both of
					Password doesn't match</div>
					<div class="form-group" hidden >
					<input type="email" name="email_reset" id="email_reset"
						class="form-control input-lg" placeholder="Email" value="<%=request.getAttribute("decrypt_email")%>">
				</div>
				<div class="form-group">
					<input type="password" name="password1" id="password1"
						class="form-control input-lg" placeholder="Enter your Password">
				</div>
				<div class="form-group">
					<input type="password" name="password2" id="password2"
						class="form-control input-lg" placeholder="Enter your Password">
				</div>
				<hr class="colorgraph">
				<div class="row">
					<div class="col-xs-6" id="login_button">
						<input type="submit" class="btn btn-lg btn-success btn-block"
							value="Sign In">
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

<jsp:include page="login_footer.jsp" />