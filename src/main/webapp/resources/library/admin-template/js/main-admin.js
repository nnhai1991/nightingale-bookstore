function utcToLocalDate(utcDate){
	return moment(new Date(utcDate+'Z')).format('DD MMM YYYY HH:mm (Z)');
}