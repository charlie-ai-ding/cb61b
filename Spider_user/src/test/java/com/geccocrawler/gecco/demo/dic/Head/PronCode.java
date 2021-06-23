package com.geccocrawler.gecco.demo.dic.Head;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

public class PronCode  implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280815L;

    @Text
    @HtmlField(cssPath = "span.PRON")
    private String pron;

    @Text
    @HtmlField(cssPath = "span.AMEVARPRON")
    private  String amevarPron;

    public String getPron() {
        return pron;
    }

    public void setPron(String pron) {
        this.pron = pron;
    }

    public String getAmevarPron() {
        return amevarPron;
    }

    public void setAmevarPron(String amevarPron) {
        this.amevarPron = amevarPron;
    }

    @Override
    public String toString() {
        return "[" +
                "pron='" + pron + '\'' +
                ", amevarPron='" + amevarPron + '\'' +
                ']';
    }
}
