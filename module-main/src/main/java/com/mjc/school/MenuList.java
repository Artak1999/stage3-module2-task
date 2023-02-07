package com.mjc.school;


public enum MenuList {
    CREATE_NEWS(1,"Create News"),
    CREATE_AUTHOR(2,"Create Author"),
    GET_ALL_NEWS(3,"Get All News"),
    GET_ALL_AUTHORS(4,"Get All Authors"),
    GET_NEWS_BY_ID(5,"Get News By Id"),
    GET_AUTHOR_BY_ID(6,"Get Author By Id"),
    UPDATE_NEWS(7,"Update News"),
    UPDATE_AUTHOR(8,"Update Author"),
    DELETE_NEWS(9,"Delete News"),
    DELETE_AUTHOR(10,"Delete Author"),
    EXIT(0,"Exit");

    private int id;
    private String values;

    MenuList(int id, String values) {
        this.id = id;
        this.values = values;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
