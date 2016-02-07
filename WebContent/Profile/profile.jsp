<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="profile_header.jsp" />
<div class="container-fluid profile_body">
	<form class="form-horizontal text-center" action="profile" method="POST" enctype="multipart/form-data" style="width: 25%; margin: 10% auto">

		<div class="image_style">


			<img id="preview" src="" height="100px" width="100px"
				class="img-circle" /> <input type="file" class="form-control"
				id="photo" placeholder="Profile Picture" name="profile_pic"
				onchange="previewImage(this)"  accept="image/*">
		</div>
			<div class="form-group">
				<label for="full_name" class="col-sm-2 control-label">Full
					Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="full_name"
						id="full_name" placeholder="Full Name"  required>
				</div>
			</div>
		
		<div class="form-group">
			<label for="username" class="col-sm-2 control-label">Username</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="username"
					id="username" placeholder="Username" required>
			</div>
		</div>
		<div class="form-group">
			<label for="dob" class="col-sm-2 control-label">Date of Birth</label>
			<div class="col-sm-10">
				<input type="date" class="form-control" name="dob" id="dob"
					placeholder="Date of birth" required>
			</div>
		</div>
		<div class="form-group">
			<label for="occupation" class="col-sm-2 control-label">Occupation</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="occupation"
					id="occupation" placeholder="Occupation">
			</div>
		</div>
		<div class="form-group">
			<label for="hometown" class="col-sm-2 control-label">Home
				Town</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="home_town"
					id="hometown" placeholder="Home Town" required>
			</div>
		</div>
		<div class="form-group">
			<label for="current_city" class="col-sm-2 control-label">Current
				City</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="current_city"
					id="current_city" placeholder="Current City" required>
			</div>
		</div>
			<div class="form-group">
			<label for="current_city" class="col-sm-2 control-label">Experience
				</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="experience"
					id="experience" placeholder="Experience details" required>
			</div>
		</div>
			<div class="form-group">
			<label for="current_city" class="col-sm-2 control-label">Area Of Interest</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="interest"
					id="interest" placeholder="Write in comma-separated format" required>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</div>
	</form>
</div>
<jsp:include page="profile_footer.jsp" />