/*
Demo: Despiration Tutorial Parallax Demo
Author: Elias Ghosn - Despiration.com
Author URL: http://www.despiration.com/
Tutorial URL: http://www.ianlunn.co.uk/blog/code-tutorials/recreate-nikebetterworld-parallax/

License: http://creativecommons.org/licenses/by-sa/3.0/ (Attribution Share Alike). Please attribute work to Despiration.com simply by leaving these comments in the source code or if you'd prefer, place a link on your website to http://www.despiration.com/.

Dual licensed under the MIT and GPL licenses:
http://www.opensource.org/licenses/mit-license.php
http://www.gnu.org/licenses/gpl.html
*/

$(document).ready(function() { //when the document is ready...


	//save selectors as variables to increase performance
	var $window = $(window);
	var $firstBG = $('#intro');
	var moon = $("#intro .bg1");
	var nebula = $("#intro .bg2");
	var shooting_stars = $("#intro .bg3");
	var trees_left = $("#intro .bg4");
	var trees_right = $("#intro .bg5");
	var bottom_background = $("#intro .bg6");
	var bottom_foreground = $("#intro .bg7");
	var $secondBG = $('#second');
	var goggles = $(".goggle_view");
	var goggles_side = $(".goggle_side");
	var compass_1 = $("#second .bg2");
	
	var windowHeight = $window.height(); //get the height of the window
	
	
	//apply the class "inview" to a section that is in the viewport
	$('#intro, #second').bind('inview', function (event, visible) {
			if (visible == true) {
			$(this).addClass("inview");
			} else {
			$(this).removeClass("inview");
			}
		});
	
			
	//function that places the navigation in the center of the window
	function RepositionNav(){
		var windowHeight = $window.height(); //get the height of the window
		var navHeight = $('#nav').height() / 2;
		var windowCenter = (windowHeight / 2); 
		var newtop = windowCenter - navHeight;
		$('#nav').css({"top": newtop}); //set the new top position of the navigation list
	}
	
	//function that is called for every pixel the user scrolls. Determines the position of the background
	/*arguments: 
		x = horizontal position of background
		windowHeight = height of the viewport
		pos = position of the scrollbar
		adjuster = adjust the position of the background
		inertia = how fast the background moves in relation to scrolling
	*/
	function newPos(x, windowHeight, pos, adjuster, inertia){
		return x + "% " + (-((windowHeight + pos) - adjuster) * inertia)  + "px";
	}
	
	//function to be called whenever the window is scrolled or resized
	function Move(){ 
		var pos = $window.scrollTop(); //position of the scrollbar

		//if the first section is in view...
		if($firstBG.hasClass("inview")){
			//call the newPos function and change the background position
			$firstBG.css({'backgroundPosition': newPos(0, windowHeight, pos, 720, 0)}); 
			//call the newPos function and change the second background position
			moon.css({'backgroundPosition': newPos(10, windowHeight, pos, 800, 0.1)});
			nebula.css({'backgroundPosition': newPos(90, windowHeight, pos, 1000, 0.2)});
			shooting_stars.css({'backgroundPosition': newPos(60, windowHeight, pos, 3700, 0.1)});
			trees_left.css({'backgroundPosition': newPos(0, windowHeight, pos, 1600, 0.6)});
			trees_right.css({'backgroundPosition': newPos(100, windowHeight, pos, 1600, 0.6)});
			bottom_foreground.css({'backgroundPosition': newPos(60, windowHeight, pos, 2200, 0.4)});
			bottom_background.css({'backgroundPosition': newPos(60, windowHeight, pos, 2500, 0.3)});
		}
		
		//if the second section is in view...
		if($secondBG.hasClass("inview")){
			//call the newPos function and change the background position
			$secondBG.css({'backgroundPosition': newPos(50, windowHeight, pos, 1550, 0.3)});
			//call the newPos function and change the second background position
			goggles.css({'backgroundPosition': newPos(50, windowHeight, pos, 2000, 0.6)});
			goggles_side.css({'backgroundPosition': newPos(10, windowHeight, pos, 2200, 0.6)});
			compass_1.css({'backgroundPosition': newPos(95, windowHeight, pos, 2200, 0.6)});
		}
		
		$('#pixels').html(pos); //display the number of pixels scrolled at the bottom of the page
	}
		
	RepositionNav(); //Reposition the Navigation to center it in the window when the script loads
	
	$window.resize(function(){ //if the user resizes the window...
		Move(); //move the background images in relation to the movement of the scrollbar
		RepositionNav(); //reposition the navigation list so it remains vertically central
	});		
	
	$window.bind('scroll', function(){ //when the user is scrolling...
		Move(); //move the background images in relation to the movement of the scrollbar
	});
	
});