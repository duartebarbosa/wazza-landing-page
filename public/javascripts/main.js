
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
		directionNav: false,
		smoothHeight:true
	});
});

// Prettyphoto
//($(document).ready(function() {
//	$("a[class^='prettyPhoto']").prettyPhoto({theme:'pp_default'});
//});

// Paxallax
$(document).ready(function(){
	//.parallax(xPosition, speedFactor, outerHeight) options:
	//xPosition - Horizontal position of the element
	//inertia - speed to move relative to vertical scroll. Example: 0.1 is one tenth the speed of scrolling, 2 is twice the speed of scrolling
	//outerHeight (true/false) - Whether or not jQuery should use it's outerHeight option to determine when a section is in the viewport
	$('#intro').parallax("50%", 0.1);
	$('#second').parallax("50%", 0.1);
});


$(document).ready(function(e) {
	$('.with-hover-text, .regular-link').click(function(e){
		e.stopPropagation();
	});
	
	/***************
	* = Hover text *
	* Hover text for the last slide
	***************/
	$('.with-hover-text').hover(
		function(e) {
			$(this).css('overflow', 'visible');
			$(this).find('.hover-text')
				.show()
				.css('opacity', 0)
				.delay(200)
				.animate(
					{
						paddingTop: '25px',
						opacity: 1
					},
					'fast',
					'linear'
				);
		},
		function(e) {
			var obj = $(this);
			$(this).find('.hover-text')
				.animate(
					{
						paddingTop: '0',
						opacity: 0
					},
					'fast',
					'linear',
					function() {
						$(this).hide();
						$( obj ).css('overflow', 'hidden');
					}
				);
		}
	);
	
	var img_loaded = 0;
	var j_images = [];
	
});


var delay = (function(){
	var timer = 0;
	return function(callback, ms){
		clearTimeout (timer);
		timer = setTimeout(callback, ms);
	};
})();

function menu_focus( element, i ) {
	if ( $(element).hasClass('active') ) {
		if ( i == 6 ) {
			if ( $('.navbar').hasClass('inv') == false )
				return;
		} else {
			return;
		}
	}
	
	enable_arrows( i );
		
	if ( i == 1 || i == 6 )
		$('.navbar').removeClass('inv');
	else
		$('.navbar').addClass('inv');
	
	$('.nav > li').removeClass('active');
	$(element).addClass('active');
	
	var icon = $(element).find('.icon');
	
	var left_pos = icon.offset().left - $('.nav').offset().left;
	var el_width = icon.width() + $(element).find('.text').width() + 10;
	
	$('.active-menu').stop(false, false).animate(
		{
			left: left_pos,
			width: el_width
		},
		1500,
		'easeInOutQuart'
	);
}


$(document).ready(function(e) {

  $(".fancybox").fancybox({
      padding: 10,
      helpers: {
          overlay: {
              locked: false
          }
      }
  });

  mixpanel.track('pageView');

// sign-up
  var emailSubmit = function(email) {
    var validator = function (email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    };

    var emailValue = $(email).val();
    var result = validator(emailValue);
    if (result) {
        $.post( "/submit", {email: emailValue}, function(data) {
          mixpanel.track("Sign Up", {"email": emailValue});
          $("#shareText").attr('hidden', false);
          $("#share").attr('hidden', false);
          //alert("Thanks for joining us! We have awesome news coming soon. Stay tuned");
        })
        .fail(function(data) {
            alert("There was an error with your submission. Please try again");
        });
    } else {
        console.log("Please insert a valid email address");
    }
  };

  $("#saveUserEmail").on("click", function(){
    emailSubmit("#userEmail");
  });
  $("#saveUserEmailUp").on("click", function(){
    emailSubmit("#userEmailUp");
  });
  $('#share').share({
    networks: ['googleplus','facebook','twitter','linkedin'],
    theme: 'square',
    urlToShare: "http://wazza.io",
    title: "I just signed up for Wazza! Join me!"
  });
});


/*
* smooth scrolling hack
*/
$(document).ready(function(){
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

//hack to fit title in the screen
$('#intro').flowtype({
	maximum : 480
});