let time = 2 * 60 * 60 * 1000; // 定义一个两小时的毫秒数
let iter = window.setInterval(()=>{
    if(time <= 0){
        // 取消定时器(退出轮询)
        window.clearInterval(iter);
        return;
    }
    time -= 1000; // 进入轮询，将剩余时间减去1000毫秒
    let date = new Date(time);
    let hour = "0" + (date.getHours() - 8); // 北京是东八区，北京时间-8小时 = 格林尼治时间
    let min = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    let sec = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    document.querySelector("#time").innerText = `倒计时：${hour}时${min}分${sec}秒`;
    if (hour == 0 && min == 0 && sec < 1) {
        alert("答题时间结束");
        // window.location.href = "result.html?radio=" + 5 + "checkbox=" + 2 + "&json=" + getjson();
    }
    //用时
    let yongshi_all = 2 * 60 * 60 * 1000 - time;
    let yongshi_hour = yongshi_all/3600000;
    let yongshi_min = yongshi_all/60000 % 60;
    let yongshi_sec = yongshi_all % 60000;
    document.querySelector("#ystime").innerText = "用时：${yongshi_hour}时${yongshi_min}分${yongshi_sec}秒";
},1000);

$(function(){
    var id = 0;
    var result = "";
    var page = 0;
    yongshi_all = 0;
    //生成试卷
    $.ajax({
        url: "/html/answer.html/excel",
        type: "post",
        dataType: "json",
        async: false,
        success:function (data){
            if(data.statecode==0) window.location.href="../html/mainweb.html";
            else if (data.statecode==-1){
                alert("请勿重复参赛！");
            }
        }
    })

    //进入页面就添加第一页的题目
    $.ajax({
        url: "/html/answer.html/question",
        type: "post",
        dataType: "json",
        data: {"id":id},
        async: true,
        success:function (data){
            id = Number(id) + Number(1);
            addElement(data);
        }
    })
    //点击下一页时
    $("#nextpage").click(function() {
        console.log("点击了下一页");
        var perPage = "";
        for (var i = 0; i <=9; i++) {
            flag = $("input[name='radio" + (i+1) + "']:checked").val(); //取出回答
            perPage = perPage + flag + ",";
        }
        console.log("perPage:"+perPage);
        result = result +perPage;
        $.ajax({
            url: "/html/answer.html/question",
            type: "post",
            dataType: "json",
            data: {"id":id},
            async: false,
            success: function (data) {
                //获得下一页数据
                exam = data;
                //准备转到下一页
                page++;
                //结果集
                x = 0;
                idFlag = 0;
                idFlag2 = 0;
                var flag = $("input[name='radio" + i + "']:checked").val();
                //判断未填
                // if (flag == null) {
                //      alert("您还有未填项，无法提交!");
                //      return false;
                // }
                    //if (i<9)
                        //字符串拼接
                        //perPage = perPage+flag+",";
                    //else
                    //    perPage = perPage+flag;
                    //清空内容并替换
                for (var i = 0; i <=9; i++){
                    if(page<=1){    //第二页时
                        order = Number(i);
                        console.log("order:"+order);
                        $("#title_"+flag+"").empty();
                        $("#title_"+flag+"").append("<span>"+flag+" "+exam.singleSelection_fronts[order].stem+"</span>");
                        for(var optionOrder = 0;optionOrder<4;optionOrder++){
                            idFlag = Number(1)+idFlag;
                            $("#"+idFlag+"").empty();
                            $("#"+idFlag+"").append(exam.singleSelection_fronts[Number(i)].option[optionOrder]);
                        }
                        if(page==1) order = 0;
                    }
                    else if(page<=3){

                        order = Number(i);
                        $("#title_"+flag+"").empty();
                        $("#title_"+flag+"").append("<span>"+flag+" "+exam.judgment_fronts[order].stem+"</span>");
                        for(var optionOrder = 0;optionOrder<4;optionOrder++){
                            idFlag2 = Number(1)+idFlag2;
                            $("#"+idFlag2+"").empty();
                            if (idFlag2%4==1)
                                $("#"+idFlag2+"").append("<span>"+"正确"+"</span>");
                            else (idFlag2%4==2)
                                $("#"+idFlag2+"").append("<span>"+"错误"+"</span>");
                            //$("#"+idFlag+"").append(exam.judgment_fronts[Number(i)].option[optionOrder]);
                        }
                    }
                }
                //更改id值以传不同数据
                id = Number(id) + Number(1);
            }

        })
    })
    // var i = 0;
    // result = "";
    // //第一次进入页面时添加元素
    // addElement();
    // page = 0;
    //
    // //点击下一页时，先遍历当前页面并取值
    // $("#nextpage").click(function() {
    //     page++;
    //     //radio
    //     var perPage = "";
    //     x = 0;
    //     idFlag = 0;
    //     //对每页
    //     for (var i = 0; i <=9; i++) {
    //         flag = Number(i)+Number(1); //1开始
    //         //var flag = $("input[name='radio" + i + "']:checked").val();
    //         //判断未填
    //         // if (flag == null) {
    //         //     alert("您还有未填项，无法提交!");
    //         //     return false;
    //         //     var perPage = "";
    //         // }
    //         if (i<9)
    //             //字符串拼接
    //             perPage = perPage+flag+",";
    //         else
    //             perPage = perPage+flag;
    //         //取值后清空内容并替换
    //         if(page<=1){    //第一页或第二页时
    //             order = Number(i)+Number(10);
    //             $("#title_"+flag+"").empty();
    //             $("#title_"+flag+"").append("<span>"+flag+" "+ques1.singleSelection_fronts[order].stem+"</span>");
    //             for(var optionOrder = 0;optionOrder<4;optionOrder++){
    //                 idFlag = Number(1)+idFlag;
    //                 console.log("idFlag:"+idFlag)
    //                 $("#"+idFlag+"").empty();
    //                 $("#"+idFlag+"").append(ques1.singleSelection_fronts[Number(i)+Number(10*page)].option[optionOrder]);
    //             }
    //             if(page==1) order = 0;
    //         }
    //         else if(page<=3){
    //             order = Number(i)+Number(10*(page-2));
    //             $("#title_"+flag+"").empty();
    //             $("#title_"+flag+"").append("<span>"+flag+" "+ques1.judgment_fronts[order].stem+"</span>");
    //             for(var optionOrder = 0;optionOrder<2;optionOrder++){
    //                 idFlag = Number(1)+idFlag;
    //                 $("#"+idFlag+"").empty();
    //                 $("#"+idFlag+"").append(ques1.judgment_fronts[Number(i)+Number(10*page)].option[optionOrder]);
    //             }
    //         }
    //     }
    //
    // });
    //result = result+100;
    $("#submit").click(function () {
        var perPage = "";
        for (var i = 0; i <=9; i++) {
            flag = $("input[name='radio" + (i+1) + "']:checked").val(); //取出回答
            if(i<9)
                perPage = perPage + flag + ",";
            else
                perPage = perPage + flag;
        }
        console.log("perPage:"+perPage);
        result = result+perPage;
        $.ajax({
            url: "/html/competition.html/judge",
            type: "post",
            dataType: "json",
            data: "result="+result,
            async: false,
            success: function (data) {
                alert("答卷已提交！");
                window.location.href="../html/result.html";
            }

        })
    })



});

