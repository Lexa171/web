package com.epam.spring.service.impl;

import com.epam.spring.dao.NewsDao;
import com.epam.spring.model.News;
import com.epam.spring.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("newsService")
@Transactional
public class NewsServiceImpl implements NewsService {
    public NewsServiceImpl() {
    }

    @Autowired
    private NewsDao newsDao;

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public void saveOrUpdateNews(News news) {
        newsDao.saveOrUpdateNews(news);
    }

    @Override
    public News getNewsById(Integer id) {
        return newsDao.getNewsById(id);
    }

    @Override
    public List<News> getAllNews() {
        return newsDao.getAllNews();
    }

    @Override
    public List<News> getAllNewsByType(String type) {
        return newsDao.getAllNewsByType(type);
    }

    @Override
    public void deleteNews(News news) {
        newsDao.deleteNews(news);
    }
}
