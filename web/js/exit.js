var exit = document.getElementById('exit');

    exit.style.display = 'none';

    function exitshow()
    {
        exit.style.display = 'block';
    }
    function exithide()
    {
        exit.style.display = 'none';
    }

$(function(){
    $(".e-button2").click(function(){
        $.ajax({
            url:"/logout",
            type:"post",
            data:0,
            dataType:"json",
            async:true,
            success:function(){
                alert("退出成功！");
                window.location.href="../html/mainweb.html";
            }
        });
    });
});