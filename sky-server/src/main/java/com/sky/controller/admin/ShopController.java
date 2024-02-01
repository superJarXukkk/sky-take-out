package com.sky.controller.admin;


import com.sky.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shop")
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(KEY, status);
        return Result.success();
    }

    @GetMapping("/status")
    public Result getStatus(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get(KEY);
        return Result.success(o);
    }
}
