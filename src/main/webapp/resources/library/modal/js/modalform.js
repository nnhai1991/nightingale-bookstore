$(function () {
    // Initialize numeric spinner input boxes
    //$(".numeric-spinner").spinedit();
    // Initialize modal dialog
    // attach modal-container bootstrap attributes to links with .modal-link class.
    // when a link is clicked with these attributes, bootstrap will display the href content in a modal dialog.
    $('body').on('click', '.modal-link', function (e) {
        e.preventDefault();

        $('#progress-indicator').show();

        $('#modal-dialog').hide();

        $('#modal-container').modal({
        }, 'show');

        $('#modal-content').load(this.href, function () {
            $('#progress-indicator').hide();
            $('#modal-dialog').show();
            bindForm(this);
        });

        return false;
    });

    // Attach listener to .modal-close-btn's so that when the button is pressed the modal dialog disappears
    $('body').on('click', '.modal-close-btn', function () {
        $('#modal-container').modal('hide');        
    });

    //clear modal cache, so that new content can be loaded
    $('body').on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
    });
    $('#CancelModal').on('click', function () {
        return false;
    });
});

function bindForm(dialog) {
    $('form', dialog).submit(function () {

        var dataString;
        if ($('form').attr("enctype") == "multipart/form-data") {

            //dataString = new FormData($('form').get(0));
            //contentType = false;
            //processData = false;

            //$.ajax({
            //    url: this.action,
            //    type: this.method,
            //    data: dataString,
            //    contentType: contentType,
            //    processData: processData,
            //    success: function (result) {
            //        if (result.success) {
            //            $('#modal-container').modal('hide');
            //            $(this).removeData('bs.modal');
            //            //Refresh
            //            location.reload();
            //        } else {
            //            $('#modal-content').html(result);
            //            bindForm();
            //        }
            //    }
            //});
        } else {
            $.ajax({
                url: this.action,
                type: this.method,
                data: $(this).serialize(),
                success: function (result) {
                    if (result.success) {
                        $('#modal-container').modal('hide');
                        $(this).removeData('bs.modal');
                        //Refresh
                        location.reload();
                    } else {
                        $('#modal-content').html(result);
                        bindForm();
                    }
                }
            });
        }

        return false;
    });
}