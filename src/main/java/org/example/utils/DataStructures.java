package org.example.utils;

import org.example.storage.model.BooksAmDataRecord;

public class DataStructures {

    /* Stack Implementation for storing BooksAmDataRecord */
    public static class Stack {
        Node top;

        public Stack() {
            this.top = null;
        }

        static class Node {
            BooksAmDataRecord data;
            Node next;

            public Node(BooksAmDataRecord data) {
                this.data = data;
            }
        }

        public void push(BooksAmDataRecord data) {
            Node newNode = new Node(data);
            newNode.next = top;
            top = newNode;
            System.out.println("Added: " + data.getBookUrl());
        }

        public BooksAmDataRecord pop() {
            if (top == null) {
                System.out.println("Stack is empty");
                return null;
            }
            BooksAmDataRecord data = top.data;
            top = top.next;
            System.out.println("Removed: " + data.getBookUrl());
            return data;
        }

        public BooksAmDataRecord peek() {
            if (top == null) {
                System.out.println("Stack is empty");
                return null;
            }
            return top.data;
        }

        public void display() {
            if (top == null) {
                System.out.println("Stack is empty");
                return;
            }
            Node current = this.top;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }
        }

        public BooksAmDataRecord search(String title) {
            Node current = this.top;
            while (current != null) {
                if (current.data.getBookTitle().equalsIgnoreCase(title)) {
                    System.out.println("Found: " + current.data);
                    return current.data;
                }
                current = current.next;
            }
            System.out.println("Book not found: " + title);
            return null;
        }
    }
}
