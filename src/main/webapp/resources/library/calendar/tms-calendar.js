//Author KMT
//Date 20151023
//office hours

var arr_office_hours = "";
var arr_location = []; // not in use

var days = [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat' ];
var months = [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ];

var now = new Date();

var day = days[now.getDay()];
var month = months[now.getMonth()];
var uniqueID = 0;
var myArrayLoactionTemp = [];
var map = {};
var map_currentSelectedData = {};
var arr_currentSelectedData = [];
var map_currentSelectedLocation={};
var map_locationid_remark={};

var map_oldrecordwithDate={};
var map_detailsarrayOfDate={};

var g_selectroomData = "0";
var roomdDetails = [];

var globalStartDate = 0;
var globalMonth = "";
var globalYear = "";
var dat = now.getDate();

// Collection Model
var collection_id;
var user_id;
var order_id;
var shop_id;
var collection_time_slot;

var _training_obj_id;
var _training_obj_masterId;
var _isTrainingMaster;
var _training_type_id;
var _training_name;
var _training_remarks;

function createCalendar(timeslots) {

	var acc_name = "";
	
	arr_office_hours = timeslots;
	
	var set_month;
	set_month = now.getMonth();
	var month = months[now.getMonth()];
	var icurrentMonth = now.getMonth();
	var icurrentYear = now.getFullYear();
	var date_data = new Date(month + " 01, " + now.getFullYear());

	globalMonth = icurrentMonth;
	globalYear = icurrentYear;

	$("#month").text(month + " " + icurrentYear);
	$("#calendar").html(createCalendarTable(icurrentMonth, icurrentYear));

	$("#previous").click(function() {
		globalStartDate = 0;
		var show_month = set_month;

		if (show_month > 0) {
			show_month = show_month - 1;

		} else {
			icurrentYear = icurrentYear - 1;
			show_month = 11;
		}

		$("#month").text(months[show_month] + " " + icurrentYear);
		set_month = show_month;

		globalMonth = show_month;
		globalYear = icurrentYear;

		$("#calendar").html(createCalendarTable(show_month, icurrentYear));

		$('.playerTip').tooltipster({
			theme : 'tooltipster-noir',
			content : 'Loading...',
			animation : 'swing',
			interactive : true,
			speed : 0,
			functionBefore : function(origin, continueTooltip) {
				continueTooltip();
				var da = $(this).attr('id');
				
				origin.tooltipster('content', $("" + createLocation(da, acc_name)));
			}
		});
	});
	
	$("#next").click(function() {
		globalStartDate = 0;
		var show_month = set_month;

		if (show_month < 11) {
			show_month = show_month + 1;

		} else {
			icurrentYear = icurrentYear + 1;
			show_month = 0;
		}

		$("#month").text(months[show_month] + " " + icurrentYear);
		set_month = show_month;

		globalMonth = show_month;
		globalYear = icurrentYear;
		$("#calendar").html(createCalendarTable(show_month, icurrentYear));

		$('.playerTip').tooltipster({
			theme : 'tooltipster-noir',
			content : 'Loading...',
			animation : 'swing',
			interactive : true,
			speed : 0,
			functionBefore : function(origin, continueTooltip) {
				continueTooltip();
				var da = $(this).attr('id');

				origin.tooltipster('content', $(""
						+ createLocation(da, acc_name)));
			}
		});
	});

	$('.playerTip').mouseover(function() {
		playerID = $(this).attr('rel');
	});

	$('.playerTip').tooltipster({
		content : 'Loading...',
		interactive : true,
		animation : 'fade',
		// maxWidth: '700',
		speed : 0,
		theme : 'tooltipster-noir',
		functionBefore : function(origin, continueTooltip) {
			continueTooltip();
			var da = $(this).attr('id');
			origin.tooltipster('content', $(""+ createLocation(da, acc_name)));
		},
		functionAfter : function(origin) {

		}
	});
}

function noofdays(mm, yyyy) {
	mm = mm + 1;
	var daysofmonth;
	if ((mm == 4) || (mm == 6) || (mm == 9) || (mm == 11)) {
		daysofmonth = 30;
	}
	else {
		daysofmonth = 31;

		if (mm == 2) {

			if (yyyy / 4 - parseInt(yyyy / 4) != 0) {
				daysofmonth = 28;
			} else {
				if (yyyy / 100 - parseInt(yyyy / 100) != 0) {
					daysofmonth = 29;
				} else {
					if (yyyy / 400 - parseInt(yyyy / 400) != 0) {
						daysofmonth = 28;
					} else {
						daysofmonth = 29;
					}
				}
			}
		}
	}

	return daysofmonth;
}

function createLocation(dateMonth, loginName, arr_data_reserved) {

	var html_layout = " <div id='tablearea_location' class='box-padding' style='width:768px'><div class='row'><div class='border-panel-header' >"
			+ "<div class='border-panel-header-inside'><span class='font-bigsize'> "+dateMonth+"</span>"
			+ " "
			+ months[globalMonth] + " " + globalYear
			+ "</div></div><div class='col-xs-offset-1 col-xs-11'>";
	
	var appendMonthWithZero = dateMonth.toString();
	
	if (dateMonth.toString().length==1) {
		appendMonthWithZero = "0"+dateMonth.toString();
	}
	
	var para = globalYear+"-"+(globalMonth+1)+"-"+appendMonthWithZero;
	var calendarDate =  globalYear+"-"+(globalMonth+1)+"-"+appendMonthWithZero;
	var arr_check = map_oldrecordwithDate[para];
	var flag = 0;
	
	for (var j = 0; j < arr_office_hours.length-1; j++) {
		var hightlighted = 0;
		var para_date = calendarDate+" "+arr_office_hours[j]+":00";
		var longCalendarDate = getDateLong(para_date);
		
		if(arr_check!=null){
			var addedrowFlag = false;
			for(var k=0;k<arr_check.length;k++){
			
				var duration = arr_check[k].split(",");
				var para_db_date_start_time = calendarDate+" "+duration[0];
				var para_db_date_end_time = calendarDate+" "+duration[1];
				
				if((longCalendarDate >= getDateLong(para_db_date_start_time)) && (getDateLong(para_db_date_end_time) > longCalendarDate)){
					if(hightlighted==0){
						html_layout = html_layout
							+ "<div id='"+j+ "time' class='border-panel-selected-time col-xs-2' onClick='selectTime(this,"+j+","+ dateMonth + ")'>" 
							+ arr_office_hours[j] + "-" +arr_office_hours[j+1] + "</div>";
						hightlighted =1;
					}
				}
			}
			
			if(hightlighted==0){
				html_layout = html_layout
				+ "<div id='"+j+ "time' class='border-panel-orange col-xs-2' onClick='selectTime(this,"+j+","+ dateMonth + ")'>" 
				+ arr_office_hours[j] + "-" +arr_office_hours[j+1] + "</div>";
			}
		}else{
			html_layout = html_layout
			+ "<div id='"+j+ "time' class='border-panel-orange col-xs-2' onClick='selectTime(this,"+j+","+ dateMonth + ")'>" 
			+ arr_office_hours[j] + "-" +arr_office_hours[j+1] + "</div>";
		}
	}
	
	html_layout = html_layout + "</div><div id='timeslots' class='col-xs-12 content-center' >";
	html_layout = html_layout
			+ "</div>"
			+ "<div></div><div class=\"col-md-12 text-center\"> <button id='add_time' onClick=reserveSlot('"
			+ dateMonth + "') name='" + dateMonth
			+ "'   name=\"singlebutton\" class=\"border-panel-orange-room-add\">Submit</button> </div><span class='height-2px col-md-12' ></span></div>";
	
	
	
	return html_layout;
}

function selectRoom (view, id, date) {
	g_selectroomData = id;
	
	var oriDate = date;
	var timeSlotLayout;
	
	for (var j=0; j < arr_location.length; j++) {
		if(j == id){
			document.getElementById(j+"li" + date).setAttribute('class', 'border-panel-orange-room');
		}else{
			document.getElementById(j+"li" + date).setAttribute('class', 'border-panel-orange-room-unselect');
		}
	}
	
	if(date.length==1){
		date = "0"+date;
	}
	
	var gen_month = (globalMonth+1).toString();
	
	if (gen_month.toString().length==1){
		gen_month ="0"+gen_month.toString();
	}
	
	var para = globalYear+"-"+gen_month+"-"+date+map_currentSelectedLocation[arr_location[id]];
	var calendarDate =  globalYear+"-"+(globalMonth+1)+"-"+date;
	var arr_check = map_oldrecordwithDate[para];
	
	// AJAX Get Method - Retrieve Working Timing based on selected location
	var url = "getWorkingTimingByShopId/" + map_currentSelectedLocation[arr_location[id]];
	
	// Assign to global variable - ShopId
	shop_id = map_currentSelectedLocation[arr_location[id]];
	
	// call controller's action
	$.getJSON(url, function(data) {
		arr_office_hours = data;
	});
	
	for (var j = 0; j < arr_office_hours.length-1; j++) {
		var hightlighted = 0;
		var para_date = calendarDate+" "+arr_office_hours[j]+":00";
		var longCalendarDate = getDateLong(para_date);
		
		if(arr_check!=null){
			var addedrowFlag = false;
			for(var k=0;k<arr_check.length;k++){
			
				var duration = arr_check[k].split(",");
				var para_db_date_start_time = calendarDate+" "+duration[0];
				var para_db_date_end_time = calendarDate+" "+duration[1];
				
				if((longCalendarDate >= getDateLong(para_db_date_start_time)) && (getDateLong(para_db_date_end_time) > longCalendarDate)){
					if(hightlighted==0){
						timeSlotLayout = timeSlotLayout
							+ "<div id='"+j+ "time' class='border-panel-selected-time col-xs-2' onClick='selectTime(this,"+j+","+ oriDate + ")'>" 
							+ arr_office_hours[j] + "-" +arr_office_hours[j+1] + "</div>";
						hightlighted =1;
					}
				}
			}
			
			if(hightlighted==0){
				timeSlotLayout = timeSlotLayout
				+ "<div id='"+j+ "time' class='border-panel-orange col-xs-2' onClick='selectTime(this,"+j+","+ oriDate + ")'>" 
				+ arr_office_hours[j] + "-" +arr_office_hours[j+1] + "</div>";
			}
		}else{
			timeSlotLayout = timeSlotLayout
			+ "<div id='"+j+ "time' class='border-panel-orange col-xs-2' onClick='selectTime(this,"+j+","+ oriDate + ")'>" 
			+ arr_office_hours[j] + "-" +arr_office_hours[j+1] + "</div>";
		}
	}
	
	$("#timeslots").html(timeSlotLayout);
}

function contains(a, obj) {
	for (var i = 0; i < a.length; i++) {
		if (a[i] === obj) {
			return true;
		}
	}
	return false;
}

function selectTime(view, id, datemonth) {
	
	if(datemonth.length==1){
		datemonth = "0"+datemonth;
	}
	
	var para = globalYear+"-"+(globalMonth+1)+"-"+datemonth+g_selectroomData;
	var arr_check = map_oldrecordwithDate[para];
	var flag = 0;
	
	for (var i = 0; i < arr_office_hours.length-1; i++) {
		var genId = i+"time";
		
		if (i == id)
		{
			document.getElementById(genId).setAttribute('class', 'border-panel-orange-select-time col-xs-2');
			arr_currentSelectedData[0] = id;
		}
		else
		{
			document.getElementById(genId).setAttribute('class', 'border-panel-orange col-xs-2');
		}
	}
}

function getDateLong(stringDate){
	var splitDateTime= stringDate.split(" ");
    var date = splitDateTime[0];
    var time = splitDateTime[1];

    var yearMonthDay = date.split("-");
    var hoursMinutesSeconds= time.split(":");

    var actualDate = new Date(yearMonthDay[0],yearMonthDay[1],yearMonthDay[2],hoursMinutesSeconds[0],hoursMinutesSeconds[1],hoursMinutesSeconds[2],0);

    return actualDate.getTime();
}

function reCurCheck(showDays, totalDays) {

	var ivalue = showDays;

	while (ivalue > totalDays) {
		ivalue--;
	}

	return ivalue;
}

function reserveSlot(date) {
	
	if (arr_currentSelectedData.length > 0) {

		var time_slot_id = arr_currentSelectedData[0];
		
		var csrf_para = $("#csrf_para").val();
		var csrf_value = $("#csrf_value").val();
		var input_shop_id = $("#shop_id").val(); 
		
		var inputMonth = (globalMonth+1).toString(); 
		var inputDate = date;
		
		if(inputDate.length==1){
			inputDate = "0"+date;
		}
		
		if (inputMonth.toString().length==1){
			inputMonth = "0" + inputMonth.toString();
		}
		
		// Collection Model
		var post_json_obj = {};
		post_json_obj.id = 0; // To be generated
		post_json_obj.orderId = 0;
		post_json_obj.shopId = input_shop_id;
		post_json_obj.userId = 0; // To be changed in controller side
		post_json_obj.collectionTimeSlot = globalYear + '-' + inputMonth + '-' + inputDate + ' ' + arr_office_hours[time_slot_id] + ":00";
		
		var jsonString = JSON.stringify(post_json_obj);
		
		// AJAX Post Method
		var url = "reserveTimeSlot";
		
    	jQuery.ajax({
			type : 'POST',
			url : url + '?' + csrf_para + '=' + csrf_value,
			contentType : 'application/json',
			data : jsonString,
			dataType : 'json',
			success : function(result) {
				if (result)
				{
					arr_currentSelectedData = [];
					// redirect
					location= 'review';
				}	
				else
					alert("Your chosen time slot is not available, please make another selection.");
			}
		});
	} else {
		alert("Please select the time.");
	}
}

// Bug here, TO BE fixed
function createCalendarTable(month, year) {

	var date_data = new Date(months[month] + " 01, " + year);
	var table = "<table class='calendar-table' width='100%'>";

	var tr = "<tr>";

	for (var iDays = 0; iDays < days.length; iDays++) {
		var genTr = "";
		if (iDays == 0 || iDays == 6) {
			genTr = "<th style='color:#ff3333'>" + days[iDays] + "</th>";
		} else {
			genTr = "<th>" + days[iDays] + "</th>";
		}
		tr = tr + genTr;
	}
	tr = tr + "</tr>";

	var append_tr = "";
	var show_date_index = date_data.getDay();
	var today_date = new Date().getDate();
	var today_month = new Date().getMonth();
	var today_year = new Date().getFullYear();
	var total_daysformonth = noofdays(month, year);

	var j_value = 1;
	var starFlag = 0;
	var dateAndDay = 0;
	
	for (var i = 0; i < (total_daysformonth / days.length) + 1 ; i++) {
		var append_td = "";

		for (var j = 0; j < (days.length); j++) {

			if (starFlag == 0) {

				if (j == 0) {
					if (show_date_index != 0) {
						td = "<td style='color:#ffd699'></td>";
					} else {

						if ((today_date == j_value) && (today_month == month)) {
							if(year > today_year || (year == today_year&& month >= today_month && today_date <= dateAndDay)){
								td = "<td id='" + j_value
										+ "'  class='playerTip'  rel='" + j
										+ "'><div class='today_bg_circle-today'>"
										+ j_value + "</div></td>";
							}else{
								td = "<td id='" + j_value
								+ "'><div class='today_bg_circle_disable'>"
								+ j_value + "</div></td>";
							}
						} else {
							if(year > today_year || (year == today_year&& month >= today_month && today_date <= dateAndDay)){
								td = "<td id=\"" + j_value
										+ "\"  class='playerTip' rel='" + j
										+ "'><div class='today_bg_circle'>"
										+ j_value + "</div></td>";
							}else{
								td = "<td id='" + j_value
								+ "'><div class='today_bg_circle_disable'>"
								+ j_value + "</div></td>";
							}
						}
						starFlag = 1;

					}
				} else if (j == 1) {
					if (show_date_index != 1) {
						td = "<td></td>";
					} else {
						if(year > today_year || (year == today_year&& month >= today_month && today_date <= dateAndDay)){
							td = "<td id=" + j_value
									+ "\" class='playerTip' rel='" + j
									+ "' ><div class='today_bg_circle'>" + j_value
									+ "</div></td>";
						}else{
							td = "<td id='" + j_value
							+ "' ><div class='today_bg_circle_disable'>" + j_value
							+ "</div></td>";
						}
						starFlag = 1;

					}
				} else if (j == 2) {
					if (show_date_index != 2) {
						td = "<td></td>";
					} else {
						if(year > today_year || (year == today_year&& month >= today_month && today_date <= dateAndDay)){
							td = "<td id=\"" + j_value
								+ "\" class='playerTip' rel='" + j
								+ "' ><div class='today_bg_circle'>" + j_value
								+ "</div></td>";
						}else{
							td = "<td id='" + j_value
							+ "' ><div class='today_bg_circle_disable'>" + j_value
							+ "</div></td>";
						}
						starFlag = 1;

					}
				} else if (j == 3) {
					if (show_date_index != 3) {
						td = "<td></td>";
					} else {
						if(year > today_year || (year == today_year&& month >= today_month && today_date <= dateAndDay)){
							td = "<td id=\"" + j_value
									+ "\" class='playerTip' rel='" + j
									+ "' ><div class='today_bg_circle'>" + j_value
									+ "</div></td>";
						}else{
							td = "<td id='" + j_value
							+ "'><div class='today_bg_circle_disable'>" + j_value
							+ "</div></td>";
						}
						starFlag = 1;

					}
				} else if (j == 4) {
					if (show_date_index != 4) {
						td = "<td></td>";
					} else {
						if(year > today_year || (year == today_year&& month >= today_month && today_date <= dateAndDay)){
						td = "<td id='" + j_value
								+ "' class='playerTip' rel='" + j
								+ "' ><div class='today_bg_circle'>" + j_value
								+ "</div></td>";
						}else{
							td = "<td id='" + j_value
							+ "'><div class='today_bg_circle_disable'>" + j_value
							+ "</div></td>";
						}
						starFlag = 1;

					}
				} else if (j == 5) {
					if (show_date_index != 5) {
						td = "<td></td>";
					} else {
						if(year > today_year || (year == today_year&& month >= today_month && today_date <= dateAndDay)){
							td = "<td id='" + j_value
									+ "' class='playerTip' rel='" + j
									+ "'><div class='today_bg_circle'>" + j_value
									+ "</div></td>";
						}else{
							td = "<td id='" + j_value
								+ "'><div class='today_bg_circle_disable'>" + j_value
								+ "</div></td>";
						}
						starFlag = 1;

					}
				} else if (j == 6) {
					if (show_date_index != 6) {
						td = "<td style='color:#ffd699'></td>";
					} else {
						if ((today_date == j_value) && (today_month == month)) {
							if(year > today_year || (year == today_year&& month >= today_month && today_date <= dateAndDay)){
								td = "<td class='playerTip'  rel='" + j
										+ "'><div class='today_bg_circle-today'>"
										+ j_value + "</div></td>";
							}else{
								td = "<td><div class='today_bg_circle-today'>"
								+ j_value + "</div></td>";
							}
						} else {
							if(year > today_year || (year == today_year&& month >= today_month && today_date <= dateAndDay)){
								td = "<td id='" + j_value
										+ "' class='playerTip'  rel='" + j
										+ "' ><div class='today_bg_circle'>"
										+ j_value + "</div></td>";
							}else{
								td = "<td id='" + j_value
								+ "'><div class='today_bg_circle_disable'>"
								+ j_value + "</div></td>";
							}
						}
						starFlag = 1;

					}
				}
				dateAndDay = j_value;
			} else {
				if (dateAndDay < total_daysformonth) {
					dateAndDay = (j_value + 1);
					if (j == 0 || j == 6) {
						if ((dateAndDay == today_date)
								&& (today_month == month)) {
							if(year > today_year || (year == today_year&& month >= today_month && today_date <= dateAndDay)){
								td = "<td id='" + dateAndDay
										+ "' class='playerTip'  rel='" + j
										+ "'><div class='today_bg_circle-today'>"
										+ dateAndDay + "</div></td>";
							}else{
								td = "<td id='" + dateAndDay
								+ "'><div class='today_bg_circle_disable'>"
								+ dateAndDay + "</div></td>";
							}
						} else {
							if(year > today_year || (year == today_year&& month >= today_month && today_date <= dateAndDay)){
								td = "<td id='" + dateAndDay
										+ "' class='playerTip' rel='" + j
										+ "'><div class='today_bg_circle'>"
										+ dateAndDay + "</div></td>";
							}else{
								td = "<td id='" + dateAndDay
								+ "'><div class='today_bg_circle_disable'>"
								+ dateAndDay + "</div></td>";
							
							}
						}
					} else {
						if ((dateAndDay == today_date) && (today_month == month)) {
							td = "<td id='" + dateAndDay
									+ "'  class='playerTip' rel='" + j
									+ "'  ><div class='today_bg_circle-today'>"
									+ dateAndDay + "</div></td>";
						} else {
						
							// alert ("year:"+year+"|now year:"+today_year+"month:"+month+"|now month:"+today_month+"today_date:"+dateAndDay+"|now today_date:"+today_date);
							 
							if(year > today_year || (year == today_year&& month >= today_month && today_date <= dateAndDay)){
								td = "<td id='" + dateAndDay
										+ "' class='playerTip' rel='" + j
										+ "'  ><div class='today_bg_circle'>"
										+ dateAndDay + "</div></td>";
							}else{
								
								td = "<td id='" + dateAndDay+ "'  ><div class='today_bg_circle_disable'>"
								+ dateAndDay + "</div></td>";
							}
						}
					}

				} else {
					if (j == 0 || j == 6) {
						td = "<td style='color:#ffd699'></td>";
					} else {
						td = "<td></td>";
					}

				}
			}
			append_td += td;
			j_value = dateAndDay;
		}
		append_tr += "<tr id=\"" + dateAndDay + "\" >" + append_td + "</tr>";
	}

	var table_end = "</table>";

	return table + tr + append_tr + table_end;
}
