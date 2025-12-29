package com.mycompany.ordersystem.product.service;

import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.services.ProductRepository;
import com.mycompany.ordersystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service("productService")
@Transactional(rollbackFor=Exception.class)
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private TransactionTemplate transactionTemplate;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, TransactionTemplate transactionTemplate) {
        this.productRepository = productRepository;
        this.transactionTemplate = transactionTemplate;
    }
    @Override
    public Product getProduct(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        // Product prod = getProduct(id);
        // if(prod != null)
        //     productRepository.delete(id);
        // 트랜잭션 실습 - 템플릿 경우
        // if (id == 0) {
        //     List<Product> products = productRepository.findAll();
        //     transactionTemplate.execute(
        //             new TransactionCallbackWithoutResult() {
        //
        //                 @Override
        //                 protected void doInTransactionWithoutResult(TransactionStatus status) {
        //                     try {
        //                         for (Product product : products) {
        //                             productRepository.delete(product.getId());
        //                         }
        //                     } catch (Exception ex) {
        //                         System.out.println("삭제를 취소하고 롤백합니다...템플릿");
        //                         status.setRollbackOnly();
        //                     }
        //                 }
        //             }
        //     );
        // } else {
        //     productRepository.delete(id);
        // }
        // 트랜잭션 실습 - AOP XML이나 어노테이션 경우
        try {
            if (id == 0) {
                List<Product> products = productRepository.findAll();
                for (Product product : products) {
                    productRepository.delete(product.getId());
                }
            } else {
                productRepository.delete(id);
            }
        } catch (Exception e) {
            System.out.println("삭제를 취소하고 롤백합니다...AOP");
        }
    }
}
