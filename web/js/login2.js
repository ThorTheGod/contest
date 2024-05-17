document.write("script language='javascript' src='login.js'></script")
document.write("script language='javascript' src='exit.js'></script")

function logout(){
    $("#nav22").on("click","#logout",function() {exitshow();})
};

function up(){
    $("#nav1").on("click","#up",function (){window.location.href="../html/upload.html";})
};

$(function(){
    $.ajax({
        url:"/html/register.html/ifLogined",
        type:"post",
        dataType:"json",
        async:true,
        success:function(data){
            if(data.statecode==1 || data.statecode==2) {
                $("#nav13").click(function() {window.location.href="../html/competition.html";})
                $(document).on("click","#nav21",function() {window.location.href="../html/personal-center.html";})
            }
            else if(data.statecode==0){
                $("#nav13").click(function() {loginshow();})
                $("#nav21").click(function() {loginshow();})
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
                    $("#nav1").append("<li><a id='up' onclick='up()'>题目上传</a></li>");
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
