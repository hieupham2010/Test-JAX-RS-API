package edu.tdt.exercise;

import java.io.IOException;
import java.util.List;
import java.io.OutputStream;
import java.io.Writer;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
public class WriteFileJRS353 {
	public void writeStudentList(String outFile , List<StudentOut> students) throws IOException {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        /**
         * Builds JsonArray from list of Employee
         */
        for (StudentOut student : students) {
            //Add desired name-value pairs to the array.
            //This example adds employee attributes and values 
            //that is read from Employee object

            jsonArrayBuilder.add(
                    Json.createObjectBuilder()
                            .add("id", student.getId())
                            .add("firstName", student.getFirstName())
                            .add("lastName", student.getLastName())
                            .add("faculty", student.getFaculty())
                            .add("school year", student.getSchoolYear())
            );

        }
        JsonArray employeesArray = jsonArrayBuilder.build();

        try ( //write to file
                OutputStream outputStream = new FileOutputStream(outFile);
                Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
                JsonWriter jsonWriter = Json.createWriter(writer)) {

            jsonWriter.writeArray(employeesArray);
            writer.flush();
        }

    }
}
