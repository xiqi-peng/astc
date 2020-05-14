package com.astc.shangpin.feign;

import com.astc.shangpin.pojo.Sku;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "shangpin")
@RequestMapping("/sku")
public interface skuFeign {
    @GetMapping("/findSkuByStatus/{status}")
    Result<List<Sku>> findSkuByStatus(@PathVariable(value = "status") String status);
}
