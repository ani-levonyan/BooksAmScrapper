package org.example.task;

import org.example.utils.SortingAlgorithms;
import org.example.utils.DataStructures.Stack;
import org.example.storage.model.BooksAmDataRecord;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataRecordSorting {

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
        BooksAmDataRecord[] recordsArray= records.toArray(new BooksAmDataRecord[0]);

        // Use the selection sorting algorithm defined in SortingAlgorithms to sort the recordArray based on price.
        SortingAlgorithms.selectionSort(recordsArray);

        // Print each record after sorting
        for (BooksAmDataRecord record : recordsArray) {
            System.out.println("Page URL: " + record.getPageUrl());
            System.out.println(", Book URL: " + record.getBookUrl());
            System.out.println(", Book Title: " + record.getBookTitle());
            System.out.println(", Author: " + record.getAuthor());
            System.out.println("Price in AMD: " + record.getPrice());
            System.out.println("-----");
        }

        System.out.println("Adding sorted records to the stack...");
        Stack stack = new Stack();
        for (BooksAmDataRecord record : recordsArray) {
            stack.push(record);
        }

        System.out.println("Printing all records:");
        stack.display();

        String searchTitle = "Финансист. Титан. Стоик";
        System.out.println("Searching for book title: " + searchTitle);
        stack.search(searchTitle);

        System.out.println("Removing the top record from the stack...");
        BooksAmDataRecord removedRecord = stack.pop();
        if (removedRecord != null) {
            System.out.println("Removed record: " + removedRecord);
        }

        System.out.println("Stack after removing the top record:");
        stack.display();
    }
}