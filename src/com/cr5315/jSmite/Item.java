package com.cr5315.jSmite;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public class Item {
    private int childItemId;
    private String description;
    private String deviceName;
    private int iconId;
    private int id;
    private int itemTier;
    private MenuItem[] menuItems;
    private int price;
    private int rootItemId;
    private String secondaryDescription;
    private String shortDescription;
    private boolean startingItem;
    private String type;

    public Item(JsonObject jsonObject) {
        childItemId = jsonObject.get("ChildItemId").asInt();
        deviceName = jsonObject.get("DeviceName").asString();
        description = jsonObject.get("ItemDescription").asObject().get("Description").asString();
        iconId = jsonObject.get("IconId").asInt();
        id = jsonObject.get("ItemId").asInt();
        itemTier = jsonObject.get("ItemTier").asInt();
        price = jsonObject.get("Price").asInt();
        rootItemId = jsonObject.get("RootItemId").asInt();
        shortDescription = jsonObject.get("ShortDesc").asString();
        startingItem = jsonObject.get("StartingItem").asBoolean();
        type = jsonObject.get("Type").asString();

        JsonArray menuItemArray = jsonObject.get("ItemDescription").asObject().get("Menuitems").asArray();
        menuItems = new MenuItem[menuItemArray.size()];
        for (int i = 0; i < menuItemArray.size(); i++) {
            JsonObject item = menuItemArray.get(i).asObject();
            menuItems[i] = new MenuItem(item.get("Description").asString(), item.get("Value").asString());
        }
    }

    public int getChildItemId() {
        return childItemId;
    }

    public void setChildItemId(int childItemId) {
        this.childItemId = childItemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemTier() {
        return itemTier;
    }

    public void setItemTier(int itemTier) {
        this.itemTier = itemTier;
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRootItemId() {
        return rootItemId;
    }

    public void setRootItemId(int rootItemId) {
        this.rootItemId = rootItemId;
    }

    public String getSecondaryDescription() {
        return secondaryDescription;
    }

    public void setSecondaryDescription(String secondaryDescription) {
        this.secondaryDescription = secondaryDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public boolean getStartingItem() {
        return startingItem;
    }

    public void setStartingItem(boolean startingItem) {
        this.startingItem = startingItem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    private class MenuItem {
        private String description;
        private String value;

        public MenuItem(String description, String value) {
            this.description = description;
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public String getValue() {
            return value;
        }
    }
}