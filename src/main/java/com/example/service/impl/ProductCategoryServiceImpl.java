package com.example.service.impl;

import com.example.dao.ProductCategoryRepository;
import com.example.domain.ProductCategory;
import com.example.service.ProductCategoryService;
import com.example.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "productCategory")
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    /**
     * 根据Id查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(key ="'product_'+#p0")
    public ProductCategory findById(Integer id) {
         return productCategoryRepository.findOne(id);
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public Page<ProductCategory> findAll(Pageable pageable) {
        return productCategoryRepository.findAll(pageable);
    }

    /**
     * 添加或修改用户信息
     *
     * @param productCategory
     */
    @Override
    @CachePut(key = "'product_'+#p0.id")
    public ProductCategory save(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
        return productCategory;

    }

    /**
     * 根据Id删除用户信息
     *
     * @param id
     */
    @Override
    @CacheEvict(key ="'product_'+#p0")
    public void delete(Integer id) {
        productCategoryRepository.delete(id);
    }

}
