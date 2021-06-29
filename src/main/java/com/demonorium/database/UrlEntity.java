package com.demonorium.database;

import javax.persistence.*;

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
}
