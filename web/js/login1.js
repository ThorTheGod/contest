document.write("script language='javascript' src='login.js'></script")
document.write("script language='javascript' src='exit.js'></script")

function topTen(){   //填充排行榜前十
    $.ajax({
        url:"/topTen",
        type:"post",
        dataType:"json",
        async:true,
        success:function(data){
            $("#r1").append(data[0].name);
            $("#r2").append(data[1].name);
            $("#r3").append(data[2].name);
            $("#r4").append(data[3].name);
            $("#r5").append(data[4].name);
            $("#r6").append(data[5].name);
            $("#r7").append(data[6].name);
            $("#r8").append(data[7].name);
            $("#r9").append(data[8].name);
            $("#r10").append(data[9].name);
        }
    });
};

function up(){
    $("#nav1").on("click","#up",function (){window.location.href="../html/upload.html";})
};

function logout(){
    $("#nav22").on("click","#logout",function() {exitshow();})
};

$(function(){
    topTen();
    console.log("准备判断状态");
    // $.ajax({   //判断是否登录
    //     url:"/ifLogined",
    //     type:"post",
    //     dataType:"json",
    //     async:true,
    //     success:function(data){
    //         if(data.statecode==1) {   //普通用户登录状态
    //                 loginhide();
    //                 hpshow();
    //                 $("#nav22").empty();
    //                 $("").prependTo("#nav22");
    //                 $("#nav22").append("<a id='logout' onclick='logout()'>退出</a>");
    //                 $("#nav22").css("float:right;display: inline-block;text-align: center;text-decoration:none;display:block;font-size: 24px; font-family: '微软雅黑';color:white;padding: 22px 20px;");
    //                 $("#nav21").empty();
    //                 $("").prependTo("#nav21");
    //                 $("#nav21").append("<a>欢迎用户</a>").append(data.username);
    //                 $("#nav21").css("float:right;display: inline-block;text-align: center;text-decoration:none;display:block;font-size: 24px; font-family: '微软雅黑';color:white;overflow: hidden;padding: 22px 20px;");
    //                 $("#nav13").click(function() {window.location.href="../html/competition.html";})   //点击我要参赛
    //                 $(document).on("click","#nav21",function() {window.location.href="../html/personal-center.html";})  //点击欢迎用户
    //                 $(".btn").click(function() {window.location.href="../html/competition.html";})   //点击海报内按钮
    //             }
    //         else if(data.statecode==2) {   //管理员登录
    //                 loginhide();
    //                 hpshow();
    //                 $("#nav1").after("<li><a>题目上传</a></li>");
    //                 $("#nav22").empty();
    //                 $("").prependTo("#nav22");
    //                 $("#nav22").append("<a id='logout' onclick='logout()'>退出</a>");
    //                 $("#nav22").css("float:right;display: inline-block;text-align: center;text-decoration:none;display:block;font-size: 24px; font-family: '微软雅黑';color:white;padding: 22px 20px;");
    //                 $("#nav21").empty();
    //                 $("").prependTo("#nav21");
    //                 $("#nav21").append("<a>欢迎管理员</a>").append(data.username);
    //                 $("#nav21").css("float:right;display: inline-block;text-align: center;text-decoration:none;display:block;font-size: 24px; font-family: '微软雅黑';color:white;overflow: hidden;padding: 22px 20px;");
    //                 $("#nav13").click(function() {window.location.href="../html/competition.html";})   //点击我要参赛
    //                 $(document).on("click","#nav21",function() {window.location.href="../html/personal-center.html";})  //点击欢迎用户
    //                 $(".btn").click(function() {window.location.href="../html/competition.html";})   //点击海报内按钮
    //             }
    //         else if(data.statecode==0){ //未登录状态
    //             console.log("未登录");
    //             $("#nav13").click(function() {loginshow();})  //点击我要参赛
    //             $("#nav21").click(function() {loginshow();})   //点击登录
    //             $(".btn").click(function() {loginshow();})   //点击海报内按钮
    //         }
    //
    //     }
    // });
    $.ajax({   //判断是否登录
        url:"/html/mainweb.html/ifLogined",
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
                $(".btn").click(function() {window.location.href="../html/competition.html";})   //点击海报内按钮
            }
            else if(data.statecode==2) {   //管理员登录
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
                $("#nav13").click(function() {window.location.href="../html/competition.html";})   //点击我要参赛
                $(document).on("click","#nav21",function() {window.location.href="../html/personal-center.html";})  //点击欢迎用户
                $(".btn").click(function() {window.location.href="../html/competition.html";})   //点击海报内按钮
            }
            else if(data.statecode==0){ //未登录状态
                console.log("未登录");
                $("#nav13").click(function() {loginshow();})  //点击我要参赛
                $("#nav21").click(function() {loginshow();})   //点击登录
                $(".btn").click(function() {loginshow();})   //点击海报内按钮
            }

        }
    });
    
    $(".l-button1").click(function() {  //点击登录弹窗确认登录按钮
        $.ajax({
            url:"/html/mainweb.html/login",
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