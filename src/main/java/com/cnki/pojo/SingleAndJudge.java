package com.cnki.pojo;

import questionType.Judgment_front;
import questionType.SingleSelection;
import questionType.SingleSelection_front;

import java.util.List;

public class SingleAndJudge {

    private List<SingleSelection_front> singleSelection_fronts;
    private List<Judgment_front> judgment_fronts;

    public List<SingleSelection_front> getSingleSelection_fronts() {
        return singleSelection_fronts;
    }

    public void setSingleSelection_fronts(List<SingleSelection_front> singleSelection_fronts) {
        this.singleSelection_fronts = singleSelection_fronts;
    }

    public List<Judgment_front> getJudgment_fronts() {
        return judgment_fronts;
    }

    public void setJudgment_fronts(List<Judgment_front> judgment_fronts) {
        this.judgment_fronts = judgment_fronts;
    }

    public SingleAndJudge(List<SingleSelection_front> singleSelection_fronts, List<Judgment_front> judgment_fronts) {
        this.singleSelection_fronts = singleSelection_fronts;
        this.judgment_fronts = judgment_fronts;
    }

    public SingleAndJudge() {
    }
}
