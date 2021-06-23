package com.geccocrawler.gecco.demo.dic.Thes;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

import java.util.List;

public class ThesSection  implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280902L;


    @Text
    @HtmlField(cssPath="span.Exponent")
    private List<ThesSectionItem> sectionItems;

    public List<ThesSectionItem> getSectionItems() {
        return sectionItems;
    }

    public void setSectionItems(List<ThesSectionItem> sectionItems) {
        this.sectionItems = sectionItems;
    }


}
