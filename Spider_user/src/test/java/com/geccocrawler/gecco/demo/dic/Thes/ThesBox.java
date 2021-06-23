package com.geccocrawler.gecco.demo.dic.Thes;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

import java.util.List;

public class ThesBox implements SpiderBean {
    private static final long serialVersionUID = -1000871271002280901L;



    @Text
    @HtmlField(cssPath="span.Section")
    private List<ThesSection> thesSections;

    public List<ThesSection> getThesSections() {
        return thesSections;
    }

    public void setThesSections(List<ThesSection> thesSections) {
        this.thesSections = thesSections;
    }
}
