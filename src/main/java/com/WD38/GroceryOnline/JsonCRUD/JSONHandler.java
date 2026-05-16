package com.WD38.GroceryOnline.JsonCRUD;

import java.io.File;
import java.util.ArrayList;

import tools.jackson.databind.ObjectMapper;


class BoilderDataObj {
    private int id;
    private int amount;

    public BoilderDataObj(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public BoilderDataObj() {
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }
}


public class JSONHandler {
    protected File jsonFile;
    protected ObjectMapper mapper;

    public JSONHandler(String filePath) {
        mapper = new ObjectMapper();
        jsonFile = new File(filePath);
    }
    
    public void addSomeData() {
        ArrayList<BoilderDataObj> data = new ArrayList<>();

        data.add(new BoilderDataObj(1, 0));
        data.add(new BoilderDataObj(2, 5));
        data.add(new BoilderDataObj(3, 12));

        try {
            // 3. Handle the exception to see what's going wrong
            mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, 2);
            System.out.println("JSON file updated successfully.");
        } catch (Exception e) {
            System.err.println("Failed to write JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JSONHandler myJSONHandler = new JSONHandler("example.json");
        myJSONHandler.addSomeData();
        System.out.println("Done!");
    }

}
