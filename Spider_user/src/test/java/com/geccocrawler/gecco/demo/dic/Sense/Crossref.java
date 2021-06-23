package com.geccocrawler.gecco.demo.dic.Sense;

import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

public class Crossref  implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280927L;

    @Href(click=false)
    @HtmlField(cssPath="a.crossRef")
    private String crossRef;

    @Text
    @HtmlField(cssPath = "span.REFHWD")
    private String crossValue;

}