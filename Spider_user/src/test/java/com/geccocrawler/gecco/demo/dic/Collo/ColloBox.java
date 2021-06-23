package com.geccocrawler.gecco.demo.dic.Collo;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

import java.util.List;

/**
 * will be use "span.ColloBox"
 */
public class ColloBox implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280905L;

    @Text
    @HtmlField(cssPath = "span.Section")
    private List<ColloSection> colloSectionList;


    public List<ColloSection> getColloSectionList() {
        return colloSectionList;
    }

    public void setColloSectionList(List<ColloSection> colloSectionList) {
        this.colloSectionList = colloSectionList;
    }
}
