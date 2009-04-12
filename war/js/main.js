$(document).ready(function() {
    $("#executeButton").click(function(event) {
		$.ajax({
		   	type: "POST",
		    url: "executor.groovy",
		    data: { script: editor.getCode() },
			dataType: "json",
		    success: function(data) {
				if (data.executionResult.length > 0) {
					$('#result').text(data.executionResult).fadeIn();
				} else {
					$('#result').fadeOut();
				}
				
				if (data.outputText.length > 0) {
					$('#output').text(data.outputText).fadeIn();
				} else {
					$('#output').fadeOut();
				}
				
				if (data.stacktraceText.length > 0) {
					$('#stacktrace').text(data.stacktraceText).fadeIn();
				} else {
					$('#stacktrace').fadeOut();
				}
				
		    }
		});
    });
});