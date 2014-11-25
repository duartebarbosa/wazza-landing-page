
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
    var result = true//validator(emailValue);
    if (result) {
        $.post( "/submit", {email: emailValue}, function(data) {
          mixpanel.track("Sign Up", {"email": emailValue});
          $("#shareText").attr('hidden', false);
          $("#share").attr('hidden', false);
          //alert("Thanks for joining us! We have awesome news coming soon. Stay tuned");
        });
    } else {
        console.log("Please insert a valid email address");
    }
  };

  $("#saveUserEmail").on("click", function(){
    emailSubmit("#userEmail");
  });
  $("#saveUserEmailUp").on("click", function(){
  	console.log("save user email up")
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

! function(a) {
    "use strict";
    a(function() {
        var b = a(window),
            c = a(document.body);
        c.scrollspy({
                target: ".bs-docs-sidebar"
            }), b.on("load", function() {
                c.scrollspy("refresh")
            }), a(".bs-docs-container [href=#]").click(function(a) {
                a.preventDefault()
            }), setTimeout(function() {
                var b = a(".bs-docs-sidebar");
                b.affix({
                    offset: {
                        top: function() {
                            var c = b.offset().top,
                                d = parseInt(b.children(0).css("margin-top"), 10),
                                e = a(".bs-docs-nav").height();
                            return this.top = c - e - d
                        },
                        bottom: function() {
                            return this.bottom = a(".bs-docs-footer").outerHeight(!0)
                        }
                    }
                })
            }, 100), setTimeout(function() {
                a(".bs-top").affix()
            }, 100),
            function() {
                var b = a("#bs-theme-stylesheet"),
                    c = a(".bs-docs-theme-toggle"),
                    d = function() {
                        b.attr("href", b.attr("data-href")), c.text("Disable theme preview"), localStorage.setItem("previewTheme", !0)
                    };
                localStorage.getItem("previewTheme") && d(), c.click(function() {
                    var a = b.attr("href");
                    a && 0 !== a.indexOf("data") ? (b.attr("href", ""), c.text("Preview theme"), localStorage.removeItem("previewTheme")) : d()
                })
            }(), a(".tooltip-demo").tooltip({
                selector: '[data-toggle="tooltip"]',
                container: "body"
            }), a(".popover-demo").popover({
                selector: '[data-toggle="popover"]',
                container: "body"
            }), a(".tooltip-test").tooltip(), a(".popover-test").popover(), a(".bs-docs-popover").popover(), a("#loading-example-btn").on("click", function() {
                var b = a(this);
                b.button("loading"), setTimeout(function() {
                    b.button("reset")
                }, 3e3)
            }), a("#exampleModal").on("show.bs.modal", function(b) {
                var c = a(b.relatedTarget),
                    d = c.data("whatever"),
                    e = a(this);
                e.find(".modal-title").text("New message to " + d), e.find(".modal-body input").val(d)
            }), a(".bs-docs-activate-animated-progressbar").on("click", function() {
                a(this).siblings(".progress").find(".progress-bar-striped").toggleClass("active")
            }), ZeroClipboard.config({
                moviePath: "/assets/flash/ZeroClipboard.swf",
                hoverClass: "btn-clipboard-hover"
            }), a(".highlight").each(function() {
                var b = '<div class="zero-clipboard"><span class="btn-clipboard">Copy</span></div>';
                a(this).before(b)
            });
        var d = new ZeroClipboard(a(".btn-clipboard")),
            e = a("#global-zeroclipboard-html-bridge");
        d.on("load", function() {
            e.data("placement", "top").attr("title", "Copy to clipboard").tooltip()
        }), d.on("dataRequested", function(b) {
            var c = a(this).parent().nextAll(".highlight").first();
            b.setText(c.text())
        }), d.on("complete", function() {
            e.attr("title", "Copied!").tooltip("fixTitle").tooltip("show").attr("title", "Copy to clipboard").tooltip("fixTitle")
        }), d.on("noflash wrongflash", function() {
            e.attr("title", "Flash required").tooltip("fixTitle").tooltip("show")
        })
    })
}(jQuery);
