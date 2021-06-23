package com.geccocrawler.gecco.demo.dic.Sense;

import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

import java.util.List;

/**
 *  in "span.Sense"
 */
public class DefContent  implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280917L;

    @Attr("id")
    @HtmlField(cssPath="span.Sense")
    private String id;

    @Text
    @HtmlField(cssPath = " span.SIGNPOST")
    private String signpost;

    @Text
    @HtmlField(cssPath = "span.GRAM span.neutral.span")
    private String gram;

    /**
     *
     */
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

    /**
     *
     */
    @Text
    @HtmlField(cssPath = "span.Subsense")
    private List<SubSense> subSenseList;


    @Href(click=false)
    @HtmlField(cssPath="span.Thesref")
    private String thesRef;


    @Href(click=false)
    @HtmlField(cssPath="span.Crossref")
    private Crossref crossRef;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSignpost() {
        return signpost;
    }

    public void setSignpost(String signpost) {
        this.signpost = signpost;
    }

    public String getGram() {
        return gram;
    }

    public void setGram(String gram) {
        this.gram = gram;
    }

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

    public List<SubSense> getSubSenseList() {
        return subSenseList;
    }

    public void setSubSenseList(List<SubSense> subSenseList) {
        this.subSenseList = subSenseList;
    }

    public String getThesRef() {
        return thesRef;
    }

    public void setThesRef(String thesRef) {
        this.thesRef = thesRef;
    }

    public Crossref getCrossRef() {
        return crossRef;
    }

    public void setCrossRef(Crossref crossRef) {
        this.crossRef = crossRef;
    }
}
