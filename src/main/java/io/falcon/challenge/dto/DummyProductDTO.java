package io.falcon.challenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class DummyProductDTO implements Serializable{

    @JsonIgnore
    private Long id;
    private String productName;
    private String productDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "DummyProductDTO{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
