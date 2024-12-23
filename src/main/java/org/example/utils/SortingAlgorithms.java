package org.example.utils;

import org.example.storage.model.BooksAmDataRecord;

public class SortingAlgorithms {
 /* Implement the selection sort which will sort descending the array objects based on the price. */

    public static void selectionSort(BooksAmDataRecord[] myArray){
        int myArrayLength = myArray.length;

        for (int i = 0; i < myArrayLength - 1; ++i){
            int maxIndex = i;
            double maxPrice = getPrice(myArray[maxIndex]);

            for (int j = i + 1; j < myArrayLength; ++j){
                double nextPrice = getPrice(myArray[j]);

                if(nextPrice > maxPrice){
                    maxIndex = j;
                    maxPrice = nextPrice;
                }
            }
            if (maxIndex != i) {
                BooksAmDataRecord temp = myArray[maxIndex];
                myArray[maxIndex] = myArray[i];
                myArray[i] = temp;
            }
        }
    }

    private static double getPrice(BooksAmDataRecord price) {
        return price.getPrice().isEmpty() ? 0 : formatPrice(price.getPrice());
    }

    private static double formatPrice(String price){
        price = price.replace(" ", "").replace("դր", "").replace(".", "");
        return Double.parseDouble(price);
    }
}
