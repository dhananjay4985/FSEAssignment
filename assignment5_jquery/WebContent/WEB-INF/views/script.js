$(document).ready(function(){
    $("#add_li").click(function (){
    $("ul").append("<li>" + $("input").val() + "<a href=\"#\" class=\"remove\"><span class='glyphicon glyphicon-remove'></span></a></li>");
    $('#candidate').val('');
	});	
    $("ul").on('click','.remove',function(){
    $(this).parents('li').remove();
    });
});
