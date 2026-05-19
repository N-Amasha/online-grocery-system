package com.WD38.GroceryOnline.JsonCRUD;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.WD38.GroceryOnline.apibodies.JsonDataItem;
import com.WD38.GroceryOnline.orderstuff.Order;

import tools.jackson.databind.JavaType;
import tools.jackson.databind.ObjectMapper;

public class JSONHandler<T extends JsonDataItem> {

    private final File jsonFile;
    private final ObjectMapper objectMapper;
    private final Class<T> type;

    public JSONHandler(String jsonFilePath, Class<T> type) throws IOException {
        this.jsonFile = new File(jsonFilePath);
        this.objectMapper = new ObjectMapper();
        this.type = type;

        ensureFileExists();
    }

    private void ensureFileExists() throws IOException {
        File parent = jsonFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        if (!jsonFile.exists()) {
            jsonFile.createNewFile();
            writeAll(new ArrayList<>());
        }

        if (jsonFile.length() == 0) {
            writeAll(new ArrayList<>());
        }
    }

    private ArrayList<T> readAllInternal() throws IOException {
        if (jsonFile.length() == 0) {
            return new ArrayList<>();
        }

        System.out.println("Json file length: " + jsonFile.length());

        JavaType listType = objectMapper
                .getTypeFactory()
                .constructCollectionType(ArrayList.class, type);

        ArrayList<T> data = objectMapper.readValue(
                jsonFile,
                listType
        );

        return data != null ? data : new ArrayList<>();
    }

    private void writeAll(ArrayList<T> data) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, data);
    }

    // CREATE
    public void add(T object) throws IOException {
        ArrayList<T> data = readAllInternal();
        data.add(object);
        writeAll(data);
    }

    // READ ALL
    public ArrayList<T> readAll() throws IOException {
        return new ArrayList<>(readAllInternal());
    }

    // READ BY INDEX
    public T readByIndex(int index) throws IOException {
        ArrayList<T> data = readAllInternal();
        T outValue = null;

        for (T item: data) {
            if (item.getId() == index)
                outValue = item;
        }

        return outValue;

        // if (index < 0 || index >= data.size()) {
        //     return null;
        // }
        // return data.get(index);
    }

    // UPDATE BY INDEX
    public boolean update(int id, T updatedObject) throws IOException {
        int listIndex = -1;
        ArrayList<T> data = readAllInternal();
        // T selectedValue = null;

        // if (index < 0 || index >= data.size()) {
        //     return false;
        // }

        for (int i=0; i<data.size(); i++) {
            T currentItem = data.get(i);
            if (currentItem.getId() == id) {
                listIndex = i;
                break;
            }
        }      

        if (listIndex == -1)
            return false;

        data.set(listIndex, updatedObject);
        writeAll(data);
        return true;
    }

    @Deprecated
    // DELETE BY INDEX
    public boolean delete(int index) throws IOException {
        ArrayList<T> data = readAllInternal();
        if (index < 0 || index >= data.size()) {
            return false;
        }

        data.remove(index);
        writeAll(data);
        return true;
    }

    // REPLACE ENTIRE FILE
    public void replaceAll(ArrayList<T> newData) throws IOException {
        writeAll(new ArrayList<>(newData));
    }

    public static void main(String[] args) throws IOException {
        // Scanner input = new Scanner(System.in);

        JSONHandler<Order> myJSONHandler = new JSONHandler<>("src/main/java/com/WD38/GroceryOnline/JsonCRUD/example.json", Order.class);

        Order order2 = new Order(2, "Sarah Wilson", "shipped");
        order2.addItem("apple", 5, 25.0, "PID001");
        order2.addItem("eggs", 2, 12.0, "PID020");

        Order order3 = new Order(3, "Michael Brown", "delivered");
        order3.addItem("milk", 3, 18.0, "PID015");
        order3.addItem("cheese", 1, 10.0, "PID016");

        Order order4 = new Order(4, "Emily Davis", "packing");
        order4.addItem("bread", 8, 32.0, "PID023");
        order4.addItem("butter", 2, 14.0, "PID021");

        Order order5 = new Order(5, "David Lee", "cancelled");
        order5.addItem("orange", 12, 60.0, "PID011");
        order5.addItem("grapes", 4, 20.0, "PID012");   
        
        // myJSONHandler.add(order2);
        // myJSONHandler.add(order3);
        // System.out.println("Orders added");

        // ArrayList<Order> allOrders = myJSONHandler.readAll();
        
        // for (Order order: allOrders) {
        //     order.generateInvoice();
        //     System.out.println("");
        // }

        myJSONHandler.update(2, order4);

        System.out.println("update successfull");

        Order readOrder = myJSONHandler.readByIndex(4);
        readOrder.generateInvoice();
    }
}
