package org.example.storage.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BooksAmDataRecord {
    String url;
    String html;

    BooksAmDataRecord(String url, String html){
        this.url = url;
        this.html = html;
    }

    public String getPageUrl() {
        return this.url;
    }

    public String getBookUrl() {
        Document document = Jsoup.parse(this.html);
        Element element = document.selectFirst(".product_name .combo_link");
        return element != null ? element.attr("href") : "N/A";
    }

    public String getBookTitle() {
        return getFirstElementText(".product_name .combo_link");
    }

    public String getAuthor() {
        return getFirstElementText(".product_author");
    }

    public String getPrice() {
        Document document = Jsoup.parse(this.html);
        Element element = document.selectFirst(".price-box .price-container .price");
        return element != null ? element.text().trim() : "N/A";
    }

    private String getFirstElementText(String selector) {
        Document document = Jsoup.parse(this.html);
        Element element = document.selectFirst(selector);
        return element != null ? element.text().trim() : "";
    }

    public String toString() {
        return "Page URL: " + url +
                "Book URL: " + this.getBookUrl() +
                ", Book Title: " + this.getBookTitle() +
                ", Author: " + this.getAuthor() +
                ", Price in AMD: " + this.getPrice();
    }
}
