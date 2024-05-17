document.write("script language='javascript' src='exit.js'></script")

$(function(){
    $.ajax({
        url:"/html/competition.html/ifLogined",
        type:"post",
        dataType:"json",
        async:true,
        success:function(data){
            if(data.statecode==1) {
                $("#nav21").append("用户").append(data.username);
            }
            else if(data.statecode==2) {
                $("nav1").append("<li><a>题目上传</a></li>");
                $("#nav21").append("管理员").append(data.username);
            }
        }
    });
});
