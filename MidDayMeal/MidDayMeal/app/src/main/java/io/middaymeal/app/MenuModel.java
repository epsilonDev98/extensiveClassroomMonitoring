package io.middaymeal.app;

public class MenuModel {
    private String day;
    private String menu;

    public MenuModel(String day, String menu) {
        this.day = day;
        this.menu = menu;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
