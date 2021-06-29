package com.demonorium;

import javax.persistence.*;

@Entity
public class UrlInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String time;
    @Column(length = 4096)
    String input;


    public UrlInfo() {
    }
    public UrlInfo(String input, String time) {
        this.input = input;
        this.time = time;
    }
    public Long getId() {
        return id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setId(Long id) { ;
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
