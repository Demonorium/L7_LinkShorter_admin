package com.demonorium;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class UrlInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String shorten;
    String input;


    public UrlInfo() {
    }
    public UrlInfo(String input) {
        this.input = input;
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

    public String getShorten() {
        return shorten;
    }

    public void setShorten(String shorten) {
        this.shorten = shorten;
    }
}
