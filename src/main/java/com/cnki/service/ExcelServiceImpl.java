package com.cnki.service;


import answerSheetProductor.JudgmentQuestionProductor;
import answerSheetProductor.QuesionBankProdector;
import answerSheetProductor.SingleSelectionQuestionProductor;
import answeredQuesionType.SingleSelection_answered;
import autoMarking.AutoMarking;
import com.cnki.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import myexcel.Back2Front;
import org.springframework.stereotype.Service;
import questionType.Judgment;
import questionType.SingleSelection;
import questionType.Judgment_front;
import questionType.SingleSelection_front;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void database() throws Exception {
        QuesionBankProdector.quesionBankProdect();
    }

    public List<SingleSelection_front> getSingle() throws Exception {
        System.out.println("Impl");
        List<SingleSelection> singleSelectionQuestionList = SingleSelectionQuestionProductor.singleSelectionQuestionProduct(20);
        List<SingleSelection_front> result_select = Back2Front.back2Front_select(singleSelectionQuestionList);
        return result_select;
    }

    @Override
    public List<Judgment_front> getJudge() throws Exception {
        List<Judgment> judgmentQuestionList = JudgmentQuestionProductor.judgmentQuestionProduct(20);
        List<Judgment_front> result_judg = Back2Front.back2Front_judg(judgmentQuestionList);

        return result_judg;
    }

    @Override
    public List<String> getScore(List<SingleSelection_answered> singleSelection_answereds) throws Exception {
        //1.接收答卷
        System.out.println("接收答卷！");
        //2.对比excel表，得出正确or错误
        AutoMarking autoMarking = new AutoMarking();
        List<String> afterJudge =
                autoMarking.autoMarkingByOneList(singleSelection_answereds,3,2);
        //3.将生成的list返回给前端
        return afterJudge;
    }
}
