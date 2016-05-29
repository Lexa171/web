package com.epam.spring.dao.impl;

import com.epam.spring.dao.NewsDao;
import com.epam.spring.model.News;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("newsDao")
public class NewsDaoImpl implements NewsDao {
    public NewsDaoImpl() {
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public NewsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdateNews(News news) {
        sessionFactory.getCurrentSession().saveOrUpdate(news);
    }

    @Override
    public News getNewsById(Integer id) {
        return (News) sessionFactory.getCurrentSession().get(News.class, id);
    }

    @Override
    public List<News> getAllNews() {
        @SuppressWarnings("unchecked")
        List<News> listNews = (List<News>) sessionFactory.getCurrentSession()
                .createCriteria(News.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listNews;
    }

    @Override
    public List<News> getAllNewsByType(String type) {
        @SuppressWarnings("unchecked")
        List<News> listNews = (List<News>) sessionFactory.getCurrentSession()
                .createQuery("SELECT from News where NEWS_TYPE= :type").list();
        return listNews;
    }

    @Override
    public void deleteNews(News news) {
        if (news != null) {

            String hql = "delete from News where NEWS_ID= :id";
            Query q = sessionFactory.getCurrentSession().createQuery(hql);
            q.setInteger("id", news.getId());
            q.executeUpdate();
        }
    }
}
