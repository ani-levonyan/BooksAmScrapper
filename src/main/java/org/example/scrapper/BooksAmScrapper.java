package org.example.scrapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.storage.model.BooksAmDataRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BooksAmScrapper {

    private static final Gson gson = new Gson();
    String resourcePath = Paths.get(System.getProperty("user.dir"), "src", "resources", "books_am.json").toString();

        public static void main(String[] args) throws InterruptedException {
            List<Map<String, String>> scrapedDataList = new ArrayList<>();

            for (int i = 25; i > 2; i--) {
             Thread.sleep(50);
             Map<String, String> data = scrapeData(i);
             if (data != null) {
                 scrapedDataList.add(data);
             }
         }

        saveDataList(scrapedDataList);

        readData();
     }


    private static Map<String, String> scrapeData(int pageNumber) {
        try {
            String url = String.format("https://www.books.am/am/catalog/category/view/id/7463/?p=%d", pageNumber);
            System.out.println("Scraping URL: " + url);
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();

            Map<String, String> scrapedData = new HashMap<>();
            scrapedData.put("url", url);
            scrapedData.put("html", doc.outerHtml());
            return scrapedData;

        }
        catch (IOException e) {
            System.out.println("Error scraping data: " + e.getMessage());
            return null;
        }
    }

    private static void saveDataList(List<Map<String, String>> dataList) {
        String resourcePath = Paths.get(System.getProperty("user.dir"), "src", "resources", "books_am.json").toString();

        try (FileWriter writer = new FileWriter(resourcePath)) {
            gson.toJson(dataList, writer);
            System.out.println("Data saved to " + resourcePath);
        }
        catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void readData() {
        String resourcePath = Paths.get(System.getProperty("user.dir"), "src", "resources", "books_am.json").toString();

        try (BufferedReader reader = new BufferedReader(new FileReader(resourcePath))) {
            List<Map<String, String>> dataList = gson.fromJson(reader, new TypeToken<List<Map<String, String>>>() {}.getType());

            for (Map<String, String> data : dataList) {
                System.out.println("URL: " + data.get("url"));
                System.out.println("HTML: " + data.get("html").substring(0, 50) + "...");
                System.out.println("-----");
            }
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }
}