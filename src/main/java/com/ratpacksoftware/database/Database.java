package com.ratpacksoftware.database;

import com.google.gson.stream.JsonReader;
import com.ratpacksoftware.Models.Action;
import com.ratpacksoftware.Models.Interaction;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;
import com.ratpacksoftware.Models.Vote;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;

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