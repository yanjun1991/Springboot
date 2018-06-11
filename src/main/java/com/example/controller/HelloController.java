package com.example.controller;

import com.example.dao.ProductCategoryRepository;
import com.example.domain.ProductCategory;
import com.example.service.ProductCategoryService;
import com.example.util.page.PageableTools;
import com.example.util.page.SortDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class HelloController {
    @Autowired
    private ProductCategoryService productCategoryService;
    private static Integer userNum=0;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @RequestMapping("/categorylist")
    public String index(Model model, @RequestParam(value = "start", defaultValue = "0") Integer start, @RequestParam(value = "limit", defaultValue = "2") Integer limit){
        Pageable pageable = PageableTools.basicPage(start, limit, new SortDto("ASC", "id"));
        Page<ProductCategory> page = productCategoryService.findAll(pageable);
        model.addAttribute("page",page);
        return "welcome";
    }
    @RequestMapping("/query/{id}")
    @ResponseBody
    public Object query(@PathVariable int id) {
        return productCategoryService.findById(id);
    }
    @RequestMapping("/add")
    public String add(){
        ProductCategory productCategory = new ProductCategory();
        ++userNum;
        productCategory.setCategoryName("洗衣液"+userNum);
        productCategory.setCategoryType(userNum);
        productCategoryService.save(productCategory);
        return "redirect:/product/categorylist";
    }
    @RequestMapping("{id}/delete")
    public String delete(@PathVariable("id") Integer id){
        productCategoryService.delete(id);
        return "redirect:/product/categorylist";
    }
}
