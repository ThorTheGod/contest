package com.cnki.service;


import answeredQuesionType.SingleSelection_answered;
import com.fasterxml.jackson.core.JsonProcessingException;
import questionType.Judgment;
import questionType.Judgment_front;
import questionType.SingleSelection;
import questionType.SingleSelection_front;

import java.util.List;

public interface ExcelService {

    void database() throws Exception;


    List<SingleSelection_front> getSingle() throws Exception;

    List<Judgment_front> getJudge( ) throws Exception;

    List<String> getScore(List<SingleSelection_answered> singleSelection_answereds) throws Exception;

}
