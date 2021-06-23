package com.geccocrawler.gecco.demo.dic.Thes;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

import java.util.List;

public class ThesSectionItem implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280903L;

    @Text
    @HtmlField(cssPath="span.display.EXP")
    private String displayEXP;

    @Text
    @HtmlField(cssPath="span.DEF")
    private String def;

    @Text
    @HtmlField(cssPath="span.EXAMPLE")
    private List<ThesExample> examples;

    public String getDisplayEXP() {
        return displayEXP;
    }

    public void setDisplayEXP(String displayEXP) {
        this.displayEXP = displayEXP;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public List<ThesExample> getExamples() {
        return examples;
    }

    public void setExamples(List<ThesExample> examples) {
        this.examples = examples;
    }
}
