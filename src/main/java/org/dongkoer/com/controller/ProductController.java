package org.dongkoer.com.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.dongkoer.com.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@Tag(name = "产品控制器",description = "产品restful 接口")
public class ProductController {
    private static HashMap<Integer, Product> productMap = new HashMap<>();

    static {
        productMap.put(1, new Product(1, "小兔子手环", "张万森的小兔子手环", 45.00));
        productMap.put(2, new Product(2, "小米su7", "雷军造的第一款汽车", 430000.00));
        productMap.put(3, new Product(3, "草莓熊", "迪士尼乐园正版", 145.00));
        productMap.put(4, new Product(4, "魔法日记本", "张万森的魔法日记", 14.00));
    }

    @GetMapping
    @Operation(summary = "获取所有产品")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> list = new ArrayList<>(productMap.values());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    @Operation(summary = "根据id获取指定商品")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        return ResponseEntity.ok(productMap.get(id));
    }
    @PostMapping
    @Operation(summary = "添加商品")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        productMap.put(product.getId(),product);
        return ResponseEntity.ok(productMap.get(product.getId()));
    }

}
