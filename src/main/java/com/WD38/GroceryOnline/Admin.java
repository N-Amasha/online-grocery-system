package com.WD38.GroceryOnline;

import java.io.File;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;


public class Admin {
    private static final String QUANTITY_KEYWORD = "quantity threshold";
    private static final String EXPIRY_KEYWORD = "expiry threshold";

    private int expiryThreshold;
    private int quantityThreshold;

    private File configFile;
    private ObjectNode configFileReference;
    private ObjectMapper mapper;

    public Admin(String filePath) {
        mapper = new ObjectMapper();
        configFile = new File(filePath);
        this.configFileReference = (ObjectNode) mapper.readTree(configFile);

        this.expiryThreshold = this.configFileReference.get(EXPIRY_KEYWORD).asInt();
        this.quantityThreshold = this.configFileReference.get(QUANTITY_KEYWORD).asInt();
    }

    public int getExpiryThreshold() {
        return expiryThreshold;
    }

    public int getQuantityThreshold() {
        return quantityThreshold;
    }

    public int setExpiryThreshold(int expiryThreshold) {
        if (expiryThreshold < 0)
            return -1;
        else {
            this.expiryThreshold = expiryThreshold;
            return 0;
        }
    }

    public int setQuantityThreshold(int quantityThreshold) {
        if (quantityThreshold < 0)
            return -1;
        else {
            this.quantityThreshold = quantityThreshold;
            return 0;
        }
    }

    public int setThresholds(int expiryThreshold, int quantityThreshold) {
        if (quantityThreshold < 0 || expiryThreshold < 0)
            return -1;
        else {
            this.quantityThreshold = quantityThreshold;
            this.expiryThreshold = expiryThreshold;

            // update file
            this.configFileReference.put(EXPIRY_KEYWORD, expiryThreshold);
            this.configFileReference.put(QUANTITY_KEYWORD, quantityThreshold);
            mapper.writerWithDefaultPrettyPrinter().writeValue(configFile, configFileReference);

            return 0;
        }        
    }

    public static void main(String[] args) {
        final String CONFIG_PATH_ABSOLUTE = "/home/meeee/Desktop/projects/uni stuff/project grocery/admin dashboard/backend/GroceryOnline (1)/GroceryOnline/src/main/resources/adminConfig.json";
        Admin myAdmin = new Admin(CONFIG_PATH_ABSOLUTE);

        System.out.println("Exp Thresh: " + myAdmin.getExpiryThreshold());
        System.out.println("Quantity Thresh: " + myAdmin.getQuantityThreshold());

        // myAdmin.setThresholds(0, 0);
    }
    
}
