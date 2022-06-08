package io;

import models.Product;

import java.io.*;
import java.util.ArrayList;

public class ReadAndWriteFile {

    File file = new File("products.csv");

    public ArrayList<Product> readFile() {

        ArrayList<Product> products = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(",");
                String id = arr[0];
                String name = arr[1];
                double price = Double.parseDouble(arr[2]);
                int amount = Integer.parseInt(arr[3]);
                String description = arr[4];

                products.add(new Product(id, name, price, amount, description));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Không tìm thấy file");
        } catch (IOException e) {
            System.err.println("Lỗi thực thi");
        }
        return products;
    }

    public void writeFile(ArrayList<Product> products) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("id,name,price,amount,description");
            bufferedWriter.newLine();

            for (Product s : products) {
                bufferedWriter.write(s.write());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Lỗi thực thi");
        }
    }
}
