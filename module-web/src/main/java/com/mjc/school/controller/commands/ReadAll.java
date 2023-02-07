package com.mjc.school.controller.commands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;

public class ReadAll<T, R, K> implements Command {
    private final BaseController<T, R, K> controller;

    public ReadAll(BaseController<T, R, K> controller) {
        this.controller = controller;
    }


    @Override
    public Object execute() {
        return controller.readAll();
    }
}
