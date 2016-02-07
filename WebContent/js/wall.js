/**
 * 
 */
/*$( window).resize(function() {
	var $doc_width=$( window ).width();
	console.log($("#navigation").width());
	//if(parseInt($("#navigation").width())== '50') {
		var percent=((($doc_width)-$("#navigation").width())/(2*$doc_width))*100;
		console.log(percent+'%');
		 // document.getElementById("universal_footer").style.width = (percent*2)+'%';
	  document.getElementById("frame1").style.width = percent+'%';
        document.getElementById("frame2").style.width = percent+'%';
	/*}
	else {
		var percent=((($doc_width)-50)/(2*$doc_width))*100;

		  document.getElementById("frame1").style.width = percent+'%';
	        document.getElementById("frame2").style.width = percent+'%';
	}
});*/


$(document).ready(function() {
	$('.appendbox').hide();
	$('#hidden_footer').hide();
	$('.navbar-nav [data-toggle="tooltip"]').tooltip();
	$('.navbar-twitch-toggle').on('click', function(event) {
		event.preventDefault();
		$('.navbar-twitch').toggleClass('open');

	});

	$('.nav-style-toggle').on('click', function(event) {
		event.preventDefault();
		var $current = $('.nav-style-toggle.disabled');
		$(this).addClass('disabled');
		$current.removeClass('disabled');

		$('.navbar-twitch').removeClass('navbar-'+$current.data('type'));
		$('.navbar-twitch').addClass('navbar-'+$(this).data('type'));
	});
	var submitIcon = $('.searchbox-icon');
	var inputBox = $('.searchbox-input');
	var searchBox = $('.searchbox');
	var isOpen = false;
	submitIcon.click(function(){
		if(isOpen == false){
			$('.appendbox').show();
			if($(".dropdown-menu").css("display","block")) {
				$(".dropdown-menu").hide();
			}
			searchBox.addClass('searchbox-open');
			inputBox.focus();
			isOpen = true;
		} else {
			$('.appendbox').hide();
			searchBox.removeClass('searchbox-open');
			inputBox.focusout();
			isOpen = false;
		}
	});  
	submitIcon.mouseup(function(){
		return false;
	});
	searchBox.mouseup(function(){
		return false;
	});
	$(document).mouseup(function(){
		if($(".dropdown-menu").css("display","block")) {
			$(".dropdown-menu").hide();
		}
		if(isOpen == true){
			$('.searchbox-icon').css('display','block');
			submitIcon.click();
		}
	});
});
/*$(document).ready(function() {
	$('#hidden_footer').hide();
	var $document_width=$( document ).width();
	var frame_percent=((parseInt($document_width)-50)/(2*$document_width))*100;
    document.getElementById("frame1").style.marginLeft = '50px';
    //document.getElementById("universal_footer").style.marginLeft = '50px';
	  document.getElementById("frame1").style.width = frame_percent+'%';
        document.getElementById("frame2").style.width = frame_percent+'%';
	$('.navbar-nav [data-toggle="tooltip"]').tooltip();
	$('.navbar-twitch-toggle').on('click', function(event) {
		event.preventDefault();
		$('.navbar-twitch').toggleClass('open');


		var $doc_width=$( document ).width();
		console.log($doc_width);
		if(parseInt($("#frame1").css("margin-left"))== '50') {
			var percent=((parseInt($doc_width)-240)/(2*$doc_width))*100;
			console.log(percent+'%');
		 document.getElementById("frame1").style.marginLeft = '240px';
		 document.getElementById("universal_footer").style.marginLeft = '240px';
		  document.getElementById("frame1").style.width = percent+'%';
	        document.getElementById("frame2").style.width = percent+'%';
		}
		else {
			var percent=((parseInt($doc_width)-50)/(2*$doc_width))*100;
	        document.getElementById("frame1").style.marginLeft = '50px';
	        document.getElementById("universal_footer").style.marginLeft = '50px';
			  document.getElementById("frame1").style.width = percent+'%';
		        document.getElementById("frame2").style.width = percent+'%';
		}
	});

	$('.nav-style-toggle').on('click', function(event) {
		event.preventDefault();
		var $current = $('.nav-style-toggle.disabled');
		$(this).addClass('disabled');
		$current.removeClass('disabled');

		$('.navbar-twitch').removeClass('navbar-'+$current.data('type'));
		$('.navbar-twitch').addClass('navbar-'+$(this).data('type'));
	});
});*/
$('.searchbox').mouseup(function(){
	console.log("kranti");
});


