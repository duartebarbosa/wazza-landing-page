$(function() {
  $("#menu-close").click(function(e) {
      e.preventDefault();
      $("#sidebar-wrapper").toggleClass("active");
  });

  $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#sidebar-wrapper").toggleClass("active");
  });

  $('a[href*=#]:not([href=#])').click(function() {
    if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') 
      || location.hostname == this.hostname) {

      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
      if (target.length) {
        $('html,body').animate({
          scrollTop: target.offset().top
        }, 1000);
        return false;
      }
    }
  });

  $("#twitter").click(function(e){
    window.open('http://twitter.com/UseWazza', 'Twitter', '');
    return false;
  });

  $("#facebook").click(function(e){
    window.open('http://www.facebook.com/usewazza', 'Facebook', '');
    return false;
  });

  $("#linkedin").click(function(e){
    window.open('http://www.linkedin.com/company/wazza-mobile', 'Linkedin', '');
    return false;
  });

});
