package controller;

import comparator.SortByPriceDown;
import comparator.SortByPriceUp;
import io.ReadAndWriteFile;
import models.Product;
import validate.ValidateProduct;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager {
    Scanner sc = new Scanner(System.in);
    ArrayList<Product> products = new ArrayList<>();
    ValidateProduct validateProduct = new ValidateProduct();
    ReadAndWriteFile readAndWriteFile = new ReadAndWriteFile();

    public void menu() {
        System.out.println("---- CHUONG TRÌNH QUẢN LÝ SẢN PHẨM ----");
        System.out.println("Chọn chức năng theo số (để tiếp tục)");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Sửa");
        System.out.println("4. Xóa");
        System.out.println("5. Sắp xếp");
        System.out.println("6. Tìm sản phẩm có giá đắt nhất");
        System.out.println("7. Đọc từ file");
        System.out.println("8. Ghi vào file");
        System.out.println("9. Thoát");
        System.out.println("Chọn chức năng: ");

        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                show();
                break;
            case 2:
                addProduct(creatProduct());
                break;
            case 3:
                editProduct();
                break;
            case 4:
                deleteProduct();
                break;
            case 5:
                sortByPrice();
                break;
            case 6:
                findMaxPrice();
                break;
            case 7:
                products = readAndWriteFile.readFile();
                System.out.println("Đọc file thành công! ");
                break;
            case 8:
                readAndWriteFile.writeFile(products);
                System.out.println("Ghi file thành công! ");
                break;
            case 9:
                System.exit(0);
        }
    }

    public void show() {
        for (int i = 0; i < products.size(); i++) {
            if (i + 1 % 5 == 0) {
                sc.nextLine();
            } else {
                System.out.println(products.get(i));
            }

        }
    }

    public Product creatProduct() {
        String id = validateProduct.validateId(products);
        String name = validateProduct.validateString("tên sản phẩm: ");
        Double price = validateProduct.validatePrice();
        int amount = validateProduct.validateAmount();
        String description = validateProduct.validateString("mô tả: ");
        Product product = new Product(id, name, price, amount, description);
        return product;
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Thêm sản phẩm thành công! ");
    }

    public void editProduct() {
        System.out.println("Nhập mã sản phẩm cần sửa: ");
        String id = sc.nextLine();
        int index = validateProduct.getIndexId(id, products);
        if (index != -1) {
            products.set(index, creatProduct());
        } else {
            System.err.println("Mã sản phẩm không tồn tại! ");
        }
    }

    public void deleteProduct() {
        System.out.println("Nhập mã sản phẩm cần xóa: ");
        String id = sc.nextLine();
        int index = validateProduct.getIndexId(id, products);
        if (index != -1) {
            System.out.println("Nhập xác nhận: ");
            System.out.println("Y: Xóa");
            System.out.println("Ký tự khác：Thoát ");
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("Y" + "")) {
                products.remove(index);
                System.out.println("Xóa thành công! ");
            }
        } else {
            System.out.println("Mã sản phẩm không tồn tại! ");
        }
    }

    public void sortByPrice() {
        while (true) {
            try {
                System.out.println("---- Sắp xếp sản phẩm theo giá ----");
                System.out.println("1. Sắp xếp sản phẩm theo giá giảm dần");
                System.out.println("2. Sắp xếp sản phẩm theo gia tăng dần");
                System.out.println("3. Thoát");
                System.out.println("Chọn chức năng: ");
                int choice2 = Integer.parseInt(sc.nextLine());
                switch (choice2) {
                    case 1:
                        products.sort(new SortByPriceUp());
                        System.out.println("Sắp xếp tăng dần thành công! ");
                        break;
                    case 2:
                        products.sort(new SortByPriceDown());
                        System.out.println("Sắp xếp giảm dần thành công! ");
                        break;
                    case 3:
                        break;
                }
            } catch (Exception e) {
                System.err.println("Nhập sai, xin mời nhập lại");
            }

        }
    }

    public void findMaxPrice() {
        double maxPrice = products.get(0).getPrice();
        Product product = products.get(0);
        for (int i = 1; i < products.size(); i++) {
            if (maxPrice < products.get(i).getPrice()) {
                maxPrice = products.get(i).getPrice();
                product = products.get(i);
            }
        }
        System.out.println(product);
    }
}