function show_footer() {
	$('#hidden_footer').show();
}
function hide_footer() {
	$('#hidden_footer').hide();
}
function status_prepend() {
	var status=$('#comment1').val();
	var to=$('#person').html();
	var from=$('#user').html();
	console.log(status);
	if(status!=""){
		$( "<div class='status' ><i class='glyphicon glyphicon-trash' style='float:right;'></i><div id='wrap'>"+status+"</div></div>" ).append( "#status_append" );
		$.ajax({
			url: "Status",
			method: "POST",
			data: { 'to' : to,'from':from,'content':status},
			success: function(response){
				//console.log(response);
				if(response) {
					console.log(response);
					if(response=="true") {
						window.location.reload();
					}

				}
				else {

				}
			} 
		});
	}
}
function friendRequest() {
	var to=$('#person').html();
	var from=$('#user').html();
	$.ajax({
		url: "AddFriend",
		method: "POST",
		data: { 'to' : to,'from':from},
		success: function(response){
			//console.log(response);
			if(response) {
				console.log(response);
				if(response=="true") {
					$('#unFriend').show();
					$('#friend').hide();
				}

			}
			else {

			}
		} 
	});
}
function follow() {
	var to=$('#person').html();
	var from=$('#user').html();
	console.log(to);

	//$("#modal_close").click();
	$.ajax({
		url: "Follow",
		method: "POST",
		data: { 'following' : to,'follower':from},
		success: function(response){
			//console.log(response);
			if(response) {
				console.log(response);
				if(response=="true") {
					location.reload();
				}

			}
			else {

			}
		} 
	});
}
function Unfriend() {
	var to=$('#person').html();
	var from=$('#user').html();
	console.log(to);

	//$("#modal_close").click();
	$.ajax({
		url: "UnFriend",
		method: "POST",
		data: { 'to' : to,'from':from},
		success: function(response){
			//console.log(response);
			if(response) {
				if(response=="true") {
					location.reload();
				}

			}
			else {

			}
		} 
	});
}

function Unfollow() {
	var to=$('#person').html();
	var from=$('#user').html();
	console.log(to);

	//$("#modal_close").click();
	$.ajax({
		url: "UnFollow",
		method: "POST",
		data: { 'following' : to,'follower':from},
		success: function(response){
			//console.log(response);
			if(response) {
				console.log(response);
				if(response=="true") {
					location.reload();
				}

			}
			else {

			}
		} 
	});
}
$('.modal').on('hidden', function () {
	document.location.reload();
})
function message() {
	var to=$('#person').html();
	var from=$('#user').html();
	var message=$('#message').val();

	$.ajax({
		url: "message",
		method: "POST",
		data: { 'message_to' : to,'message_from':from,"message":message},
		success: function(response){
			//console.log(response);
			if(response) {
				console.log(response);
				if(response=="true") {
					document.getElementById("modal_close").click();
				}

			}
			else {
				document.getElementById("modal_close").click();
			}
		} 
	});
}
function forcePopUp(){
	$('#message').val("");
	$(".modal").css("display","block");
}
function forceOpen(){
	$(".dropdown-menu").css("display","block");
}

function buttonUp(){
	$('#search').empty();
	var inputVal = $('.searchbox-input').val();
	if( inputVal){
		$('.searchbox-icon').css('display','none');
		$.ajax({
			url: "search",
			method: "POST",
			data: { 'search' : inputVal},
			success: function(response){
				//console.log(response);
				if(response) {
					//sconsole.log(response);
					var obj=jQuery.parseJSON(response);
					//console.log(obj);
					$('#search').empty();
					obj.forEach(function append(i) {
						$( '#search' ).append( "<a href='profile/"+i.username+"'><div id='message'><img src='data:image/jpg;base64,"+i.occupation+"'class='img-circle img-responsive' id='circular-image'>"+i.full_name+"</div></a>" );
					});


				}
				else {
					document.getElementById("modal_close").click();
				}
			} 

		});
	} else {
		$('.searchbox-input').val('');
		$('.searchbox-icon').css('display','block');

	}
}