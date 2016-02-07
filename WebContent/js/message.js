/**
 * 
 */
/**
 * 
 */
$( window).resize(function() {
	
});
$(document).ready(function() {
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
});
function status_prepend() {
	var status=$('#comment1').val();
	console.log(status);
	$( "<div class='status'>"+status+"</div>" ).appendTo( ".wall_frame1" );
}
function show(sender,content,img) {
	//var img=$('#circular-image').attr('src');
	//var sender=$('#sender_name').html();
	//var content=$('#message_content').html();
	$('#messenger').empty();
	$('#message_data').empty();
	$('#messenger').append(sender);
	$('#message_data').append(content);
	/*$.ajax({
		url: "picture",
		method: "POST",
		data: { 'picture' :img},
		success: function(response){
			//console.log(response);
			if(response) {
				$('#sender-image').attr('src',"data:image/jpg;base64,"+response);
				

			}
			else {

			}
		} 
	});*/
	$('#sender-image').attr('src',"data:image/jpg;base64,"+img);
	console.log(img);
}
function show_footer() {
	$('#hidden_footer').show();
}
function hide_footer() {
	$('#hidden_footer').hide();
}
$( window).resize(function() {
	var $doc_width=$( window ).width();
	console.log($doc_width);
	if($doc_width<1300) {
		document.getElementById("left_box").style.width = 'auto';
		document.getElementById("right_box").style.width = 'auto';
		document.getElementById("right_box").style.margin = '5% auto';
		/*document.getElementById("right_box").style.float = 'left';*/
	}
	/*document.getElementById("left_box").style.width = 'auto';
	document.getElementById("right_box").style.width = '58%';
	document.getElementById("right_box").style.marginLeft = '35%';*/

	
});