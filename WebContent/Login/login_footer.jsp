<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!--modal-->
<div id="pwdModal" class="modal fade" role="dialog" tabindex=-1 style="">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h1 class="text-center">What's My Password?</h1>
			</div>
			<div class="modal-body">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="text-center">

								<p>If you have forgotten your password you can reset it
									here.</p>
								<div class="panel-body">
									<fieldset>
									<div id="email_error" style="color:red">This email doesn't exist.</div>
										<div class="form-group">
											<input class="form-control input-lg"
												placeholder="E-mail Address" onkeyup="forgotEmailCheck()" id="email_forgot" name="forgot_email" type="email">
										</div>
										<input class="btn btn-lg btn-primary btn-block"
											value="Submit" onclick ="forgot()" type="submit">
									</fieldset>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-md-12">
					<button class="btn" id="close_modal" data-dismiss="modal" aria-hidden="true">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-alpha1/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<%
	if (request.getParameter("exist") != null) {
%>
<script src="../js/my.js"></script>
<%
	} else {
%>
<script src="js/my.js"></script>
<%
	}
%>

</body>
</html>