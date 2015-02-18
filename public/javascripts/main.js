'use strict';

$(document).ready(function() {

	//mixpanel.track('pageView');

  $("#requestContactForm").submit(function(e) {
    //prevent Default functionality
    e.preventDefault();
   
    var dataArr = $("#requestContactForm").serializeArray();
    var data = {};
    $.each(dataArr, function() {
      if (data[this.name] !== undefined) {
        if (!data[this.name].push) {
          data[this.name] = [data[this.name]];
        }
        data[this.name].push(this.value || '');
      } else {
        data[this.name] = this.value || '';
      }
    });

    var actionUrl = e.currentTarget.action;
    console.log(data);
    console.log(actionUrl);

    $.post(actionUrl, data)
      .done(function(r) {
        console.log("success");
      })
      .fail(function(e){
        console.log("error");
      })
  });

	// $("#saveUserEmail").on("click", function(){
  //   console.log("email submit");
    
  //   $.post( "/submit", {email: $("#userEmail").val()}, function(data) {
	// 		mixpanel.track("Sign Up", {"email": data.emailValue});
	// 		//$("#shareText").attr('hidden', false);
  //     console.log("SUCCESS");
	// 		$("#share").attr('hidden', false);
	// 		$("#asksales").addClass('hidden');
	// 	});
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
