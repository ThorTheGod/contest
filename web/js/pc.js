document.write("script language='javascript' src='exit.js'></script")

$(function() {
    //个人信息填充
        $.ajax({
            url:'/html/personal-center.html/userInfo',
            type:"post",
            dataType:'json',
            async:true,
            success:function(data){              
                $("#table1").append("<tr><td>"+"用户名："+data.user.username+"</td><td>"+"学校："+data.user.school+"</td></tr>");
                $("#table1").append("<tr><td>"+"出生日期："+data.user.birthday+"</td><td>"+"学院："+data.user.college+"</td></tr>");  
                $("#table1").append("<tr><td>"+"联系电话："+data.user.contact+"</td><td>"+"职务："+data.user.duty+"</td></tr>");  
                $("#table1").append("<tr><td>"+"邮箱："+data.user.email+"</td></tr>");
                $("#table2").append("<tr><td>"+"得分："+data.grade.grade+"</td><td>"+"学校排名："+data.first+"</td></tr>");
                $("#table2").append("<tr><td>"+"用时："+formatSeconds(data.grade.time)+"</td><td>"+"学院排名："+data.second+"</td></tr>");  
                $("#table2").append("<tr><td>"+"总体排名："+data.rank+"</td></tr>");
            },
        });
    //登录状态判断
        $.ajax({
            url:"/html/personal-center.html/ifLogined",
            type:"post",
            dataType:"json",
            async:true,
            success:function(data){
                if(data.statecode==1) {
                    $("#nav21").append("用户").append(data.username);
                }
                else if(data.statecode==2) {
                    $("nav1").after("<li><a>题目上传</a></li>"); 
                    $("#nav21").append("管理员").append(data.username);
                }
            }
        });
});

function formatSeconds(value) {
    var theTime = parseInt(value);// 秒
    var theTime1 = 0;// 分
    var theTime2 = 0;// 小时
    if(theTime > 60) {
        theTime1 = parseInt(theTime/60);
        theTime = parseInt(theTime%60);
        if(theTime1 > 60) {
            theTime2 = parseInt(theTime1/60);
            theTime1 = parseInt(theTime1%60);
        }
    }
    var result = ""+parseInt(theTime)+"秒";
        result = ""+parseInt(theTime1)+"分"+result;
        result = ""+parseInt(theTime2)+"小时"+result;
    return result;
}
