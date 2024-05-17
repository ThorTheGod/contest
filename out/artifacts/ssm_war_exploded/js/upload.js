document.write("script language='javascript' src='exit.js'></script")

$(function() {
    $.ajax({
        url:"/html/upload.html/ifLogined",
        type:"post",
        dataType:"json",
        async:true,
        success:function(data){
            if(data.statecode==2) {
                $("#nav21").append(data.username);
            }
        }
    });

    $(".btn-upload").click(function(){   //点击立即上传题库
        var formData = new FormData($("#uploadForm")[0]);
        $.ajax({
            url:"/html/upload.html/upload",
            type:"post",
            data:formData,
            cache:false,
            contentType:false,
            processData:false,
            dataType:"json",
            //async:true,
            success:function(){
                alert("上传成功");
            },
            error:function() {
                alert("失败");
            }
        });
    })
});