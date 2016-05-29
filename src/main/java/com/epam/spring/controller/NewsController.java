package com.epam.spring.controller;

import java.util.List;

import javax.validation.Valid;

import com.epam.spring.common.MessageProperties;
import com.epam.spring.dto.NewsDto;
import com.epam.spring.exception.CustomGenericException;
import com.epam.spring.model.News;
import com.epam.spring.model.User;
import com.epam.spring.service.NewsService;
import com.epam.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequestMapping(value = "/news")
public class NewsController extends GlobalExceptionController {
    public NewsController() {
    }

    @Autowired
    private MessageProperties messageProperties;

    public void setMessageProperties(MessageProperties messageProperties) {
        this.messageProperties = messageProperties;
    }

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getNewsList(Model model) throws Exception {
        List<News> newslist = newsService.getAllNews();
        model.addAttribute("newslist",newslist);
        return "newslist";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getNews(@PathVariable("id") Integer id, Model model) throws Exception {

        News news = newsService.getNewsById(id);
        if (news != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.getUserByName(auth.getName());
            model.addAttribute("news", news);
            model.addAttribute("user", user);
            return "newspage";
        } else {
            throw new CustomGenericException(messageProperties.getMessage("error.code.News.GetNews"),
                    messageProperties.getMessage("error.News.LinkIsNull"));
        }
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String addNews(Model model) {
        NewsDto newsDto = new NewsDto();
        model.addAttribute("news", newsDto);
        return "addnews";
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public String saveNews(@ModelAttribute("news") @Valid NewsDto newsDto, BindingResult bindingResult, Model model)
            throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            return "addnews";
        }
        News news = new News(newsDto);
        newsService.saveOrUpdateNews(news);
        model.addAttribute("news", news);
        return "redirect:/news/" + news.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String editNews(@PathVariable("id") Integer id, Model model) {
        News news = newsService.getNewsById(id);
        if (news == null) {
            throw new CustomGenericException(messageProperties.getMessage("error.code.News.EditNews"),
                    messageProperties.getMessage("error.News.LinkIsNull"));
        }
        NewsDto newsDto = new NewsDto(news);
        model.addAttribute("news", newsDto);
        return "addnews";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateNews(@PathVariable("id") Integer id, @ModelAttribute("news") @Valid NewsDto newsDto,
                             BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            return "addnews";
        }
        News news = newsService.getNewsById(id);
        News newNews = new News(newsDto);
        news.setName(newNews.getName());
        news.setAuthor(newNews.getAuthor());
        news.setData(newNews.getData());
        news.setText(newNews.getText());
        news.setType(newNews.getType());
        newsService.saveOrUpdateNews(news);
        return "redirect:/news/" + id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteNews(@PathVariable("id") Integer id, Model model) {
        News news = newsService.getNewsById(id);
        if (news != null) {
            newsService.deleteNews(news);
            model.addAttribute("newslist", newsService.getAllNews());
            return "redirect:/news/";
        } else {
            throw new CustomGenericException(messageProperties.getMessage("error.code.News.DeleteNews"),
                    messageProperties.getMessage("error.News.LinkIsNull"));
        }
    }
}


