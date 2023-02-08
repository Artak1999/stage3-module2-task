package com.mjc.school.controller.commands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {
    public static final int CREATE_NEWS = 1;
    public static final int GET_ALL_NEWS = 2;
    public static final int GET_NEWS_BY_ID = 3;
    public static final int UPDATE_NEWS = 4;
    public static final int DELETE_NEWS = 5;
    public static final int CREATE_AUTHOR = 6;
    public static final int GET_ALL_AUTHORS = 7;
    public static final int GET_AUTHOR_BY_ID = 8;
    public static final int UPDATE_AUTHOR = 9;
    public static final int DELETE_AUTHOR = 10;
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;

    @Autowired
    public CommandFactory(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                          BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.newsController = newsController;
        this.authorController = authorController;
    }

    public Command create(int commandCode, Object... params) {
        switch (commandCode) {
            case CREATE_NEWS:
                return new Create<>(newsController, new NewsDtoRequest(null, (String) params[0], (String) params[1], (Long) params[2]));
            case GET_ALL_NEWS:
                return new ReadAll<>(newsController);
            case GET_NEWS_BY_ID:
                return new GetById<>(newsController, (Long) params[0]);
            case UPDATE_NEWS:
                return new Update<>(newsController, new NewsDtoRequest((Long) params[0], (String) params[1], (String) params[2], (Long) params[3]));
            case DELETE_NEWS:
                return new Delete<>(newsController, (Long) params[0]);

            case CREATE_AUTHOR:
                return new Create<>(authorController, new AuthorDtoRequest(null, (String) params[0]));
            case GET_ALL_AUTHORS:
                return new ReadAll<>(authorController);
            case GET_AUTHOR_BY_ID:
                return new GetById<>(authorController, (Long) params[0]);
            case UPDATE_AUTHOR:
                return new Update<>(authorController, new AuthorDtoRequest((Long) params[0], (String) params[1]));
            case DELETE_AUTHOR:
                return new Delete<>(authorController, (Long) params[0]);
            default:
                return null;
        }

    }
}
