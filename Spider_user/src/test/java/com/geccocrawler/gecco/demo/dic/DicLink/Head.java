package com.geccocrawler.gecco.demo.dic.DicLink;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.demo.dic.Head.PronCode;
import com.geccocrawler.gecco.spider.SpiderBean;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Head implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280905L;

    @Text
    @HtmlField(cssPath = "span.HYPHENATION")
    private String hyphenAction;


    @Text
    @HtmlField(cssPath = "span.PronCodes")
    private PronCode pronCodes;

    @Text
    @HtmlField(cssPath = "span.FREQ")
    private List<String> level;

    @Text
    @HtmlField(cssPath = "span.POS")
    private String pos;

    @Text
    @HtmlField(cssPath = "span.GRAM span.neutral.span")
    private String gram;



    public String getHyphenAction() {
        return hyphenAction;
    }

    public void setHyphenAction(String hyphenAction) {
        this.hyphenAction = hyphenAction;
    }

    public PronCode getPronCodes() {
        return pronCodes;
    }

    public void setPronCodes(PronCode pronCodes) {
        this.pronCodes = pronCodes;
    }

    public List<String> getLevel() {
        return level;
    }

    public void setLevel(List<String> level) {
        this.level = level;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getGram() {
        return gram;
    }

    public void setGram(String gram) {
        this.gram = gram;
    }
}
