$(document).ready(function() {
	$(function() {
		$("#tabs").tabs();
		$("#textarea-container").resizable({ handles: 's', alsoResize: 'iframe' });
	});
		
    $("#executeButton").click(function(event) {
		$.ajax({
		   	type: "POST",
		    url: "executor.groovy",
		    data: { script: editor.getCode() },
			dataType: "json",
		    success: function(data) {				
				if (data.outputText.length > 0) {
					$("#tabs").tabs('select', 1);
					$('#output').text(data.outputText).fadeIn();
				} else {
					$('#output').fadeOut();
				}

				if (data.executionResult.length > 0) {
					if (data.executionResult != "null") {
						$("#tabs").tabs('select', 0);
					}
					$('#result').text(data.executionResult).fadeIn();
				} else {
					$('#result').fadeOut();
				}
				
				if (data.stacktraceText.length > 0) {
					$("#tabs").tabs('select', 2);
					$('#stacktrace').text(data.stacktraceText).fadeIn();
				} else {
					$('#stacktrace').fadeOut();
				}
				
		    }
		});
    });

	$('#loadingDiv')
	    .hide()
	    .ajaxStart(function() {
	        $(this).show();
	    })
	    .ajaxStop(function() {
	        $(this).hide();
	    });
});