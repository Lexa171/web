package com.epam.spring.dto;



import com.epam.spring.model.News;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NewsDto {
    @NotNull
    private String name;

    @NotNull
    @Size(min = 3, max = 30,
            message = "Authors name must be between 3 and 30 characters long.")
    @Pattern(regexp = "^[a-zA-Zа-яёА-ЯЁ0-9]+[a-zA-Zа-яёА-ЯЁ0-9,!.]*$",
            message = "Authors name must be alphanumeric with spaces or ,.!)")
    private String author;

    @NotNull
    @Size(min = 10, max = 10,
            message = "Data must be 10 numerics.")
    @Pattern(regexp = "^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$",
            message = "The date must be written on the model of:" + "\n" + "01.09.2006.")
    private String data;
    @NotNull
    private String text;
    @NotNull
    @Size(min = 3, max = 30,
            message = "News must be between 3 and 30 characters long.")
    @Pattern(regexp = "^[a-zA-Zа-яёА-ЯЁ0-9]+[a-zA-Zа-яёА-ЯЁ0-9,!.]*$",
            message = "News type must be alphanumeric with spaces or ,.!)")
    private String type;

    private Integer id;

    public NewsDto(String name, String author, String data) {
        this.name = name;
        this.author = author;
        this.data = data;
    }

    public NewsDto(News news) {
        this.name = news.getName();
        this.author = news.getAuthor();
        this.data = news.getData();
        this.id = news.getId();
        this.text=news.getText();
        this.type=news.getType();
    }

    public NewsDto() {

    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
