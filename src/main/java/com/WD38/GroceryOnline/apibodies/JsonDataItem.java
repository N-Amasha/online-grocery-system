package com.WD38.GroceryOnline.apibodies;

public class JsonDataItem {
    protected int id;

    public void setId(int id) {
        if (id>=0)
            this.id = id;
        else
            System.out.println("Error: id must be >= 0");
    }

    public int getId() {
        return id;
    }

}
