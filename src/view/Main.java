package view;

import controller.ProductManager;

public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        while (true) {
            productManager.menu();
        }
    }
}
