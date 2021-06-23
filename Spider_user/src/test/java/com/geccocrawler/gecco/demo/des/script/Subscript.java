package com.geccocrawler.gecco.demo.des.script;

import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

import java.util.List;

public class Subscript  implements SpiderBean {
    private static final long serialVersionUID = -1000871271002060401L;

//    @Text
//    @HtmlField(cssPath = "h4")
   // private String name;

    @Href
    @HtmlField(cssPath = "ul>li>a[href]")
    private List<String> urls;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
