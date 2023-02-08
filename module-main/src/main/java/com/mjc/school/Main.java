package com.mjc.school;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.mjc.school.service",
        "com.mjc.school.repository",
        "com.mjc.school.controller",
        "com.mjc.school"})
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Menu menu = context.getBean(Menu.class);
        menu.printMenuList();
    }
}