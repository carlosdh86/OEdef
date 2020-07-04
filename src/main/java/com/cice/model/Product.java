package com.cice.model;

public class Product {

    int productId;
    String productName;
    String productDescription;
    int categoryId;
    int weightClass;
    String warrantyPeriod;
    int supplierId;
    String productStatus;
    Double listPrice;
    String catalogUrl;
    int warehouseId;
    int quantity;

    public Product() {
    }

    public Product(int productId, String productName, String productDescription, int categoryId, int weightClass, String warrantyPeriod, int supplierId, String productStatus, Double listPrice, String catalogUrl, int warehouseId, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.categoryId = categoryId;
        this.weightClass = weightClass;
        this.warrantyPeriod = warrantyPeriod;
        this.supplierId = supplierId;
        this.productStatus = productStatus;
        this.listPrice = listPrice;
        this.catalogUrl = catalogUrl;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(int weightClass) {
        this.weightClass = weightClass;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public String getCatalogUrl() {
        return catalogUrl;
    }

    public void setCatalogUrl(String catalogUrl) {
        this.catalogUrl = catalogUrl;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", categoryId=" + categoryId +
                ", weightClass=" + weightClass +
                ", warrantyPeriod='" + warrantyPeriod + '\'' +
                ", supplierId=" + supplierId +
                ", productStatus='" + productStatus + '\'' +
                ", listPrice=" + listPrice +
                ", catalogUrl='" + catalogUrl + '\'' +
                ", warehouseId=" + warehouseId +
                ", quantity=" + quantity +
                '}';
    }
}
