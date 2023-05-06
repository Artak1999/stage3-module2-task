package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.CommandHandler;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;
    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService) {
        this.newsService = newsService;
    }

    @Override
    @CommandHandler(operation = 1)
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    @CommandHandler(operation = 2)
    public List readAll() {
        return newsService.readAll();
    }

    @Override
    @CommandHandler(operation = 3)
    public NewsDtoResponse readById(Long id) {
        return newsService.readById(id);
    }

    @Override
    @CommandHandler(operation = 4)
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    @CommandHandler(operation = 5)
    public boolean deleteById(Long id) {
        return newsService.deleteById(id);
    }
}
