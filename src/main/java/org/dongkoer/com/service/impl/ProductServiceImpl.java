package org.dongkoer.com.service.impl;

import org.dongkoer.com.entity.Product;
import org.dongkoer.com.mapper.ProductMapper;
import org.dongkoer.com.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dongkoer
 * @since 2025-01-03
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
