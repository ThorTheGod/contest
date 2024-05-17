document.write("script language='javascript' src='exit.js'></script")

$(function(){

    var result_arr ={"a":["False","False","False","True","False","False","False","True","False","False","True","False","False","True","False","False","True","True","False","False","False","False","True","True","False","False","False","False","False","True","False","False","True","False","True","False","True","True","False","False"]};
    //正误颜色
    for(i=0;i<=39;i++){
        j =i+1;
        if( result_arr.a[i] == "True"){
            document.getElementById('td_'+ j).style.backgroundColor="#b9febd";//绿色
            document.getElementById('td_'+ j).style.borderColor="#00FF00";
        }
        else{
            document.getElementById('td_'+ j).style.backgroundColor="#feadad";//红色
            document.getElementById('td_'+ j).style.borderColor="#FF0000";
        }
    }



    $.ajax({
        url:"/html/result.html/ifLogined",
        type:"post",
        dataType:"json",
        async:true,
        success:function(data){
            // if(data.statecode==1) {
            //     $("#nav21").append("用户").append(data.username);
            // }
            // else if(data.statecode==2) {
            //     $("#nav1").after("<li><a>题目上传</a></li>");
            //     $("#nav21").append("管理员").append(data.username);
            // }
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
    //读取结果
    $.ajax({
        url:"/html/result.html/getJudge",
        type:"post",
        dataType:"json",
        async:true,
        success:function(data){


        }


    })




});