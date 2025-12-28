package com.mycompany.ordersystem.controller;

import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private MessageSource messageSource;
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
        return "pdfProductReport";
    }

    @GetMapping(path = "/update")
    public String update(Model model, @RequestParam("id")  long id) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping(path = {"/edit", "/update"})
    public String save(
            Product product, BindingResult result,
            // 이게 실제 파일
            @RequestParam(name = "image", required = false) MultipartFile image,
            // 이건 경로를 위해 사용한다고?
            HttpServletRequest request,
            Locale locale
    ) {
        if (result.hasErrors()) {
            // 일단 기본적으로 에러 뜨면 제자리로 돌려보내고
            System.out.println("result.hasErrors()----------------");
            return "product/edit";
        }
        try {
            if (!image.isEmpty()) {
                String rootPath = request.getSession().getServletContext().getRealPath("/");
                String filePath = rootPath + image.getOriginalFilename();
                System.out.println("file is saved in -------------------------- " + filePath);
                // 이게 실제로 저장하는 코드구만...
                image.transferTo(Paths.get(filePath));
            } else {
                System.out.println("image is empty-------------------");
            }
        } catch (IOException e) {
            // 저장 과정에서 문제가 있어도 다 취소하고 돌려보내기
            System.out.println("IOException---------------------");
            result.reject(e.getMessage());
            return "product/edit";
        }
        String message = messageSource.getMessage("product.info",
                new Object[] {product.getName(), product.getDescription(), product.getPrice()},
                locale);
        System.out.println(message);
        productService.saveProduct(product);
        return "product/register";
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
