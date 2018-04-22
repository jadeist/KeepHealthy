
(function($) {

	skel
		.breakpoints({
			xlarge: '(max-width: 1680px)',
			large: '(max-width: 1280px)',
			medium: '(max-width: 980px)',
			small: '(max-width: 736px)',
			xsmall: '(max-width: 480px)'
		});

	$(function() {

		var	$window = $(window),
			$body = $('body');

		//Desactivar animaciones
			$body.addClass('is-loading');

			$window.on('load', function() {
				window.setTimeout(function() {
					$body.removeClass('is-loading');
				}, 100);
			});

		//Fix- Error del placeholder
			$('form').placeholder();

		//Elementos importantes
			skel.on('+medium -medium', function() {
				$.prioritize(
					'.important\\28 medium\\29',
					skel.breakpoint('medium').active
				);
			});

		// Nav.
			$('#nav')
				.append('<a href="#nav" class="close"></a>')
				.appendTo($body)
				.panel({
					delay: 500,
					hideOnClick: true,
					hideOnSwipe: true,
					resetScroll: true,
					resetForms: true,
					side: 'right'
				});

				//Scroll suve Tenorio
		     $('a[href*=#]').click(function() {
 
				     if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'')
				         && location.hostname == this.hostname) {
	
				             var $target = $(this.hash);
	
				             $target = $target.length && $target || $('[name=' + this.hash.slice(1) +']');
	
				             if ($target.length) {
	
				                 var targetOffset = $target.offset().top;
	
				                 $('html,body').animate({scrollTop: targetOffset}, 1000);
	
				                 return false;
	
				            }
	
				  }
			//Scroll suve Tenorio
			 $('a[href*=#]').click(function() {
	
				     if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'')
				         && location.hostname == this.hostname) {
	
				             var $target = $(this.hash);
	
				             $target = $target.length && $target || $('[name=' + this.hash.slice(1) +']');
	
				             if ($target.length) {
	
				                 var targetOffset = $target.offset().top;
	
				                 $('html,body').animate({scrollTop: targetOffset}, 1000);
	
				                 return false;
	
				            }
	
				  }
			 });
	});

})(jQuery);