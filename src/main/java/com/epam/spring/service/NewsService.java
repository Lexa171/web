package com.epam.spring.service;


import com.epam.spring.model.News;

import java.util.List;


public interface NewsService {
    void saveOrUpdateNews(News news);

    News getNewsById(Integer id);

    List<News> getAllNews();

    List<News> getAllNewsByType(String type);

    void deleteNews(News news);
}
