package com.airhacks.workshops.entity;

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
@NamedQuery(name = Workshop.all, query = "SELECT w FROM Workshop w")
public class Workshop {

    public static final String all = "Workshop.all";

    @Id
    @GeneratedValue
    private long id;

    private String name;

    Workshop() {
    }

    public Workshop(String name) {
        this.name = name;
    }

}
