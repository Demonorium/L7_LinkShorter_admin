package com.demonorium.database;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;
    @Column(length = 4096)
    private String input;


    public UrlEntity() {
    }
    public UrlEntity(String input, String time) {
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

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd - HH:mm:ss");
    public String getTimeFormat() {
        return simpleDateFormat.format(new Date(Long.parseLong(time, 16)));
    }
}
