
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import models.Task;

public class FileIO {
	 private static String escapeJson(String input) {
	        // Replace characters that are special in JSON
	        return input.replaceAll("\"", "\\\\\"");
	    }
   
   public static void writeTasksToFile(List<Task> tasks) {

        final String FILENAME = "tasks.txt";
        File file = new File(FILENAME);
        JSONObject m=new JSONObject();
    

        // Build JSON objects for each task
        for (int i = 0; i < tasks.size(); i++) {
           Task task = tasks.get(i);
          m.put("task_priority", task.getPrioirty());
          m.put("task date", task.getDue_date());
          m.put("task status", task.getStaus());
          m.put("task_title",task.getTask_title());
          m.put("task_description",task.getTask_description());
         
       }

       

        try (FileWriter fileWriter = new FileWriter(FILENAME)) {
           fileWriter.write(m.toString());
            System.out.println("Tasks saved to file: " + FILENAME);
        } catch (IOException e) {
           System.err.println("Error writing tasks to file: " + e.getMessage());
       }
    }
   public static void readTasksFromFile(String filename)throws IOException {
       List<Task> tasks = new ArrayList<>();

           // Parse JSON file
    	   try (FileReader reader = new FileReader(filename)) {
               StringBuilder sb = new StringBuilder();
               int character;
               while ((character = reader.read()) != -1) {
                   sb.append((char) character);
               }
//               System.out.print(sb);
               JSONArray  JsonArray=  new JSONArray(sb.toString());
           // Process each JSON object in the array
           for (int i = 0; i < JsonArray.length(); i++) {
               JSONObject jsonObject = JsonArray.getJSONObject(i);
            
               String title = jsonObject.getString("task_title");
               String description = jsonObject.getString("task_description");
               Status status = Status.valueOf(jsonObject.getString("task status"));
              Priority priority=Priority.valueOf(jsonObject.getString("task_priority"));
//              tasks.add(new Task(title,description,priority,status));
              System.out.print(tasks.toString());
           }

       } catch (IOException e) {
           e.printStackTrace();
       }


       return ;
   }

}

