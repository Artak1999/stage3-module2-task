package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.AuthorModel;
import com.mjc.school.repository.model.implementation.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.NewsValidator;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.Error;
import com.mjc.school.service.exception.ServiceException;
import com.mjc.school.service.interfaces.NewsMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    private final BaseRepository<NewsModel, Long> newsRepository;
    private final BaseRepository<AuthorModel, Long> authorRepository;
    private final NewsValidator newsValidator;
    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> repository, BaseRepository<AuthorModel, Long> authorRepository, NewsValidator validator) {
        this.newsRepository = repository;
        this.authorRepository = authorRepository;
        this.newsValidator = validator;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsMapper.listOfModelsToListOfResponses(newsRepository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        newsValidator.validateNewsId(id);
        Optional<NewsModel> maybeNullModel = newsRepository.readById(id);
        if (maybeNullModel.isEmpty()) {
            throw new ServiceException(String.format(Error.NEWS_DOES_NOT_EXIST.toString(), id));
        }
        return newsMapper.modelToDTOResponse(maybeNullModel.get());
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        newsValidator.validateNewsRequest(createRequest);
        NewsModel model = newsMapper.DtoRequestToModel(createRequest);
        if (!authorRepository.existById(model.getAuthorId())) {
            throw new ServiceException(String.format(Error.AUTHOR_DOES_NOT_EXIST.toString(), model.getAuthorId()));
        }
        LocalDateTime creationDate = LocalDateTime.now();
        model.setCreateDate(creationDate);
        model.setLastUpdateDate(creationDate);
        model = newsRepository.create(model);
        return newsMapper.modelToDTOResponse(model);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        newsValidator.validateNewsRequest(updateRequest);
        newsValidator.validateNewsId(updateRequest.getId());
        NewsModel requestModel = newsMapper.DtoRequestToModel(updateRequest);
        requestModel.setLastUpdateDate(LocalDateTime.now());
        NewsModel updateModel = newsRepository.update(requestModel);
        if (updateModel == null) {
            throw new ServiceException(String.format(Error.NEWS_DOES_NOT_EXIST.toString(), updateRequest.getId()));
        }
        return newsMapper.modelToDTOResponse(updateModel);
    }

    @Override
    public boolean deleteById(Long id) {
        newsValidator.validateNewsId(id);
        if (!newsRepository.deleteById(id)) {
            throw new ServiceException(String.format(Error.NEWS_DOES_NOT_EXIST.toString(), id));
        }
        return true;
    }
}