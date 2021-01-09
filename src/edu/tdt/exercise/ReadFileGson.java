package edu.tdt.exercise;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.logging.Level;
public class ReadFileGson {
	public List<StudentIn> buildEmployeeList(String fileName) throws IOException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Type listType = new TypeToken<ArrayList<StudentIn>>() {
        }.getType();
        InputStream inputStream = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<StudentIn> employees = gson.fromJson(reader, listType);
        return employees;
    }
}
