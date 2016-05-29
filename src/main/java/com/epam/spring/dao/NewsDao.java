package com.epam.spring.dao;


import com.epam.spring.model.News;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 16.05.2016.
 */
public interface NewsDao {
    void saveOrUpdateNews(News news);

    News getNewsById(Integer id);

    List<News> getAllNews();

    List<News> getAllNewsByType(String type);

    void deleteNews(News news);

}
