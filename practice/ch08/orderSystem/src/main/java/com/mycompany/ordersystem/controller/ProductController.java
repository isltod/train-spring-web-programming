package com.mycompany.ordersystem.controller;

import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/edit")
    public String create(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/edit";
    }

    @GetMapping(path = "/list")
    public String list(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping(path = "/update")
    public String update(Model model, @RequestParam("id")  long id) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping(path = {"/edit", "/update"})
    public String save(Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping(path = "/delete")
    public String delete(Model model, @RequestParam("id")  long id) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product/delete";
    }

    @PostMapping(path = "/delete")
    public String remove(@RequestParam("id") long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
