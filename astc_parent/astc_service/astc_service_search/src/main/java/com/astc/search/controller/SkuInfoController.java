package com.astc.search.controller;

import com.astc.search.service.SkuInfoService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SkuInfoController
 * @Description
 * @Author 彭茜奇
 * @Date 1:38 2020/5/18
 * @Version 2.1
 **/
@RestController
@RequestMapping("/search")
public class SkuInfoController {
    @Autowired
    private SkuInfoService skuInfoService;

    @GetMapping("/importDate")
    public Result importDate() {
        skuInfoService.importSkuInfoToEs();
        return new Result(true, StatusCode.OK, "导入数据成功");
    }
}
