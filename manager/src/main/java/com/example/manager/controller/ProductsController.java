package com.example.manager.controller;

import com.example.manager.controller.payload.NewProductPayload;
import com.example.manager.entity.Product;
import com.example.manager.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {
    private final ProductService productService;

    @GetMapping( "")
    public String getProductsList(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        return "catalogue/products/product_list";
    }

    @GetMapping("create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(@Valid NewProductPayload payload, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "catalogue/products/new_product";
        }

        String title = payload.title();
        String details = payload.details();
        Product product = this.productService.createProduct(title, details);
        return "redirect:/catalogue/products/"+product.getId();
    }
}
