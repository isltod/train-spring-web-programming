package com.mycompany.ordersystem.main.utils;

public enum Task {

    ERROR(-1), QUIT(99), HELP(0),
    CUST_INSERT(11), CUST_GET(12), CUST_GETALL(13),  CUST_UPDATE(14), CUST_DELETE(15), CUST_AUTO(16),
    PROD_INSERT(21), PROD_GET(22), PROD_GETALL(23),  PROD_UPDATE(24), PROD_DELETE(25), PROD_AUTO(26),
    INVTR_STORE(31), INVTR_GET(32), INVTR_GETALL(33), INVTR_TAKE(34), INVTR_AUTO(35),
    ORDR_PURCHASE(41), ORDR_GET(42), ORDR_GETALL(43), ORDR_CANCEL(44);

    private int id;

    Task(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Task getOrdinal(int id) {
        for (Task task : Task.values()) {
            if (task.getId() == id) {
                return task;
            }
        }
        return ERROR;
    }

}
