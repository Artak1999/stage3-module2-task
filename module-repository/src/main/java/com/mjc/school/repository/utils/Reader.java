package com.mjc.school.repository.utils;

import java.time.*;
import java.util.*;
import java.io.*;

public class Reader {
    public static List<String> readLines(String path, int bound) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found: " + path);
        }
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            for (int i = 0; i < bound; i++) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    throw new IllegalArgumentException("File contains less than 20 lines!");
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return lines;
        }
        return lines;
    }
    public static LocalDateTime getRandomDate() {
        Random random = new Random();
        int year = LocalDateTime.now().getYear()+random.nextInt(10)-10;
        int month = random.nextInt(12)+1;
        int day = random.nextInt(28)+1;
        int hour = random.nextInt(23)+1;
        int minute = random.nextInt(59)+1;
        return LocalDateTime.of(year, Month.of(month), day, hour, minute);

    }
}
