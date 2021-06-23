package com.geccocrawler.gecco.demo.dic.Collo;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

public class CollocateItem implements HtmlBean {

    private static final long serialVersionUID = -1000871271092768001L;

    @Text
    @HtmlField(cssPath="span.collo.COLLOC")
    private String collocate;

    @Text
    @HtmlField(cssPath="span.neutral.span")
    private String collGloss;

    @Text
    @HtmlField(cssPath="span.EXAMPLE")
    private List<ColloExample> colloExampleList;


    public String getCollGloss() {
        return collGloss;
    }

    public void setCollGloss(String collGloss) {
        this.collGloss = collGloss;
    }

    public List<ColloExample> getColloExampleList() {
        return colloExampleList;
    }

    public void setColloExampleList(List<ColloExample> colloExampleList) {
        this.colloExampleList = colloExampleList;
    }

    public String getCollocate() {
        return collocate;
    }

    public void setCollocate(String collocate) {
        this.collocate = collocate;
    }
}
