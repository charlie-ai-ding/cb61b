package com.geccocrawler.gecco.demo.dic.Sense;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

public class ColloExa implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280212L;

    @Text
    @HtmlField(cssPath="span.COLLO")
    private String collo;

    @Text
    @HtmlField(cssPath="span.EXAMPLE")
    private String example;

    public String getCollo() {
        return collo;
    }

    public void setCollo(String collo) {
        this.collo = collo;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
