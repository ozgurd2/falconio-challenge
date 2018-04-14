package io.falcon.challenge.dto;

public final class DummyProductDTOBuilder {
    private Long id;
    private String productName;
    private String productDescription;

    private DummyProductDTOBuilder() {
    }

    public static DummyProductDTOBuilder aDummyProductDTO() {
        return new DummyProductDTOBuilder();
    }

    public DummyProductDTOBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public DummyProductDTOBuilder productName(String productName) {
        this.productName = productName;
        return this;
    }

    public DummyProductDTOBuilder productDescription(String productDescription) {
        this.productDescription = productDescription;
        return this;
    }

    public DummyProductDTO build() {
        DummyProductDTO dummyProductDTO = new DummyProductDTO();
        dummyProductDTO.setId(id);
        dummyProductDTO.setProductName(productName);
        dummyProductDTO.setProductDescription(productDescription);
        return dummyProductDTO;
    }
}
