$(document).ready(function()
{ 
	/* Update hidden key after selecting the dropdown */
	$('ul li').click(function()
	{
		$("#search-key").val(this.id);
	});

	/* On load, focus on the search textbox */
	$(".search-bar input").focus();

	/* After selecting the dropdown, focus on the search textbox */
	$(".search-bar .dropdown-menu a").click(function()
	{
		var value = $(this).html();
		$(".search-bar .dropdown button").html(value);
		$(".search-bar input").focus();
	});

    /* On focus, change search icon to blue */
    $(".search-bar input").focus(function () {
        $(this).siblings(".glyphicon").removeClass("offfocus");
    });

    /* Show press-enter message 3 seconds after key down. */
    $(".search-bar input").keyup(function () {
        $(this).after("<span class='label label-default'>Press ENTER to search</span>");
        $(this).siblings(".label").delay(3000).fadeIn();
        $(this).unbind("keyup");
    });
    
    /* Hide press-enter message on blur */
    $(".search-bar input").blur(function () {
        $(this).siblings(".label").hide();
        $(this).siblings(".glyphicon").addClass("offfocus");
    });
});