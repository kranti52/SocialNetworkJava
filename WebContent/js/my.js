
$(function(){
	$('#match_error').hide();
	$('#email_error').hide();
	$('#validate_error').hide();
	$('#username_error').hide();
	$('#username_signup_error').hide();
	$('.button-checkbox').each(function(){
		var $widget = $(this),
		$button = $widget.find('button'),
		$checkbox = $widget.find('input:checkbox'),
		color = $button.data('color'),
		settings = {
			on: {
				icon: 'glyphicon glyphicon-check'
			},
			off: {
				icon: 'glyphicon glyphicon-unchecked'
			}
		};

		$button.on('click', function () {
			$checkbox.prop('checked', !$checkbox.is(':checked'));
			$checkbox.triggerHandler('change');
			updateDisplay();
		});

		$checkbox.on('change', function () {
			updateDisplay();
		});

		function updateDisplay() {
			/*var isChecked = $checkbox.is(':checked');
			// Set the button's state
			$button.data('state', (isChecked) ? "on" : "off");

			// Set the button's icon
			$button.find('.state-icon')
				.removeClass()
				.addClass('state-icon ' + settings[$button.data('state')].icon);

			// Update the button's color
			if (isChecked) {
				$button
					.removeClass('btn-default')
					.addClass('btn-' + color + ' active');
			}
			else
			{
				$button
					.removeClass('btn-' + color + ' active')
					.addClass('btn-default');
			}*/
		}
		function init() {
			updateDisplay();
			// Inject the icon if applicable
			/*if ($button.find('.state-icon').length == 0) {
				$button.prepend('<i class="state-icon '+ settings[$button.data('state')].icon + '"></i> ');
			}*/
		}
		init();
	});
});

function forcePopUp(){
	$(".modal").css("display","block");
}
function emailCheck(value) {
	if(value=="login") {
		var email=$('#email_login').val();
		$.ajax({
			url: "EmailValid",
			method: "POST",
			data: { 'email' : email},
			success: function(response){
				//console.log(response);
				if(response) {
					$('#username_error').hide();
				}
				else {
					$('#email_login').val("");
					$('#validate_error').hide();
					$('#username_error').show();
				}
			} 
		});
	}
	else {
		var email=$('#email_signup').val();
		$.ajax({
			url: "EmailValid",
			method: "POST",
			data: { 'email' : email},
			success: function(response){
				//console.log(response);
				if(response) {
					$('#username_signup_error').show();
					$('#email_signup').val("");
				}
				else {
					
					$('#username_signup_error').hide();
				}
			} 
		});
	}

}
function validate() {
	var email=$('#email_login').val();
	var password=$('#password_login').val();
	$.ajax({
		url: "PasswordCheck",
		method: "POST",
		data: { 'email' : email, 'password':password},
		success: function(response){
			console.log(response);
			if(response=="true"){
				document.forms["login_form"].submit();
				//console.log("really");
				//window.location.href="/Socialweb/Login?email="+email+"&password="+password;
			}
			$('#validate_error').show();
			return false;
		} 
	});
}
function password_check() {
	var password1=$('#password1').val();
	var password2=$('#password2').val();
	if(password1==password2) {
		document.forms["password_form"].submit();
	}
	else {
		$("#match_error").show();
	}
}
function forgot() {
	var email=$('#email_forgot').val();
	if ($('#email_error').css('display') == 'none') {
		$.ajax({
			url: "ForgotPassword",
			method: "POST",
			data: { 'email' : email},
			success: function(response){
				console.log(response);
				if(response=="true"){
					//alert("mail has been sent");
					$("#close_modal").click();
					alert("message has been sent");
				}
				$(".modal").css("display","none");
			} 
		});
	    
	}
	else {
		alert("This email doesn't exist");
	}
}
function forgotEmailCheck() {
		var email=$('#email_forgot').val();
		$.ajax({
			url: "EmailValid",
			method: "POST",
			data: { 'email' : email},
			success: function(response){
				console.log(email);
				console.log(response);
				if(response) {
					$('#email_error').hide();
				}
				else {
					$('#email_error').show();
				}
			} 
		});
	


}