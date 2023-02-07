package com.mjc.school.repository;

import com.mjc.school.repository.model.implementation.AuthorModel;
import com.mjc.school.repository.model.implementation.NewsModel;
import com.mjc.school.repository.utils.Reader;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataSource {
    private static final String NEWS_TITLE_FILE_NAME = "news";
    private static final String NEWS_CONTENT_FILE_NAME = "content";
    private static final String AUTHORS_FILE_NAME = "authors";
    private static final int AMOUNT_OF_LINES_TO_READ = 20;
    private final List<NewsModel> news = new ArrayList<>();
    private final List<AuthorModel> authors = new ArrayList<>();

    public DataSource() {
        readAuthors();
        List<AuthorModel> shuffledAuthors = new ArrayList<>(this.authors);
        Collections.shuffle(shuffledAuthors);
        createNews(shuffledAuthors);
    }

    public List<NewsModel> getNews() {
        return news;
    }

    public List<AuthorModel> getAuthors() {
        return authors;
    }

    private void readAuthors() {
        List<String> authorsLines = Reader.readLines(AUTHORS_FILE_NAME, AMOUNT_OF_LINES_TO_READ);
        LocalDateTime randomDate;
        for (int i = 0; i < AMOUNT_OF_LINES_TO_READ; i++) {
            randomDate = Reader.getRandomDate();
            authors.add(new AuthorModel((long) i + 1, authorsLines.get(i), randomDate, randomDate));
        }
    }

    private void createNews(List<AuthorModel> authors) {
        List<String> titles = Reader.readLines(NEWS_TITLE_FILE_NAME, AMOUNT_OF_LINES_TO_READ);
        List<String> contents = Reader.readLines(NEWS_CONTENT_FILE_NAME, AMOUNT_OF_LINES_TO_READ);
        LocalDateTime randomDate;
        for (int i = 0; i < AMOUNT_OF_LINES_TO_READ; i++) {
            randomDate = Reader.getRandomDate();
            news.add(new NewsModel((long) i + 1, titles.get(i), contents.get(i), randomDate, randomDate, authors.get(i).getId()));
        }
    }
}
