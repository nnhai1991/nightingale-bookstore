/*****
 * 
 * Author: Roger Lau
 * 
 */

function initStickyTableHeader() {
    var listTable = $('.list-table:not(.sticky-table-header)');
    
    // Execute only when there is a list-table
    if (listTable.length > 0) {
        // Duplicate header and make it sticky
        theadHTML = listTable.find('thead').html();
        listTable.after("<table class='sticky-table-header'></table>");
        
        var stickyHeader = $('.sticky-table-header');
        stickyHeader.append(theadHTML);
        stickyHeader.addClass(listTable.attr('class'));
        
        resizeStickyTableHeader();

        var headerTop = $('.list-table:not(.sticky-table-header)').position().top;

        $(window).scroll(function () {
            if ($(this).scrollTop() > headerTop) {
                $('.list-table.sticky-table-header').css("display", "block");
            } else {
                $('.list-table.sticky-table-header').css("display", "none");
            }
        });

        var resizeTimer;
        $(window).resize(function () {
            clearTimeout(resizeTimer); // Use this as a hack to execute the function only when the resize is done
            resizeTimer = setTimeout(resizeStickyTableHeader, 250);
        });
    }
}

function resizeStickyTableHeader() {
    var thWidth = [];
    var stickyWidth = [];

    var listTable = $('.list-table:not(.sticky-table-header)');
    var stickyHeader = $('.sticky-table-header');

    stickyHeader.css('width', listTable.css('width'));

    listTable.find('th').each(function (index) {
        thWidth.push($(this).css('width'));
        $(this).css('width', thWidth[index]);
    });

    stickyHeader.find('th').each(function (index) {
        //$(this).width(250);
        $(this).css('width', thWidth[index]);
        stickyWidth.push($(this).css('width'));
    });
}