
// Tooltips for Social Links
$('.tooltip-social').tooltip({
  selector: "a[data-toggle=tooltip]"
})

// Flexslider
$(document).ready(function($) {
	$('#main-slider').flexslider({
		animation: "fade",
		slideshowSpeed: 3500,
		controlNav: false,
		directionNav: false
	});
});

// Owl Carousel
$(document).ready(function($) {
      $("#owl-example").owlCarousel();
});

// Custom Tab styles
$(document).ready(function($) {
	$(".i-div").on('click', function() {
	       $(".android-div").fadeOut();
	       $(".windows-div").fadeOut();
	       $(".iphone-div").fadeIn();
	});

	$(".a-div").on('click', function() {
	       $(".android-div").fadeIn();
	       $(".windows-div").fadeOut();
	       $(".iphone-div").fadeOut();
	});

	$(".w-div").on('click', function() {
	       $(".android-div").fadeOut();
	       $(".windows-div").fadeIn();
	       $(".iphone-div").fadeOut();
	});
});

// Prettyphoto
$(document).ready(function() {
	$("a[class^='prettyPhoto']").prettyPhoto({theme:'pp_default'});
});



// Paxallax
$(document).ready(function(){
	//.parallax(xPosition, speedFactor, outerHeight) options:
	//xPosition - Horizontal position of the element
	//inertia - speed to move relative to vertical scroll. Example: 0.1 is one tenth the speed of scrolling, 2 is twice the speed of scrolling
	//outerHeight (true/false) - Whether or not jQuery should use it's outerHeight option to determine when a section is in the viewport
	$('#intro').parallax("50%", 0.1);
	$('#second').parallax("50%", 0.1);
});

