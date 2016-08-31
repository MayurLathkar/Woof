package com.example.dell.woof.model;

import com.example.dell.woof.interfaces.Saleable;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Saleable, Serializable {
    private static final long serialVersionUID = -4073256626483275668L;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemMrpAmt() {
        return itemMrpAmt;
    }

    public void setItemMrpAmt(String itemMrpAmt) {
        this.itemMrpAmt = itemMrpAmt;
    }

    public String getItemImgUrl() {
        return itemImgUrl;
    }

    public void setItemImgUrl(String itemImgUrl) {
        this.itemImgUrl = itemImgUrl;
    }

    public String getItemDiscountPercentage() {
        return itemDiscountPercentage;
    }

    public void setItemDiscountPercentage(String itemDiscountPercentage) {
        this.itemDiscountPercentage = itemDiscountPercentage;
    }

    public BigDecimal getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(BigDecimal itemAmount) {
        this.itemAmount = itemAmount;
    }

    private int itemId;
    private String itemTitle, itemDescription, itemMrpAmt, itemImgUrl, itemDiscountPercentage;
    private BigDecimal itemAmount;

    public Product() {
        super();
    }

    public Product(int itemId, BigDecimal itemAmount, String itemTitle, String itemDescription, String itemMrpAmt, String itemImgUrl, String itemDiscountPercentage) {
        setItemId(itemId);
        setItemTitle(itemTitle);
        setItemAmount(itemAmount);
        setItemDescription(itemDescription);
        setItemImgUrl(itemImgUrl);
        setItemMrpAmt(itemMrpAmt);
        setItemDiscountPercentage(itemDiscountPercentage);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Product)) return false;
        return (this.itemId == ((Product) o).getItemId());
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + itemId;
        hash = hash * prime + (itemTitle == null ? 0 : itemTitle.hashCode());
        hash = hash * prime + (itemAmount == null ? 0 : itemAmount.hashCode());
        hash = hash * prime + (itemDescription == null ? 0 : itemDescription.hashCode());
        return hash;
    }

    @Override
    public BigDecimal getPrice() {
        return itemAmount;
    }

    @Override
    public String getName() {
        return itemTitle;
    }

}
