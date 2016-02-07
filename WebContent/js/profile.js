/**
 * 
 */     
  function previewImage(input) {
    var preview = document.getElementById('preview');
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function (e) {
        preview.setAttribute('src', e.target.result);
      }
      reader.readAsDataURL(input.files[0]);
    } else {
      preview.setAttribute('src', 'profile_pic.png');
    }
  }

  $(document).ready(function() {
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