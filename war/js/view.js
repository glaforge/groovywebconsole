$(document).ready(function() {
    $("#toggleLineNumbers").click(function(event) {
        if ($(".syntaxhighlighter").hasClass("nogutter")) {
            $(".syntaxhighlighter").removeClass("nogutter");
        } else {
            $(".syntaxhighlighter").addClass("nogutter");
        }
    });
});