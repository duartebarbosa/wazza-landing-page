$(document).ready(function() {

	mixpanel.track('pageView');

	// sign-up
	var emailSubmit = function(email) {
		$.post( "/submit", {email: $(email).val()}, function(data) {
			mixpanel.track("Sign Up", {"email": emailValue});
			//$("#shareText").attr('hidden', false);
			//$("#share").attr('hidden', false);
			$("#thankyou").removeClass('hidden');
			$("#asksales").addClass('hidden');
		});
	};

	$("#saveUserEmail").on("click", function(){
		emailSubmit("#userEmail");
	});

  // TODO: put this to work
  // $('#share').share({
  //   networks: ['googleplus','facebook','twitter','linkedin'],
  //   theme: 'square',
  //   urlToShare: "http://wazza.io",
  //   title: "I just signed up for Wazza! Join me!"
  // });

	/*
	* smooth scrolling hack
	*/

	$('a[href^="#"]').on('click',function (e) {
		e.preventDefault();

		var target = this.hash,
		$target = $(target);

		$('html, body').stop().animate({
			'scrollTop': $target.offset().top
		}, 900, 'swing', function () {
			window.location.hash = target;
		});
	});
});
