package kr.co.kwan.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private Long id;
    private final String name;
    private final String address;
    private List<MenuItem> MenuItems = new ArrayList<MenuItem>();

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return "Bob zip";
    }

    public List<MenuItem> getMenuItems() {
        return MenuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            addMenuItem(menuItem);
        }
    }

    public void addMenuItem(MenuItem menuItem) {
        MenuItems.add(menuItem);
    }

    public String getInformation() {
        return name + " in seoul";
    }
}
