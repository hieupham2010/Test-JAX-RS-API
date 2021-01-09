package edu.tdt.exercise;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import javax.json.Json;
import javax.json.JsonArray;

import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class ReadFileJRS353 {

	public List<StudentIn> buildStudentList(String jsonFileName) throws IOException {

        List<StudentIn> studentList = new ArrayList<>();
        
        try {
        	InputStream inputStream = new FileInputStream(jsonFileName);
        	JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject rootJSON = jsonReader.readObject();
            JsonArray studentArray = rootJSON.getJsonArray("data");
            jsonReader.close();
            for (JsonValue jsonValue : studentArray) {
                if (jsonValue.getValueType().equals(JsonValue.ValueType.OBJECT)) {
                    JsonObject jsonObject = (JsonObject) jsonValue;
                    StudentIn student = new StudentIn();
                    student.setId(jsonObject.getString("id"));
                    student.setName(jsonObject.getString("name"));
                    studentList.add(student);
                }
            }

        }catch(Exception e) {
        	System.out.print("Error");
        }
        return studentList;
    }
	
}
