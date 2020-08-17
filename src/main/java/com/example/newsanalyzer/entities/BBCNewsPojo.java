package com.example.newsanalyzer.entities;

import java.util.List;

public class BBCNewsPojo {

    private String status;
    private int totalResults;
    private List<ArticlePojo> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticlePojo> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlePojo> articles) {
        this.articles = articles;
    }
}
