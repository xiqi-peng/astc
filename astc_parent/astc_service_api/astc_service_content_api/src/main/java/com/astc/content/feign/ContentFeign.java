package com.astc.content.feign;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/****
 * @Author:传智播客
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name = "content")
@RequestMapping("/content")
public interface ContentFeign {
    @GetMapping("/findContentsByCategoryId/{categoryId}")
    Result findContentsByCategoryId(@PathVariable(value = "categoryId") Long categoryId);
}