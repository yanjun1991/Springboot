package com.example.dao;

import com.example.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    /**
     * 通过类别编号查询
     * @param categoryType
     * @return
     */
    ProductCategory findByCategoryType(Integer categoryType);

    /**
     * 通过类别id查询
     * @param id
     * @return
     */
    ProductCategory findById(Integer id);

    /**
     * 通过类别编号范围得到列表
     * @param categoryTypes
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);
}
