package com.geccocrawler.gecco.demo.dic.Collo;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

public class ColloExample implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280804L;

    @Text
    @HtmlField(cssPath="span.EXAMPLE")
    private String example;

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
