package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.CommandHandler;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;

    @Autowired
    public AuthorController(BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService) {
        this.authorService = authorService;
    }

    @Override
    @CommandHandler(operation = 6)
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return authorService.create(createRequest);
    }

    @Override
    @CommandHandler(operation = 7)
    public List<AuthorDtoResponse> readAll() {
        return authorService.readAll();
    }

    @Override
    @CommandHandler(operation = 8)
    public AuthorDtoResponse readById(Long id) {
        return authorService.readById(id);
    }

    @Override
    @CommandHandler(operation = 9)
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        return authorService.update(updateRequest);
    }

    @Override
    @CommandHandler(operation = 10)
    public boolean deleteById(Long id) {
        return authorService.deleteById(id);
    }
}
