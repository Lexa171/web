package com.epam.spring.model;


import javax.persistence.*;


import com.epam.spring.dto.NewsDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "News", uniqueConstraints = {@UniqueConstraint(columnNames = "NEWS_ID")})
public class News {
    private Integer id;
    private String data;
    private String author;
    private String name;
    private String text;
    private String type;

    public News() {
    }

    public News(NewsDto newsDto) {
        this.name = newsDto.getName();
        this.author = newsDto.getAuthor();
        this.data = newsDto.getData();
        this.text = newsDto.getText();
        this.type = newsDto.getType();
    }

    @Id
    @GeneratedValue
    @Column(name = "NEWS_ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NEWS_DATA", nullable = false, length = 15)

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Column(name = "NEWS_NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "NEWS_AUTHOR", nullable = false, length = 30)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "NEWS_TEXT", nullable = false, length = 6000)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "NEWS_Type", nullable = false, length = 30)

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
