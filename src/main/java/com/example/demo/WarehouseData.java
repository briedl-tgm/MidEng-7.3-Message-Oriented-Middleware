package com.example.demo;

public class WarehouseData {
    private String warehouseId;
    private String date;
    private int stockCount;

    // Default-Konstruktor für JSON Deserialisierung erforderlich
    public WarehouseData() {
    }

    public WarehouseData(String warehouseId, String date, int stockCount) {
        this.warehouseId = warehouseId;
        this.date = date;
        this.stockCount = stockCount;
    }

    // Getter und Setter
    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    @Override
    public String toString() {
        return "WarehouseData{" +
                "warehouseId='" + warehouseId + '\'' +
                ", date='" + date + '\'' +
                ", stockCount=" + stockCount +
                '}';
    }
}