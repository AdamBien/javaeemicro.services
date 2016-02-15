package com.airhacks.screencasts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = Video.all, query = "SELECT v FROM Video v")
public class Video {

    public static final String all = "Video.all";

    @Id
    @GeneratedValue
    private long id;

    private String name;

    Video() {
    }

    public Video(String name) {
        this.name = name;
    }

}
