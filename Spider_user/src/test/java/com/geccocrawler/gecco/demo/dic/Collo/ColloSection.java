package com.geccocrawler.gecco.demo.dic.Collo;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

import java.util.List;

public class ColloSection implements SpiderBean {

    private static final long serialVersionUID = -1000871271092760024L;

    @Text
    @HtmlField(cssPath="span.SECHEADING")
    private String sectionName;

    @Text
    @HtmlField(cssPath="span.Collocate")
    private List<CollocateItem> collocates;



    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<CollocateItem> getCollocates() {
        return collocates;
    }

    public void setCollocates(List<CollocateItem> collocates) {
        this.collocates = collocates;
    }

}