function addElement(exam){
    //[{"stem":"《廉政准则》规定，不准干预和插手批办各类行政许可和（）等事项。","orderOfOriBank":"64","option":["资金审核","资金借贷","资金抵押",""]},
    // {"stem":"坚决惩治和有效预防腐败，关系人心向背和党的（），是党必须始终抓好的重大政治任务。","orderOfOriBank":"3","option":["生机活力","务实清廉","执政能力","生死存亡",""]},{"stem":"党的各级委员会实行()。","orderOfOriBank":"39","option":["集中领导和分工负责相结合的制度","集体领导和个人分工负责相结合的制度","集体领导和分工负责相结合的制度","",""]},{"stem":"培养干部要以提高素质（）改进作风和增强团结为重点，把各级领导班子建设成为坚持贯彻“三个代表”重要思想的坚强领导集体。","orderOfOriBank":"7","option":["优化人员","优化结构","优化年龄",""]},{"stem":"四项基本原则的核心是（）。","orderOfOriBank":"72","option":["坚持社会主义道路","坚持共产党领导","坚持人民民主专政","",""]},{"stem":"《廉政准则》规定，党员领导干部不准个人或者借他人名义（）。","orderOfOriBank":"10","option":["经商炒股","炒股办企业","经商办企业",""]},{"stem":"党的（）首次使用“邓小平理论”这个科学称谓。","orderOfOriBank":"75","option":["十三大","十四大","十五大","",""]},{"stem":"社会保障体系中最基本的内容是（   ）。","orderOfOriBank":"13","option":["社会救助","社会福利","社会优抚","社会保险"]},{"stem":"()运动的爆发是掀起抗日救亡运动新高潮的重大历史事件。","orderOfOriBank":"14","option":["八•二三","一二•九","五•四","八•一三"]},{"stem":"贯彻实施《廉政准则》，要坚持（　　）相结合，发挥民主党派人民团体人民群众和新闻舆论的监督作用。","orderOfOriBank":"80","option":["批评与自我批评","党内监督与党外监督","教育与惩处",""]},{"stem":"9月15日至28日，第一届全国人民代表大会在北京隆重召开大会通过了《中华人民共和国宪法》，以根本大法的形式，把中国共产党在过渡时期的总路线作为国家在过渡时期的总任务确定下来。","orderOfOriBank":"49","option":["1949年","1952年","1954年","",""]},{"stem":"《廉政准则》规定，不准以不正当手段获取荣誉（）学历学位等利益。","orderOfOriBank":"51","option":["职级","奖金","职称","",""]},{"stem":"《廉政准则》规定，不准用公款支付配偶子女及其配偶以及其他亲属学习（）旅游等费用，为配偶子女及其配偶以及其他亲属出国（境）定居留学探亲等向个人或者机构索取资助。","orderOfOriBank":"85","option":["参观","培训","考察",""]},{"stem":"党员领导干部组织实施和执行《廉政准则》的情况，应列入( )的重要内容，考核结果作为对其任免奖惩的重要依据。","orderOfOriBank":"86","option":["党风廉政建设责任制和干部考核","党风廉政建设责任制","干部考核","干部选拔任用"]},{"stem":"发展经济的根本目的是（）。","orderOfOriBank":"90","option":["提高全国人民的生活水平和质量","提高综合国力　　","发展社会主义的生产力","",""]},{"stem":"《廉政准则》规定，党员领导干部参加（　　）和述职述廉，要对照《廉政准则》进行检查，认真开展批评和自我批评。","orderOfOriBank":"26","option":["民主生活会","作风座谈会","干部考核会",""]},{"stem":"从1956年到1966年，是党领导我国社会主义建设在探索中曲折发展的十年期间，石油工业的发展尤其突出，到（　）已经实现原油的全部自给。","orderOfOriBank":"27","option":["1958年","1962年","1965年","",""]},{"stem":"党员领导干部不准接受（　　）的礼品宴请以及旅游健身娱乐等活动安排。","orderOfOriBank":"92","option":["可能影响公正执行公务","影响公正执行公务","在特定范围内影响公正执行公务","",""]},{"stem":"《廉政准则》规定,党员领导干部不准索取接受或者()占用管理和服务对象以及其他与行使职权有关系的单位或者个人的财物。","orderOfOriBank":"29","option":["以购买为名","以借用为名","以个人名义",""]},{"stem":"2005年3月14日，十届全国人民代表大会第三次会议通过（）。","orderOfOriBank":"31","option":["《反分裂法》","《反国家分裂法》","《反分裂国家法》","",""]}]
    var idFlag = 0;
    for (var j = 0;j<10;j++){
        k = j+1;
        //perPage = $("input[name='radio" + j + "']:checked").val();
        //console.log("perPage:"+perPage);
        $("#title_"+j+"").append("<span>"+k+" "+exam.singleSelection_fronts[j].stem+"</span>");
        for(var x =0;x<4;x++){
            idFlag = idFlag+1;
            $("#"+idFlag+"").append(exam.singleSelection_fronts[j].option[x]);
        }
        //result2 += perPage;
    }
    //return result2;
}




