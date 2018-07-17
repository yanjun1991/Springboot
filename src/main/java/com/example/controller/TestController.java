package com.example.controller;

import com.example.domain.ProductCategory;
import com.example.service.ProductCategoryService;
import com.example.util.page.PageableTools;
import com.example.util.page.SortDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Api("swaggerTestController相关api")
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ProductCategoryService productCategoryService;
    private static Integer userNum=0;

    @ApiOperation(value="获取商品列表", notes="获取商品列表")
    @RequestMapping(value = "/categorylist",method = RequestMethod.GET)
    public String index(Model model, @RequestParam(value = "start", defaultValue = "0") Integer start, @RequestParam(value = "limit", defaultValue = "5") Integer limit){
        Pageable pageable = PageableTools.basicPage(start, limit, new SortDto("ASC", "id"));
        Page<ProductCategory> page = productCategoryService.findAll(pageable);
        model.addAttribute("page",page);
        return "welcome";
    }
    @ApiOperation(value="查询某个商品", notes="查询某个商品")
    @RequestMapping(value="/query/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object query(@PathVariable int id) {
        return productCategoryService.findById(id);
    }

    @ApiOperation(value="新增商品", notes="新增商品")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(){
        ProductCategory productCategory = new ProductCategory();
        ++userNum;
        productCategory.setCategoryName("洗衣液"+userNum);
        productCategory.setCategoryType(userNum);
        productCategoryService.save(productCategory);
        return "redirect:/product/categorylist";
    }

    @ApiOperation(value="删除商品", notes="根据url的id来指定删除商品")
    @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value="{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        productCategoryService.delete(id);
        return "redirect:/product/categorylist";
    }
}
