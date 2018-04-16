package io.falcon.challenge.dto;

public final class ProductDTOBuilder {
    private String productName;
    private String productDescription;

    private ProductDTOBuilder() {
    }

    public static ProductDTOBuilder aDummyProductDTO() {
        return new ProductDTOBuilder();
    }

    public ProductDTOBuilder productName(String productName) {
        this.productName = productName;
        return this;
    }

    public ProductDTOBuilder productDescription(String productDescription) {
        this.productDescription = productDescription;
        return this;
    }

    public ProductDTO build() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(productName);
        productDTO.setProductDescription(productDescription);
        return productDTO;
    }
}
