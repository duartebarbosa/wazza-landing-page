'use strict';

$(document).ready(function() {

  var originalLeave = $.fn.popover.Constructor.prototype.leave;
  $.fn.popover.Constructor.prototype.leave = function(obj){
    var self = obj instanceof this.constructor ?
          obj : $(obj.currentTarget)[this.type](this.getDelegateOptions()).data('bs.' + this.type)
      var container, timeout;
      
      originalLeave.call(this, obj);

    if(obj.currentTarget) {
      container = $(obj.currentTarget).siblings('.popover')
      timeout = self.timeout;
      container.one('mouseenter', function(){
          //We entered the actual popover – call off the dogs
        clearTimeout(timeout);
        //Let's monitor popover content instead
        container.one('mouseleave', function(){
          $.fn.popover.Constructor.prototype.leave.call(self, self);
        });
      })
    }
  };

  $('body').popover({ selector: '[data-popover]', trigger: 'click hover', placement: 'auto', delay: {show: 50, hide: 400}});
    
	mixpanel.track('pageView');

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
    data["country"] = $("#countrySelector").val();
    
    $.post(actionUrl, data)
      .done(function(r) {
        mixpanel.register({
          "Name": data.name,
          "Email": data.email,
          "Company": data.company,
          "Country": data.country
        });
        mixpanel.people.set({
          "name": data.name,
          "$email": data.email,
          "$create": new Date(),
          "company": data.company
        });
        mixpanel.identify(data.email);
        mixpanel.track("Sign Up");
        $("#onSuccess").attr('hidden', false);
        $("#onFailure").attr('hidden', true);
      })
      .fail(function(e){
        $("#onSuccess").attr('hidden', true);
        $("#onFailure").attr('hidden', false);
      })
  });

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
