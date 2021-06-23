package com.geccocrawler.gecco.demo.dic.Sense;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

import java.util.List;

public class SubSense  implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280997L;

    @Text
    @HtmlField(cssPath = "span.DEF")
    private String def;

    /**
     *
     */
    @Text
    @HtmlField(cssPath = "span.EXAMPLE")
    private List<GramExample> gramExampleList;

    /**
     *
     */
    @Text
    @HtmlField(cssPath = "span.GramExa")
    private List<GramExa> gramExaList;

    /**
     *
     */
    @Text
    @HtmlField(cssPath = "span.ColloExa")
    private List<ColloExa> colloExaList;

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public List<GramExample> getGramExampleList() {
        return gramExampleList;
    }

    public void setGramExampleList(List<GramExample> gramExampleList) {
        this.gramExampleList = gramExampleList;
    }

    public List<GramExa> getGramExaList() {
        return gramExaList;
    }

    public void setGramExaList(List<GramExa> gramExaList) {
        this.gramExaList = gramExaList;
    }

    public List<ColloExa> getColloExaList() {
        return colloExaList;
    }

    public void setColloExaList(List<ColloExa> colloExaList) {
        this.colloExaList = colloExaList;
    }
}
