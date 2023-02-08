package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.OnDelete;
import com.mjc.school.repository.model.implementation.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel,Long> {
    private final DataSource dataSource;

    @Autowired
    public AuthorRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthors();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return dataSource.getAuthors().stream().filter(news -> Objects.equals(news.getId(), id)).findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        entity.setId((long) dataSource.getAuthors().size()+1);
        dataSource.getAuthors().add(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        Optional<AuthorModel> maybeNullModel = readById(entity.getId());
        if (maybeNullModel.isEmpty()) return null;
        AuthorModel modelToUpdate = maybeNullModel.get();
        modelToUpdate.setId(entity.getId());
        modelToUpdate.setName(entity.getName());
        modelToUpdate.setLastUpdateDate(entity.getLastUpdateDate());
        return modelToUpdate;
    }

    @OnDelete
    @Override
    public boolean deleteById(Long id) {
        Optional<AuthorModel> maybeNullModel = readById(id);
        if (maybeNullModel.isEmpty())return false;
        dataSource.getAuthors().remove(maybeNullModel.get());
        return true;
    }

    @Override
    public boolean existById(Long id) {
        return this.dataSource.getAuthors().stream().anyMatch((author) -> id.equals(author.getId()));

    }
}
