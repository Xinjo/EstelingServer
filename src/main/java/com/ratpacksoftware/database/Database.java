package com.ratpacksoftware.database;

import java.io.*;

import com.google.gson.*;

/**
 * Created by stijn on 31-5-2017.
 */
public class Database {
    private String dir;
    private Data data;
    private File path;

    public Database(String directory) {
        this.dir = directory + "\\";
        path = new File(dir + "data.json");
        System.out.println("Database initialized in directory: " + this.dir);
    }

    public void loadData(){
        Gson gson = new Gson();

        if (path.exists()) {
            try {
                this.data = gson.fromJson(new FileReader(dir + "data.json"), Data.class);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData(){
        Gson gson = new Gson();

        if (path.exists()) {
            try {
                path.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(gson.toJson(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}