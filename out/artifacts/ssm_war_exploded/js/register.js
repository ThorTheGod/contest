document.write("script language='javascript' src='login.js'></script")
//用户名查重
$(function(){
    $("#check").click(checkname=function(a){
            $.ajax({
                url:"/html/register.html/checkname",
                type:"post",
                data:$("#username").serialize(),
                async:true,
                dataType:"json",
                success:function(data){
                    if(data.statecode==1) {
                        alert("用户名可以使用");
                        return a=1;
                }
                    else if(data.statecode==0) alert("用户名已存在！请重新输入");
                    else if(data.statecode==-1) alert("请输入包含   的用户名！")
                },
                error:function(){
                    alert("传送失败")
                }
            });
        });
});
/*
//密码强度
$(function() {
    regex = new RegExp('(?=^.{8,}$)((?=.*\d+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$');
    pwd = $("#password");
    if(!regex.test(pwd)){
        alert("输入错误：密码不符合规则，请重新输入。");
        $("#pwd").focus();
        return;
    }
})
*/

$(function(){
    $("#vCode").click(function(){
        $.ajax({
            url:"/html/register.html/sendMail",
            type:"post",
            data:$("#email").serialize(),
            //contentType:"application/json;charset='utf-8",//发送给后台数据的格式
            async:true,
            dataType:"json",
            success:function(response){
                if(response.statecode==1) alert("验证码发送成功，请登录邮箱查收");
            },
            error: function(){
                alert("传送失败")
            }
        });
    });
});

$(function(){
    $(".r-button1").click(function() {
        //if(checkname(1)){
            $.ajax({
                url:"/html/register.html/register",
                type:"post",
                data:$("#registerform").serialize(),
                dataType:"json",
                async:true,
                success:function(data){
                    if(data.statecode==1) 
                    {
                        alert("注册成功!可登录");
                        window.location.href="../html/mainweb.html";
                        loginshow();
                    }
                },
            });
        //}
        //else alert("用户名不可用！请重新输入");
    });
});