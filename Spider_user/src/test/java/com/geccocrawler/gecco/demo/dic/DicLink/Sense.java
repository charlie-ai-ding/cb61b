package com.geccocrawler.gecco.demo.dic.DicLink;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.demo.dic.Sense.Crossref;
import com.geccocrawler.gecco.demo.dic.Sense.DefContent;
import com.geccocrawler.gecco.spider.SpiderBean;

public class Sense implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280907L;

    @Text
    @HtmlField(cssPath = "span.Sense")
    private DefContent sense;

    public DefContent getSense() {
        return sense;
    }

    public void setSense(DefContent sense) {
        this.sense = sense;
    }
}