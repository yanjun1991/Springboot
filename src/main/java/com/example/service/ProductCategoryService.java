package com.example.service;

import com.example.domain.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductCategoryService  {
    /**
     * 根据Id查询用户信息
     * @param id
     * @return
     */
    ProductCategory findById(Integer id);

    /**
     * 查询所有用户信息
     * @return
     */
     Page<ProductCategory> findAll(Pageable pageable);

    /**
     * 添加或修改用户信息
     * @param productCategory
     */
    ProductCategory save(ProductCategory productCategory);

    /**
     * 根据Id删除用户信息
     * @param id
     */
    void delete(Integer id);
}
