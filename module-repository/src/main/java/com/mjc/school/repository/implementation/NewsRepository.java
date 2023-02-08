package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.model.implementation.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel,Long> {
    private final DataSource dataSource;
    @Autowired
    public NewsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<NewsModel> readAll() {

        return dataSource.getNews();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return dataSource.getNews().stream().filter(news -> Objects.equals(news.getId(), id)).findFirst();
    }

    @Override
    public NewsModel create(NewsModel entity) {
        entity.setId((long) dataSource.getNews().size() + 1);
        dataSource.getNews().add(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        Optional<NewsModel> maybeNullModel = readById(entity.getId());
        if (maybeNullModel.isEmpty()) {
            return null;
        }
        NewsModel modelToUpdate = maybeNullModel.get();
        modelToUpdate.setAuthorId(entity.getAuthorId());
        modelToUpdate.setContent(entity.getContent());
        modelToUpdate.setTitle(entity.getTitle());
        modelToUpdate.setLastUpdateDate(entity.getLastUpdateDate());
        return modelToUpdate;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<NewsModel> model = readById(id);
        if (model.isEmpty()) {
            return false;
        }
        dataSource.getNews().remove(model.get());
        return true;
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getNews().stream().anyMatch(news -> id.equals(news.getId()));
    }
}
