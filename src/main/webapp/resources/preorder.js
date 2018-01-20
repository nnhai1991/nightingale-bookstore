$(document).ready(function()
{ 
	$('form').submit(function(){
	    $(this).find(':input[type=submit]').not(".escape-disable").prop('disabled', true);
	});
});

function utcToLocalDate(utcDate){
	return moment(new Date(utcDate+'Z')).format('DD MMM YYYY HH:mm (Z)');
}












