package edu.tdt.exercise;
import javax.ws.rs.core.MediaType;




import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.google.gson.Gson;


@Path("/RenderJson")
public class HandleFile {
	private static final Logger logger = Logger.getLogger(HandleFile.class.getName()); 
	private static final String Directory = "C:\\uploadedFiles\\";
	@POST
	@Path("/Render")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response saveFileUpload(@FormDataParam("Type") String Type, @FormDataParam("fileUpload") InputStream fileUpload) throws Exception {
		try {
			File theDir = new File(Directory);
	        if (!theDir.exists()) {
	            theDir.mkdir();
	        }
		}catch(Exception e) {
			return Response.status(500).entity("Server Error").build();
		}
		String uploadFileLocation = Directory + "input.json";
		try {
			saveToFile(fileUpload, uploadFileLocation);
		}catch(IOException e) {
			return Response.status(500).entity("Server Error").build();
		}
		logger.setLevel(Level.INFO);
		ReadFileJRS353 readFile = new ReadFileJRS353();
		List<StudentIn> student = readFile.buildStudentList(uploadFileLocation);
		List<StudentOut> studentOut = handleData(student);
		WriteFileJRS353 writeFile = new WriteFileJRS353();
		writeFile.writeStudentList(Directory + "output.json", studentOut);
		logger.log(Level.INFO, studentOut.toString());
		String res = "<h1>Processing completed</h1><a href=\"http://localhost:8080/Exercise/soa/DowloadFile/output.json\">Click here to get the result</a>";
		return Response.status(200).entity(res).type(MediaType.TEXT_HTML).build();
	}
	
	@GET
	@Path("/GetData")
	public Response getData() throws IOException {
		String uploadFileLocation = Directory + "input.json";
		ReadFileJRS353 readFile = new ReadFileJRS353();
		List<StudentIn> student = readFile.buildStudentList(uploadFileLocation);
		String json = new Gson().toJson(student);
		return Response.status(200).entity(json).type(MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("/DeleteData/{StudentID}")
	public Response deleteDataById(@PathParam("StudentID") String studentID) throws IOException {
		String uploadFileLocation = Directory + "input.json";
		ReadFileJRS353 readFile = new ReadFileJRS353();
		List<StudentIn> students = readFile.buildStudentList(uploadFileLocation);
		for(int i = 0; i < students.size(); i++) {
			if(students.get(i).getId().equals(studentID)) {
				students.remove(i);
			}
		}
		WriteFileJRS353 writeFile = new WriteFileJRS353();
		writeFile.writeStudentIn(uploadFileLocation, students);
		String json = new Gson().toJson(students);
		return Response.status(200).entity(json).type(MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("/UpdateData")
	public Response updateDataById(@FormParam("id") String studentID, @FormParam("name") String name) throws IOException {
		String uploadFileLocation = Directory + "input.json";
		ReadFileJRS353 readFile = new ReadFileJRS353();
		List<StudentIn> students = readFile.buildStudentList(uploadFileLocation);
		for(StudentIn student : students) {
			if(student.getId().equals(studentID)) {
				student.setName(name);
			}
		}
		WriteFileJRS353 writeFile = new WriteFileJRS353();
		writeFile.writeStudentIn(uploadFileLocation, students);
		String json = new Gson().toJson(students);
		return Response.status(200).entity(json).type(MediaType.APPLICATION_JSON).build();
	}
	public List<StudentOut> handleData(List<StudentIn> students) {
		List<StudentOut> studentOut = new ArrayList<>();
		for(int i = 0 ; i < students.size() ; i++) {
			String id = students.get(i).getId();
			String name = students.get(i).getName();
			String firtName = getFirstName(name);
			String lastName = getLastName(name);
			String faculty = getFaculty(id);
			String schoolYear = getSchoolYear(id);
			studentOut.add(new StudentOut(id,firtName,lastName,faculty,schoolYear));
		}
		return studentOut;
	}
	public String getSchoolYear(String id) {
		int code = (int)getSchoolYearCode(id);
		return "20" + code;
	}
	public int getSchoolYearCode(String id) {
		return Integer.parseInt((String.valueOf(id.charAt(1)) + String.valueOf(id.charAt(2))));
	}
	public String getFaculty(String id) {
		int code = getFacultyCode(id);
		String result = "";
		if(code == 1) {
			result = "Dược";
		}else if(code == 2) {
			result = "Điện - Điện tử";
		}else if(code == 3) {
			result = "Kế toán";
		}else if(code == 4) {
			result = "Khoa học xã hội và nhân văn";
		}else if(code == 5) {
			result = "Công nghệ thông tin";
		}else if(code == 6) {
			result = "Kỹ thuật công trình";
		}else if(code == 7) {
			result = "Luật";
		}else if(code == 8) {
			result = "Toán - Tin";
		}else {
			result = "Quản trị kinh doanh";
		}
		return result;
	}
	public int getFacultyCode(String id) {
		return Integer.parseInt(String.valueOf(id.charAt(0)));
	}
	public String getLastName(String name) {
		String[] result = name.split(" ");
		return result[result.length-1];
	}
	public String getFirstName(String name) {
		String[] s = name.split(" ");
		String result = "";
		for(int i = 0 ; i < s.length - 1 ; i++) {
			result += s[i];
			if(i < s.length - 2) {
				result += " ";
			}
		}
		return result;
	}
	public void saveToFile(InputStream inStream, String target) throws IOException {
        OutputStream out = null;
        int read = 0;
        byte[] bytes = new byte[1024];
        out = new FileOutputStream(new File(target));
        while ((read = inStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
    }

    
}
