$(document).ready(function() {
    $(".author-input").click(function(){
        let input = $("#author").clone();
        input.val("");
        input.attr('value',' ');
        $(".author-box").append(input);
    })
});