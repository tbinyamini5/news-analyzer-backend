package com.example.newsanalyzer.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.newsanalyzer.config.properties.NewsApiProperties;
import com.example.newsanalyzer.services.api.NewsApiClient;
import com.example.newsanalyzer.services.impl.NewsApiClientImpl;

@Configuration
public class BeanDefinitions {

    @Bean(name = "newsApiProperties")
    @ConfigurationProperties("merlinx.news.analyzer.news-api")
    public NewsApiProperties newsApiProperties() {
        return new NewsApiProperties();
    }

    @Bean(name = "newsApiClient")
    public NewsApiClient newsApiClient(@Qualifier("newsApiProperties") NewsApiProperties newsApiProperties) {
        return new NewsApiClientImpl(newsApiProperties);
    }

}
