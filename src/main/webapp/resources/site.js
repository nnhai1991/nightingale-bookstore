$(document).ready(function() {
	// To enable touchstart event, react to :hover css
	document.addEventListener("touchstart", function() {
	}, true);


	highlightSidebar();
	
	displayNotification();

});

// Purpose: Generate global header from json file.
function initiateGlobalNav(jsonFilePath, isLoggedIn, extraLinks) {


	$.getJSON(jsonFilePath, function(data) {
		// Retrieve the template data from the HTML (jQuery is used here).
		var template = $('#top-nav-template').html();
		var template2 = $('#second-nav-template').html();
		var template3 = $('#mobile-nav-template').html();

		// Compile the template data into a function
		var templateScript = Handlebars.compile(template);
        var templateScript2 = Handlebars.compile(template2);
        var templateScript3 = Handlebars.compile(template3);

        var context = data;

		var html = templateScript(context);
		var html2 = templateScript2(context);
		var html3 = templateScript3(context);

		// Insert the HTML code into the page
		$("#top-nav").html(html);
		$("#second-nav").html(html2);
		$("#mobile-nav").html(html3);


		// Initialize hover event for Deskop Menu
		var timer;
		$(".left-nav a").each(function() {
			var thisDivId = "#" + $(this).attr("data-id");

			// Hover effect in top-nav
			$(this).hover(function() {
				// On mouse enter
				clearTimeout(timer);
				
				// Delay showing when mouse enter, to reduce unintended
				// activation
				timer = setTimeout(function() {
					// Hide the rest
					$(".left-nav a").each(function() {
						var thisDivId = "#" + $(this).attr("data-id");
						$(thisDivId).addClass("hide");
					});

					// Show dropdown menu
					$(thisDivId).removeClass("hide");
					$("#second-nav-container").removeClass("hide");
				}, 200);

				// Hover effect in second-nav.
				$(thisDivId).hover(function() {
					clearTimeout(timer);
				}, function() {
					clearTimeout(timer);

					// Delay hiding when mouse out to reduce unintended
					// de-activation
					timer = setTimeout(function() {
						$(thisDivId).addClass("hide");
						$("#second-nav-container").addClass("hide");
					}, 500);
				});

			}, function() {
				// On mouse leave
				clearTimeout(timer);
				timer = setTimeout(function() {
					$(thisDivId).addClass("hide");
					$("#second-nav-container").addClass("hide");
				}, 500);
			});
		});

		// Configure event for mobile menu
		var mobileMenu = $("#mobile-menu");
		var crossMenu = $("#cross-menu");
		var mobileNavContainer = $("#mobile-nav-container");
		var levelOneMenu = $("#mobile-nav-level-one");
		var levelTwoMenu = $("#mobile-nav-level-two");
		var levelTwoMenuDiv = $("#mobile-nav-level-two div");

		// Initiate hamburger menu event
		mobileMenu.click(function() {
			$(this).addClass("hide");
			crossMenu.removeClass("hide");
			// Hide content section, that is those are not in class:nav-section
			$("section").not(".nav-section").addClass("hide");
			// Show level one nav
			mobileNavContainer.removeClass("hide");
			levelOneMenu.removeClass("hide");
		});

		// Initiate cross menu event
		crossMenu.click(function() {
			$(this).addClass("hide");
			mobileMenu.removeClass("hide");
			// Show content section, that is those are not in class:nav-section
			$("section").not(".nav-section").removeClass("hide");
			// Hide level one nav
			mobileNavContainer.addClass("hide");
			levelOneMenu.addClass("hide");
			// Hide all level two
			levelTwoMenu.addClass("hide");
			levelTwoMenuDiv.each(function() {
				$(this).addClass("hide");
			})
		});

		// Initiate click event for each level-one link
		$("#mobile-nav-level-one .two-level-link").each(
				function() {
					var thisDivId = "#mobile-nav-level-two #"
							+ $(this).attr("data-id");

					$(this).click(function() {
						levelOneMenu.addClass("hide");
						levelTwoMenu.removeClass("hide");
						$(thisDivId).removeClass("hide");
					});

				});

		// Initiate click event for back button
		$("#back-to-level-one").click(function() {
			// Show level one
			levelOneMenu.removeClass("hide");
			// Hide all level two
			levelTwoMenu.addClass("hide");
			levelTwoMenuDiv.each(function() {
				$(this).addClass("hide");
			})
		});

		// If user is logged in, setup logout button
		// Only when login-button is present in the first place
		var loginButton = $(".login-button");
		if (isLoggedIn && loginButton) {
			// Apply click event for logout button
			var logoutButton = $(".logout-button");

			logoutButton.removeClass("hide");

			logoutButton.each(function() {
				$(this).click(function(e) {

                    $("form#logout-form").submit();
					return false;
				});
			});
			
			loginButton.addClass("hide");
		} else {
			loginButton.removeClass("hide");
			loginButton.removeAttr('target');
			
			loginButton.each(function() {
				$(this).click(function(e) {
					e.preventDefault();
					// $("form#login-form").submit();
					window.location=$("form#login-form").attr('action');
						// return false;
				});
			});
		}
		
		// If there are more links
		if(extraLinks) {
			// For each extraLinks
			$.each(extraLinks, function (index, item) {
                //
                // $("." + item.class).attr("href", item.link);
                //
                // //override default behavior: not open the link in new tab
                // if(item.openInNewTab != null && item.openInNewTab !=
				// undefined && item.openInNewTab == false){
                // $("." + item.class).removeAttr('target');
                // }
                // });
                var targetDOM = $("." + item.class);
                targetDOM.attr("href", item.link);

                if(item.target) {
                    targetDOM.attr("target", item.target);
                }
            });
		}

	});

}
function highlightSidebar() {
    var currentPath = window.location.pathname;
    $("#sidebar li a").each(function() {

        var thisNav = $(this);
        // Regex: replace '/' with '\/' so that the string can be used in regex.
        // Add (?!-) for negative lookahead for '-' so that product and
        // product-type can be differentiated
        var pattern = thisNav.attr("href").replace(/\//g, "\\/") + "(?!-)";
        var regexPattern = new RegExp(pattern);
        var regexResult = currentPath.match(regexPattern);

        if (regexResult !== null) {
            thisNav.addClass("nav-active");
        }

    });
}

function scrollToAnchor(anchor) {
	var tag = $("a[name='" + anchor + "']");
	$('html,body').animate({
		scrollTop : tag.offset().top
	}, 'slow');
}

/* For demo only. Remove from development */
function showTime(anchor, special) {

	$('#timeslot-time-section').hide();

	if (special == "true") {
		scrollToAnchor(anchor);
		$('#timeslot-time-section').fadeIn(500);
	}
}
function setHighlightClick(className) {
	$("." + className).each(function() {
		$(this).click(function() {
			$(this).toggleClass(className + "-selected");
		});
	});
}

function showError() {
	$(".error-message").toggleClass("hide");
}

function validateNRIC(str, ictype) {
	if (str.length != 9)
		return false;

	str = str.toUpperCase();

	var i, icArray = [];
	for (i = 0; i < 9; i++) {
		icArray[i] = str.charAt(i);
	}

	if (ictype == "1") {
		if (icArray[0] != "S" && icArray[0] != "T")
			return false;
	} else if (ictype == "2") {
		if (icArray[0] != "F" && icArray[0] != "G")
			return false;
	}

	icArray[1] = parseInt(icArray[1], 10) * 2;
	icArray[2] = parseInt(icArray[2], 10) * 7;
	icArray[3] = parseInt(icArray[3], 10) * 6;
	icArray[4] = parseInt(icArray[4], 10) * 5;
	icArray[5] = parseInt(icArray[5], 10) * 4;
	icArray[6] = parseInt(icArray[6], 10) * 3;
	icArray[7] = parseInt(icArray[7], 10) * 2;

	var weight = 0;
	for (i = 1; i < 8; i++) {
		weight += icArray[i];
	}

	var offset = (icArray[0] == "T" || icArray[0] == "G") ? 4 : 0;
	var temp = (offset + weight) % 11;

	var st = [ "J", "Z", "I", "H", "G", "F", "E", "D", "C", "B", "A" ];
	var fg = [ "X", "W", "U", "T", "R", "Q", "P", "N", "M", "L", "K" ];

	var theAlpha;
	if (icArray[0] == "S" || icArray[0] == "T") {
		theAlpha = st[temp];
	} else if (icArray[0] == "F" || icArray[0] == "G") {
		theAlpha = fg[temp];
	}

	return (icArray[8] === theAlpha);
}

function omnitureClick() {
	// var s = s_gi(s_account);
	// s.linkTrackVars = 'evar35,products,events';
	// s.linkTrackEvents = 'event35';
	// s.eVar35 = 'buy-mobile:pre-order';
	// s.products = ';galaxy-s8';
	// s.events = 'event35';
	// s.tl(this, 'o', 'buy-mobile:pre-order', ';galaxy-s8');
}

function removeLastComma() {
	$(".remove-last-comma").each(function() {
		var str = $(this).html();

		// Replace newline character to make them single line (JS regex cannot
		// process multiple line) (regex: find ',' and any space or newline,
		// replace with ', ')
		// Then remove the last comma (regex: find ',' that has no more ',' in
		// front then replace with blank)
		str = str.trim().replace(/,\s*(?!,)/g, ", ").replace(/,(?!.*,)/, "");

		$(this).html(str);

	});

}

//Animate notification after create, update and delete
function displayNotification() {
    var notificationDiv = $("#notification");
    if (window.location.hash != "#done" && $.trim(notificationDiv.html()).length > 0) {
        notificationDiv.fadeIn().delay(3000).fadeOut();
        window.location.hash = "done";
    }
}
