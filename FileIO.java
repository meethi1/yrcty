package service;
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

import models.Task;

public class FileIO {
	 private static String escapeJson(String input) {
	        // Replace characters that are special in JSON
	        return input.replaceAll("\"", "\\\\\"");
	    }
   
   public static void writeTasksToFile(List<Task> tasks) {

        final String FILENAME = "tasks.txt";
        File file = new File(FILENAME);
        StringBuilder jsonBuilder = new StringBuilder();
    

        // Build JSON objects for each task
        for (int i = 0; i < tasks.size(); i++) {
           Task task = tasks.get(i);
           jsonBuilder.append("{\n");
          jsonBuilder.append("  \"taskTitle\": \"" + escapeJson(task.getTask_title()) + "\",\n");
          jsonBuilder.append("  \"taskDescription\": \"" + escapeJson(task.getTask_description()) + "\",\n");
           jsonBuilder.append("  \"priority\": \"" + task.getPrioirty() + "\",\n");
           jsonBuilder.append("  \"dueDate\": \"" +task.getDue_date() + "\",\n");
           jsonBuilder.append("  \"status\": \"" + task.getStaus() + "\"\n");
           jsonBuilder.append("}");

         if (i < tasks.size() ) {
               jsonBuilder.append(",");
           }
           jsonBuilder.append("\n");
       }

       

        try (FileWriter fileWriter = new FileWriter(FILENAME)) {
           fileWriter.write(jsonBuilder.toString());
            System.out.println("Tasks saved to file: " + FILENAME);
        } catch (IOException e) {
           System.err.println("Error writing tasks to file: " + e.getMessage());
       }
    }
   public static List<Task> readTasksFromFile(String filename) {
       List<Task> tasks = new ArrayList<>();

       try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
           String line;

           while ((line = br.readLine()) != null) {
               // Remove leading and trailing whitespace
               line = line.trim();

               // Skip empty lines
               if (line.isEmpty()) {
                   continue;
               }

               // Parse JSON object from line
               Task task = parseTaskFromJson(line);
               if (task != null) {
                   tasks.add(task);
               }
           }

       } catch (IOException e) {
           System.err.println("Error reading tasks from file: " + e.getMessage());
       }

       return tasks;
   }

   private static Task parseTaskFromJson(String jsonLine) {
	   Task task = new Task(); // Create a new Task object with default constructor
	   System.out.println(task.toString());
       try {
           // Remove leading and trailing whitespace
           jsonLine = jsonLine.trim();

           // Remove leading and trailing curly braces if present
           if (jsonLine.startsWith("{") && jsonLine.endsWith("}")) {
               jsonLine = jsonLine.substring(1, jsonLine.length() - 1);
           }
	  
           // Split by comma outside quotes
           String[] keyValuePairs = jsonLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

           SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

           for (String pair : keyValuePairs) {
               // Split by colon
               String[] keyValue = pair.split(":", 2);
               if (keyValue.length == 2) {
                   String key = keyValue[0].trim().replaceAll("\"", "");
                   String value = keyValue[1].trim().replaceAll("\"", "");

                   switch (key) {
                       case "taskTitle":
                           task.setTask_title(value);
                           break;
                       case "taskDescription":
                           task.setTask_description(value);
                           break;
                       case "priority":
                           task.setPrioirty(Priority.valueOf(value));
                           break;
                       case "dueDate":
                           task.setDue_date(dateFormat.parse(value));
                           break;
                       case "status":
                           task.setStaus(Status.valueOf(value));
                           break;
                   }
               }
           }

       } catch (ParseException e) {
           System.err.println("Error parsing date: " + e.getMessage());
       } catch (IllegalArgumentException e) {
           System.err.println("Error parsing enum value: " + e.getMessage());
       }

       return task;
   }
}

   

