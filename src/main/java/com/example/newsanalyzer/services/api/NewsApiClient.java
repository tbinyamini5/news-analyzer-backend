package com.example.newsanalyzer.services.api;

import java.util.Map;

import com.example.newsanalyzer.entities.BBCNewsPojo;

public interface NewsApiClient {

    BBCNewsPojo getBBCNews(String from, String to);

    Map<String, Long> countWords(String from, String to);

}
