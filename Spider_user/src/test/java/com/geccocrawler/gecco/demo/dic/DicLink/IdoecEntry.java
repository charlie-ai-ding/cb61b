package com.geccocrawler.gecco.demo.dic.DicLink;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class IdoecEntry implements SpiderBean {

    private static final long serialVersionUID = -1000871271002280904L;

    @Text
    @HtmlField(cssPath = "span.topics_container")
    private TopicContainer topicContainer;

    @Text
    @HtmlField(cssPath = "span.frequent.Head")
    private Head head;

    @Text
    @HtmlField(cssPath = "span.Sense")
    private List<Sense> senseList;

    @Text
    @HtmlField(cssPath = "span.Tail")
    private Tail tail;


    @Text
    @HtmlField(cssPath = "span.PhrVbEntry")
    private List<PhrVbEntrys> phrVbEntrysList;





    public Tail getTail() {
        return tail;
    }

    public void setTail(Tail tail) {
        this.tail = tail;
    }

    public TopicContainer getTopicContainer() {
        return topicContainer;
    }

    public void setTopicContainer(TopicContainer topicContainer) {
        this.topicContainer = topicContainer;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public List<Sense> getSenseList() {
        return senseList;
    }

    public void setSenseList(List<Sense> senseList) {
        this.senseList = senseList;
    }

    public List<PhrVbEntrys> getPhrVbEntrysList() {
        return phrVbEntrysList;
    }

    public void setPhrVbEntrysList(List<PhrVbEntrys> phrVbEntrysList) {
        this.phrVbEntrysList = phrVbEntrysList;
    }
}
