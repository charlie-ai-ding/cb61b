package com.geccocrawler.gecco.demo.dic.DicLink;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.demo.dic.Collo.ColloBox;
import com.geccocrawler.gecco.demo.dic.Thes.ThesBox;
import com.geccocrawler.gecco.spider.SpiderBean;

public class Tail implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280907L;

    @Text
    @HtmlField(cssPath = "span.ThesBox")
    private ThesBox thesBox;

    @Text
    @HtmlField(cssPath = "span.ColloBox")
    private ColloBox colloBox;


    public ThesBox getThesBox() {
        return thesBox;
    }

    public void setThesBox(ThesBox thesBox) {
        this.thesBox = thesBox;
    }

    public ColloBox getColloBox() {
        return colloBox;
    }

    public void setColloBox(ColloBox colloBox) {
        this.colloBox = colloBox;
    }
}