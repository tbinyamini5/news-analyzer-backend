package com.example.newsanalyzer.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.client.RestTemplate;

import com.example.newsanalyzer.config.properties.NewsApiProperties;
import com.example.newsanalyzer.entities.ArticlePojo;
import com.example.newsanalyzer.entities.BBCNewsPojo;
import com.example.newsanalyzer.services.api.NewsApiClient;

public class NewsApiClientImpl implements NewsApiClient {

    private final NewsApiProperties newsApiProperties;

    public NewsApiClientImpl(NewsApiProperties newsApiProperties) {
        this.newsApiProperties = newsApiProperties;
    }

    @Override
    public BBCNewsPojo getBBCNews(String from, String to) {
        final String uri = "http://newsapi.org/v2/everything?language=en&pageSize=100&sortBy=publishedAt&sources=bbc-news&from=" + from + "&to=" + to + "&apiKey=" + newsApiProperties.getPrivateApiKey();

        RestTemplate restTemplate = new RestTemplate();
        BBCNewsPojo result = restTemplate.getForObject(uri, BBCNewsPojo.class);

        System.out.println(result);
        return result;
    }

    @Override
    public Map<String, Long> countWords(String from, String to) {
        final String uri = "http://newsapi.org/v2/everything?language=en&pageSize=100&sortBy=publishedAt&sources=bbc-news&from=" + from + "&to=" + to + "&apiKey=" + newsApiProperties.getPrivateApiKey();

        RestTemplate restTemplate = new RestTemplate();
        BBCNewsPojo result = restTemplate.getForObject(uri, BBCNewsPojo.class);

        List<ArticlePojo> articles = result.getArticles();
        return getWordToOccurrencesMap(articles);
    }

    private Map<String, Long> getWordToOccurrencesMap(List<ArticlePojo> articles) {
        Stream<List<String>> titlesListStream = articles.stream()
                .map(articlePojo -> Arrays.asList(articlePojo.getTitle().toLowerCase().split(" ")));
        Stream<List<String>> descriptionsListStream = articles.stream()
                .map(articlePojo -> Arrays.asList(articlePojo.getDescription().toLowerCase().split(" ")));

        return Stream.concat(titlesListStream, descriptionsListStream)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }
}
