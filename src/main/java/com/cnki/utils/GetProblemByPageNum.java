package com.cnki.utils;

import com.cnki.pojo.SingleAndJudge;
import questionType.Judgment_front;
import questionType.SingleSelection_front;

import java.util.ArrayList;
import java.util.List;

public class GetProblemByPageNum {

//{"singleSelection_fronts":
//        [{"stem":"发展经济的根本目的是（）。","orderOfOriBank":"64","option":["提高全国人民的生活水平和质量","提高综合国力　　","发展社会主义的生产力",""]}
//        ,{"stem":"1949年5月，中国人民解放军第三野战军第十兵团及第二野战军一部先后进军福建，在福建地方党组织及其领导的革命武装的配合下，至（）年5月12日解放了全省。","orderOfOriBank":"36","option":["1949","1950","1951","1952",""]}
//        ,{"stem":"党员领导干部组织实施和执行《廉政准则》的情况，应列入( )的重要内容，考核结果作为对其任免奖惩的重要依据。","orderOfOriBank":"37","option":["党风廉政建设责任制和干部考核","党风廉政建设责任制","干部考核","干部选拔任用"]}
//        ,{"stem":"《廉政准则》规定，不准在社会保障（）救灾救济款物分配等事项中优亲厚友显失公平。","orderOfOriBank":"70","option":["资金补助","政策倾斜","政策扶持","",""]}
//        ,{"stem":"中国共产党党徽为（）和组成的图案。","orderOfOriBank":"71","option":["镰刀锤头","镰刀斧头","麦穗和齿轮","",""]}
//        ,{"stem":"领导班子主要负责人对开好民主生活会负责，并承担制定和落实领导班子整改措施的( )。","orderOfOriBank":"8","option":["全面责任","主要责任","领导责任","直接责任",""]}
//        ,{"stem":"《党纪处分条例》规定，对党员的纪律处分有( )种。","orderOfOriBank":"40","option":["4","5","6","7"]}
//        ,{"stem":"深入贯彻落实科学发展观，要求我们继续深化（　）","orderOfOriBank":"10","option":["改革开放","经济结构调整　　","对外开放",""]}
//        ,{"stem":"中国人民民主革命取得伟大胜利的重要标志是（）。","orderOfOriBank":"44","option":["三大战役的胜利　　","解放军占领南京　","中国人民政治协商会议的召开","",""]}
//        ,{"stem":"党风廉政建设责任制规定，单独受到责令辞职免职处理的领导干部，（）内不得重新担任与其原任职务相当的领导职务；受到降职处理的，（）内不得提升职务。","orderOfOriBank":"80","option":["半年，一年","一年，二年","二年，三年","一年，一年半"]}
//        ,{"stem":"禁止脱离实际,弄虚作假,损害群众利益和党群干群关系。不准搞劳民伤财的“工程”和沽名钓誉的“工程”。()","orderOfOriBank":"49","option":["政绩形象","形象政绩","民生形象","",""]}
//        ,{"stem":"党的各级领导班子主要负责人应当带头执行()，支持领导班子成员在职责范围内独立负责地开展工作。","orderOfOriBank":"50","option":["下级服从上级的原则","民主集中制","集体讨论的原则","",""]}
//        ,{"stem":"《廉政准则》规定，不准允许纵容配偶子女及其配偶在（）工商注册登记后，到本人管辖的地区和业务范围内从事可能与公共利益发生冲突的经商办企业活动。","orderOfOriBank":"84","option":["异地","本地","国外",""]}
//        ,{"stem":"党员领导干部应当向党组织如实报告个人( )，自觉接受监督。","orderOfOriBank":"23","option":["主要事项","重要事项","必要事项","有关事项"]}
//        ,{"stem":"中共十七届四中全会《决定》强调指出，加强和改进行党的作风建设的核心问题是（　）","orderOfOriBank":"88","option":["开展批评和自我批评　","保持党同人民群众的血肉联系","立党为公执政为民","",""]}
//        ,{"stem":"《论十大关系》的讨论相联系，1956年4月的政治局扩大会议上还提出在文化工作中实行（　）的方针。","orderOfOriBank":"57","option":["百花齐放，百家争鸣","为人民服务，为社会主义服务","贴近生活贴近实际贴近群众","",""]}
//        ,{"stem":"设常委会的基层党组织的党委常委会纪委常委会分别向委员会全体会议每年报告工作( )次。","orderOfOriBank":"26","option":["1","2","3","4"]}
//        ,{"stem":"《廉政准则》规定,党员领导干部不准违反规定拥有()的股份或者证券。","orderOfOriBank":"27","option":["上市公司(企业)","非上市公司(企业)","境外公司(企业)","",""]}
//        ,{"stem":"党的各级组织和党员领导干部，应当自觉接受并正确对待党和( )的监督。","orderOfOriBank":"92","option":["新闻媒体","人民群众","民主党派","人大",""]}
//        ,{"stem":"党员干部特别是高中级干部要带头学习和实践“三个代表”重要思想，成为勤奋学习，（）的模范，勇于实践锐意创新的模范。","orderOfOriBank":"31","option":["善于思考","善于组织","善于宣传","",""]}]
//,"judgment_fronts":
//    [{"stem":"《廉政准则》适用于党的机关人大机关行政机关政协机关审判机关检察机关中科级以上党员领导干部。 ","orderOfOriBank":"64"}
//    ,{"stem":"“利用知悉或者掌握的内幕信息谋取利益”，属于禁止违反公共财物管理和使用的规定，假公济私化公为私所不准有的行为。 ","orderOfOriBank":"96"}
//    ,{"stem":"“一国两制”的构思，最初是在解决香港问题时提出的。 ","orderOfOriBank":"2"}
//    ,{"stem":"对预备党员违犯党纪的，可以因为还不是正式党员所以不受党纪约束。 ","orderOfOriBank":"98"}
//    ,{"stem":"标志着中国进入改革开放新时期的重要会议是中共八大 ","orderOfOriBank":"99"}
//    ,{"stem":"国有和国有控股企业（含国有和国有控股金融企业）及其分支机构领导人员中的党员；县（市区旗）直属机关审判机关检察机关的科级党员负责人，乡镇（街道）党员负责人，基层站所的党员负责人参照执行《廉政准则》。 ","orderOfOriBank":"5"}
//    ,{"stem":"长征中红十五军是最先到达陕北的红军部队。 ","orderOfOriBank":"38"}
//    ,{"stem":"从1956年到1966年，是党领导我国社会主义建设在探索中曲折发展的十年期间，石油工业的发展尤其突出，到1958年已经实现原油的全部自给。 ","orderOfOriBank":"7"}
//    ,{"stem":"社会保障体系中最基本的内容是社会救助。 ","orderOfOriBank":"39"}
//    ,{"stem":"党员领导干部组织实施和执行《廉政准则》的情况，应列入党风廉政建设责任制和干部考核的重要内容，考核结果作为对其任免奖惩的重要依据。 ","orderOfOriBank":"72"}
//    ,{"stem":"《廉政准则》规定，不准在干部考察工作中隐瞒或者歪曲事实真相。 ","orderOfOriBank":"10"}
//    ,{"stem":"党的基层组织设立的委员会委员候选人的差额为应选人数的0.05。 ","orderOfOriBank":"11"}
//    ,{"stem":"中共十五大提出，面向新世纪，全党要继续推进“新的伟大工程”是现代化建设。 ","orderOfOriBank":"44"}
//    ,{"stem":"邓小平理论博大精深，是中国改革开放建设现代化的重要保证，解放思想，实事求是是邓小平理论的基石。 ","orderOfOriBank":"76"}
//    ,{"stem":"中国彻底的民主革命纲领是中共一大提出来的。 ","orderOfOriBank":"13"}
//    ,{"stem":"毛泽东思想的“活的灵魂”是理论联系实际，密切联系群众，批评和自我批评　　。 ","orderOfOriBank":"50"}
//    ,{"stem":"党风廉政建设责任制规定，错误决策由领导干部个人决定或者批准的，追究该领导干部个人的责任。 ","orderOfOriBank":"21"}
//    ,{"stem":"新修订的《关于实行党风廉政建设责任制的规定》于40492颁布实施。 ","orderOfOriBank":"91"}
//    ,{"stem":"《廉政准则》规定，不准为配偶子女及其配偶以及其他亲属经商办企业提供便利条件，或者朋友之间利用职权相互为对方配偶子女及其配偶以及其他亲属经商办企业提供便利条件。 ","orderOfOriBank":"92"}
//    ,{"stem":"《廉政准则》规定,党员领导干部不准非法占有公共财物,或者以征用等方式非法占有公共财物。 ","orderOfOriBank":"61"}]
//    }


    public static SingleAndJudge getProblems(SingleAndJudge singleAndJudge,int id){
        SingleAndJudge singleAndJudge1 = new SingleAndJudge();
        if(id<=1&&id>=0){
            //取出试卷中的20道单选
            List<SingleSelection_front>singleSelection_fronts =
                    singleAndJudge.getSingleSelection_fronts();
            List<SingleSelection_front> single = new ArrayList<>();
            //提取单选中的10道题
            for (int i = id * 10; i < 10+id*10; i++) {
                single.add(singleSelection_fronts.get(i));
            }
            //将抽出的10道单选放入空的返回集中
            singleAndJudge1.setSingleSelection_fronts(single);
        }
        else if(id>=2&id<=3){
            //取出试卷中的20道判断
            List<Judgment_front> judgment_fronts =
                    singleAndJudge.getJudgment_fronts();
            List<Judgment_front> judge = new ArrayList<>();
            //取出判断中的10道题
            for (int i = (id-2)*10; i < 10+(id-2)*10; i++) {
                judge.add(judgment_fronts.get(i));
            }
            //将抽出的10道判断放入空的返回集
            singleAndJudge1.setJudgment_fronts(judge);
        }else
            return null;
        return singleAndJudge1;
    }
}
