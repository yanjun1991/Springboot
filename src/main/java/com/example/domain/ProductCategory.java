package com.example.domain;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;

/**
 * 这是商品类目的实体类
 */
@Entity
@Data
public class ProductCategory implements Serializable {
    /**
     * 类别id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类别名称
     */
    @Column(name = "category_name")
    private String categoryName;

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                '}';
    }

    /**
     * 类别编号
     */
    @Column(name = "category_type")
    private Integer categoryType;

    public ProductCategory() {
    }


    public ProductCategory(String json) throws IOException {
        ProductCategory productCategory = new ObjectMapper().readValue(json, ProductCategory.class);
        this.id = productCategory.id;
        this.categoryName = productCategory.categoryName;
        this.categoryType = productCategory.categoryType;
    }
}
