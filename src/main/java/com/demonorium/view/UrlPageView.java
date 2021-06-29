package com.demonorium.view;

public class UrlPageView {
    private String url;
    private Boolean badUrl = false;
    private String shorten;
    private boolean correct;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getBadUrl() {
        return badUrl;
    }

    public void setBadUrl(Boolean badUrl) {
        this.badUrl = badUrl;
    }

    public String getShorten() {
        return shorten;
    }

    public void setShorten(String shorten) {
        this.shorten = shorten;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
