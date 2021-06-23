package com.geccocrawler.gecco.demo.dic.Sense;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

public class GramExa implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280515L;

    @Text
    @HtmlField(cssPath="span.PROPFORM")
    private String propform;

    @Text
    @HtmlField(cssPath="span.EXAMPLE")
    private String example;

    public String getPropform() {
        return propform;
    }

    public void setPropform(String propform) {
        this.propform = propform;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
