document.write("script language='javascript' src='login.js'></script")
document.write("script language='javascript' src='exit.js'></script")
document.write("script language='javascript' src='download.js'></script")

function logout(){
    $("#nav22").on("click","#logout",function() {exitshow();})
};

$(function() {
    //登录状态判断
    $.ajax({
        url:"/html/ranking.html/ifLogined",
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
                dlshow();  //下载排行榜出现
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
    //填充排行榜
    $.ajax({
        url:'/html/ranking.html/top',
        type:"post",
        dataType:'json',
        async:true,
        success:function(data){
            var time = "";
            var rank = 0;
            for (index in data){
                time = formatSeconds(data[index].time);
                rank = parseInt(index)+1;
                $("#rank").append("<tr><td>"+rank+"</td><td>"+data[index].name+"</td><td>"+data[index].firstUnit+" "+data[index].secondUnit+"</td><td>"+data[index].score+"</td><td>"+time+"</td></tr>");
            }
        }            
    });


    
    
    $("#download").click(function() {     //点击下载当前排行榜（管理员登录时出现）
        dfshow();    //下载确认弹窗出现
        $(".dl-button2").click(function() {   //点击确认下载按钮
            dfhide();
            alert("请稍候~");
            // $.ajax({
            //     url:"/html/ranking.html/download",
            //     type:"post",
            //     cache:false,
            //     contentType:false,
            //     processData:false,
            //     dataType:"json",
            //     success:function(){
            //         alert("下载成功!")
            //     }
            // })
        })
    })

    $(".l-button1").click(function() {
        $.ajax({
            url:"/html/ranking.html/login",
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
                    dlshow();
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

//转化用时
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

