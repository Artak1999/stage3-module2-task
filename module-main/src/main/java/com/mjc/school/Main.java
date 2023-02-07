package com.mjc.school;

import org.springframework.context.annotation.ComponentScan;
import static java.lang.System.exit;
import java.util.Scanner;

@ComponentScan("com.mjc.school.*")
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();
        while (true) {
            menu.printMenu();
            System.out.print("Enter number: ");
            String number = input.nextLine();
            switch (number) {
                case "1" -> menu.createNews(input);
                case "2" -> menu.createAuthor(input);
                case "3" -> menu.getAllNews();
                case "4" -> menu.getAllAuthors();
                case "5" -> menu.getNewsById(input);
                case "6" -> menu.getAuthorById(input);
                case "7" -> menu.updateNews(input);
                case "8" -> menu.updateAuthor(input);
                case "9" -> menu.deleteNews(input);
                case "10" -> menu.deleteAuthor(input);
                case "0" -> exit(0);
                default -> System.out.println("Command not found.Please enter again!");
            }
        }
    }
}