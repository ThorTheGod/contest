
$(function(){
    var i = 0;
    var perPage = "";
    var ques1={"singleSelection_fronts":[{"stem":"发展经济的根本目的是（）。","orderOfOriBank":"64","option":["提高全国人民的生活水平和质量","提高综合国力　　","发展社会主义的生产力",""]},{"stem":"1949年5月，中国人民解放军第三野战军第十兵团及第二野战军一部先后进军福建，在福建地方党组织及其领导的革命武装的配合下，至（）年5月12日解放了全省。","orderOfOriBank":"36","option":["1949","1950","1951","1952",""]},{"stem":"党员领导干部组织实施和执行《廉政准则》的情况，应列入( )的重要内容，考核结果作为对其任免奖惩的重要依据。","orderOfOriBank":"37","option":["党风廉政建设责任制和干部考核","党风廉政建设责任制","干部考核","干部选拔任用"]},{"stem":"《廉政准则》规定，不准在社会保障（）救灾救济款物分配等事项中优亲厚友显失公平。","orderOfOriBank":"70","option":["资金补助","政策倾斜","政策扶持","",""]},{"stem":"中国共产党党徽为（）和组成的图案。","orderOfOriBank":"71","option":["镰刀锤头","镰刀斧头","麦穗和齿轮","",""]},{"stem":"领导班子主要负责人对开好民主生活会负责，并承担制定和落实领导班子整改措施的( )。","orderOfOriBank":"8","option":["全面责任","主要责任","领导责任","直接责任",""]},{"stem":"《党纪处分条例》规定，对党员的纪律处分有( )种。","orderOfOriBank":"40","option":["4","5","6","7"]},{"stem":"深入贯彻落实科学发展观，要求我们继续深化（　）","orderOfOriBank":"10","option":["改革开放","经济结构调整　　","对外开放",""]},{"stem":"中国人民民主革命取得伟大胜利的重要标志是（）。","orderOfOriBank":"44","option":["三大战役的胜利　　","解放军占领南京　","中国人民政治协商会议的召开","",""]},{"stem":"党风廉政建设责任制规定，单独受到责令辞职免职处理的领导干部，（）内不得重新担任与其原任职务相当的领导职务；受到降职处理的，（）内不得提升职务。","orderOfOriBank":"80","option":["半年，一年","一年，二年","二年，三年","一年，一年半"]}]};

    var idFlag = 0;
    for (var j = 0;j<10;j++){
        for(var x =0;x<4;x++){
            idFlag = Number(idFlag)+Number(1);
            $("#"+idFlag+"").append(ques1.singleSelection_fronts[j].option[x]);
            console.log("id="+idFlag+":加载数据");
        }
    }
    //点击提交时遍历并赋值
    $("#btnSave").click(function() {
        //radio
        for (var i = 1; i <= 10; i++) {
            var flag = $("input[name='radio" + i + "']:checked").val();
            //alert(flag);
            // if (flag == null) {
            //     alert("您还有未填项，无法提交!");
            //     return false;
            // }
            console.log(flag);
            //字符串拼接
            perPage = perPage+flag+",";
        }
        console.log(perPage);
    });

    //对每一页操作
    // $('input[name="radio"]').each(function(){
    //     perPage = this.value+perPage;
    // });
    // alert(perPage);
    // for (; i<4;i++) {
    //     //点击下一页时
    //     $("#next").click(function (){
    //         //拿到数据
    //         for (let j = 0; j < 10; j++) {
    //             var x = ""+j;
    //             perPage = $("input:radio:checked").val();
    //         }
    //         //拿到所有radio里的值
    //            for (let j = 0; j < 10; j++) {
    //         var x = ""+j;
    //         perPage = $("input:radio:checked").val();
    //     }
    //
    //         //清空数据
    //         $("#demo").empty();
    //         //填充数据
    //         for(i=0;i<10;i++){
    //             // txt0 = ques1.singleSelection_fronts[i].stem;
    //             // txt1= ques1.singleSelection_fronts[i].option[0];
    //             // txt2= ques1.singleSelection_fronts[i].option[1];
    //             // txt3= ques1.singleSelection_fronts[i].option[2];
    //             // txt4= ques1.singleSelection_fronts[i].option[3];
    //             j=i+1;
    //             i=""+i;
    //             id = "answer"+i;
    //             txt = txt+j+" "+txt0+ "<br name='testradio'><input type='radio' id='radio1"+"' value='A'>"+"A"+" "+txt1+ "<br><input type='radio' id='radio_"+i+"' value='B'>"+"B"+" "+txt2+ "<br><input type='radio' id='radio_"+i+"' value='C'>"+"C"+" "+txt3+ "<br><input type='radio' id='radio_"+i+"' value='D'>"+"D"+" "+txt4+ "<br>";
    //         }
    //
    //     })
    //
    //
    //
    //
    //
    //
    //
    //     if(i==3){
    //         i = 0;
    //     }
    // }
    //
    //
    //

})