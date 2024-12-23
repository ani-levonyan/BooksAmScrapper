package org.example.data_structures;


import org.example.storage.model.BooksAmDataRecord;
import org.example.utils.DataStructures.Stack;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataRecordDataStructures {

    public static List<BooksAmDataRecord> readDataRecordsFromJson(String filePath) {
        List<BooksAmDataRecord> dataRecords = new ArrayList<>();
        Gson gson = new Gson();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Type listType = new TypeToken<List<BooksAmDataRecord>>() {}.getType();
            dataRecords = gson.fromJson(reader, listType);
        }
        catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }

        return dataRecords;
    }

    public static void main(String[] args) {
        String filePath = "src/resources/books_am.json";

        List<BooksAmDataRecord> records = readDataRecordsFromJson(filePath);

        Stack stack = new Stack();
        for (BooksAmDataRecord record: records) {
            stack.push(record);
        }
        System.out.println("Stack contents:");
        stack.display();

    }
}
