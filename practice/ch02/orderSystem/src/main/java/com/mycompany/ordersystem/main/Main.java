package com.mycompany.ordersystem.main;

import com.mycompany.ordersystem.main.ui.*;
import com.mycompany.ordersystem.main.utils.Task;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {

    private static CustomerUI customerUI = null;
    private static ProductUI productUI = null;
    private static InventoryUI inventoryUI = null;
    private static OrderUI orderUI = null;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        customerUI = context.getBean("customerUI", CustomerUI.class);
        productUI = context.getBean("productUI", ProductUI.class);
        inventoryUI = context.getBean("inventoryUI", InventoryUI.class);
        orderUI = context.getBean("orderUI", OrderUI.class);

        Scanner scanner = new Scanner(System.in);
        Task task = Task.HELP;
        do {
            System.out.println("작업을 선택하세요(종료: 99, 도움말: 0): ");
            int input = 0;
            try {
                input = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("잘못된 명령입니다.");
                scanner.next();
            }
            task = Task.getOrdinal(input);
            switch (task) {
                case HELP -> HelpUI.displayHelp();
                case CUST_INSERT -> customerUI.insertCustomer();
                case CUST_GET -> customerUI.getCustomer();
                case CUST_GETALL -> customerUI.getAllCustomers();
                case CUST_UPDATE -> customerUI.updateCustomer();
                case CUST_DELETE -> customerUI.deleteCustomer();
                case CUST_AUTO -> customerUI.insertCustomerInfos();
                case PROD_INSERT -> productUI.insertProduct();
                case PROD_GET -> productUI.getProduct();
                case PROD_GETALL -> productUI.getAllProducts();
                case PROD_UPDATE -> productUI.updateProduct();
                case PROD_DELETE -> productUI.deleteProduct();
                case PROD_AUTO -> productUI.insertProductInfos();
                case INVTR_STORE -> inventoryUI.storeProduct();
                case INVTR_GET -> inventoryUI.getInventory();
                case INVTR_GETALL -> inventoryUI.getInventories();
                case INVTR_TAKE -> inventoryUI.takeInventory();
                case INVTR_AUTO -> inventoryUI.storeInventoryInfos();
                case ORDR_PURCHASE -> orderUI.purchaseOrder();
                case ORDR_GET -> orderUI.getOrder();
                case ORDR_GETALL -> orderUI.getOrders();
                case ORDR_CANCEL -> orderUI.cancelOrder();
            }
        } while (task != Task.QUIT);
    }
}
