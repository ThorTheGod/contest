document.write("script language='javascript' src='login.js'></script")
document.write("script language='javascript' src='exit.js'></script")

function logout(){
    $("#nav22").on("click","#logout",function() {exitshow();})
};

$(function(){
    $.ajax({
        url:"/html/about.html/ifLogined",
        type:"post",
        dataType:"json",
        async:true,
        success:function(data){
            if(data.statecode==1) {   //普通用户登录状态
                loginhide();
                hpshow();
                $("#nav22").empty();
                $("").prependTo("#nav22");
                $("#nav22").append("<a id='logout' onclick='logout()'>退出</a>");
                $("#nav22").css("float:right;display: inline-block;text-align: center;text-decoration:none;display:block;font-size: 24px; font-family: '微软雅黑';color:white;padding: 22px 20px;");
                $("#nav21").empty();
                $("").prependTo("#nav21");
                $("#nav21").append("<a>欢迎用户</a>").append(data.username);
                $("#nav21").css("float:right;display: inline-block;text-align: center;text-decoration:none;display:block;font-size: 24px; font-family: '微软雅黑';color:white;overflow: hidden;padding: 22px 20px;");
                $("#nav13").click(function() {window.location.href="../html/competition.html";})   //点击我要参赛
                $(document).on("click","#nav21",function() {window.location.href="../html/personal-center.html";})  //点击欢迎用户
            }
            else if(data.statecode==2) {   //管理员登录
                loginhide();
                hpshow();
                $("#nav1").append("<li><a>题目上传</a></li>");
                $("#nav22").empty();
                $("").prependTo("#nav22");
                $("#nav22").append("<a id='logout' onclick='logout()'>退出</a>");
                $("#nav22").css("float:right;display: inline-block;text-align: center;text-decoration:none;display:block;font-size: 24px; font-family: '微软雅黑';color:white;padding: 22px 20px;");
                $("#nav21").empty();
                $("").prependTo("#nav21");
                $("#nav21").append("<a>欢迎管理员</a>").append(data.username);
                $("#nav21").css("float:right;display: inline-block;text-align: center;text-decoration:none;display:block;font-size: 24px; font-family: '微软雅黑';color:white;overflow: hidden;padding: 22px 20px;");   
                $("#nav13").click(function() {window.location.href="../html/competition.html";})   //点击我要参赛
                $(document).on("click","#nav21",function() {window.location.href="../html/personal-center.html";})  //点击欢迎用户
            }
            else if(data.statecode==0){ //未登录状态
            $("#nav13").click(function() {loginshow();})  //点击我要参赛
            $("#nav21").click(function() {loginshow();})   //点击登录
            }
        
        }
    });

    $(".l-button1").click(function() {
        $.ajax({
            url:"/html/about.html/login",
            type:"post",
            data:$("#loginform").serialize(),
            dataType:"json",
            async:true,
            success:function(data){
                if(data.statecode==1) {
                    loginhide();
                    hpshow();
                    $("#nav22").empty();
                    $("").prependTo("#nav22");
                    $("#nav22").append("<a id='logout' onclick='logout()'>退出</a>");
                    $("#nav22").css("float:right;display: inline-block;text-align: center;text-decoration:none;display:block;font-size: 24px; font-family: '微软雅黑';color:white;padding: 22px 20px;");
                    $("#nav21").empty();
                    $("").prependTo("#nav21");
                    $("#nav21").append("<a>欢迎用户</a>").append(data.username);
                    $("#nav21").css("float:right;display: inline-block;text-align: center;text-decoration:none;display:block;font-size: 24px; font-family: '微软雅黑';color:white;overflow: hidden;padding: 22px 20px;");
                    
                }
                else if(data.statecode==0) alert("用户名或密码错误！请重新输入。");
                else if(data.statecode==2) {
                    loginhide();
                    hpshow();
                    $("#nav1").append("<li><a>题目上传</a></li>");
                    $("#nav22").empty();
                    $("").prependTo("#nav22");
                    $("#nav22").append("<a id='logout' onclick='logout()'>退出</a>");
                    $("#nav22").css("float:right;display: inline-block;text-align: center;text-decoration:none;display:block;font-size: 24px; font-family: '微软雅黑';color:white;padding: 22px 20px;");
                    $("#nav21").empty();
                    $("").prependTo("#nav21");
                    $("#nav21").append("<a>欢迎管理员</a>").append(data.username);
                    $("#nav21").css("float:right;display: inline-block;text-align: center;text-decoration:none;display:block;font-size: 24px; font-family: '微软雅黑';color:white;overflow: hidden;padding: 22px 20px;");   
                }
                else if(data.statecode==-1) alert("用户名不存在！");
                else if(data.statecode==-2) alert("密码错误！");
                else alert("登录异常！");
            },
        });
    });
    
});
