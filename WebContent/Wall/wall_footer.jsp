<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!-- Footer -->


<div id="footer_universal">
	<div class="container">
		<div class="row">
			<div id="hidden_footer"><h3 class="footertext text-center">About Us:</h3>
			<br>
			<div class="col-md-4 text-center">

				<img
					src="//graph.facebook.com/817289565033392/picture?width=100&height=100"
					class="img-circle" alt="the-brains"> <br>
				<h4 class="footertext">Programmer</h4>
				<p class="footertext">
					You can thank all the crazy programming here to this guy.<br>
				</p>

			</div>
			<div class="col-md-4 text-center">

				<img
					src="//graph.facebook.com/817289565033392/picture?width=100&height=100"
					class="img-circle" alt="..."> <br>
				<h4 class="footertext">Artist</h4>
				<p class="footertext">
					All the images here are hand drawn by this man.<br>
				</p>
			</div>
			<div class="col-md-4 text-center">

				<img
					src="//graph.facebook.com/817289565033392/picture?width=100&height=100"
					class="img-circle" alt="..."> <br>
				<h4 class="footertext">Designer</h4>
				<p class="footertext">
					This pretty site and the copy it holds are all thanks to this guy.<br>
				</p>

			</div>
			</div>
			<div class="row text-center" onmouseover="show_footer()" onmouseout="hide_footer();">
				<p class="">
					  <h3>Our Team</h3>
				<p class="">Copyright 2015</p>
			</div>
		</div>
	</div>
</div>




<!--modal-->
<div id="Modal" class="modal fade" role="dialog" tabindex=-1 style="">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="modal_close" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h1 class="text-center">Send Message</h1>
			</div>
			<div class="modal-body">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="text-center">

								<p>Enter your Message</p>
								<div class="panel-body">
									<fieldset>
										<div class="form-group">
										<textarea class="form-control input-lg"
												placeholder="Type your message here" id="message" name="message" ></textarea>
										</div>
										<input class="btn btn-lg btn-primary btn-block"
											value="Send My Message" onclick="message();" type="submit">
									</fieldset>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-md-12">
					<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</div>



<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-alpha1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="js/wall.js"></script>

<script src="../js/wall.js"></script>
</body>
</html>