package validate;

import models.Product;
import org.omg.IOP.CodecPackage.FormatMismatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateProduct {
    Scanner scanner = new Scanner(System.in);

    public int getIndexId(String id, ArrayList<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public String validateId(ArrayList<Product> products) {
        while (true) {
            try {
                System.out.println("Nhập mã sản phẩm: ");
                String id = scanner.nextLine();
                if (getIndexId(id, products) != -1) {
                    throw new Exception();
                }

                Pattern pattern = Pattern.compile("[0-9a-zA-Z]+");
                Matcher matcher = pattern.matcher(id);
                if (matcher.matches()) {
                    return id;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.err.println("Nhập sai mã sản phẩm, xin mời nhập lại! ");
            }
        }
    }

    public String validateString(String string) {
        while (true) {
            try {
                System.out.println("Nhập " + string);
                String st = scanner.nextLine();

                Pattern pattern = Pattern.compile(".+");
                Matcher matcher = pattern.matcher(st);

                if (matcher.matches()) {
                    return string;
                } else {
                    throw new Exception();

                }
            } catch (Exception e) {
                System.err.println("Lỗi định dạng, xin mời nhập lại");
            }
        }
    }

    public double validatePrice() {
        while (true) {
            try {
                System.out.println("Nhập giá sản phẩm: ");
                String db = scanner.nextLine();

                Pattern pattern = Pattern.compile("[0-9]+\\.?[0-9]{0,2}");
                Matcher matcher = pattern.matcher(db);

                if (matcher.matches()) {
                    return Double.parseDouble(db);
                }
            } catch (Exception e) {
                System.out.println("Lỗi định dạng giá, xin mời nhập lại! ");
            }


        }
    }

    public int validateAmount() {
        while (true) {
            try {
                System.out.println("Nhập số lượng sản phẩm: ");
                String amount = scanner.nextLine();

                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(amount);
                if (matcher.matches()) {
                    return Integer.parseInt(amount);
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Lỗi định dạng số lượng, xin mời nhập lại! ");
            }
        }
    }


}
