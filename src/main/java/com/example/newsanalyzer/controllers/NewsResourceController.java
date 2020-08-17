package com.example.newsanalyzer.controllers;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.newsanalyzer.entities.BBCNewsPojo;
import com.example.newsanalyzer.entities.DatesRange;
import com.example.newsanalyzer.services.api.NewsApiClient;

@RestController
@CrossOrigin(origins = "*")
public class NewsResourceController {

    @Resource(name="newsApiClient")
    private NewsApiClient newsApiClient;


    @GetMapping("/bbc-news")
    public BBCNewsPojo getBBCNews(@RequestParam String from, @RequestParam String to) {
        return newsApiClient.getBBCNews(from, to);
    }

    @PostMapping("/bbc-news/count-words")
    public Map<String, Long> countWords(@RequestBody DatesRange datesRange) {
        return newsApiClient.countWords(datesRange.getFrom(), datesRange.getTo());
    }
}
