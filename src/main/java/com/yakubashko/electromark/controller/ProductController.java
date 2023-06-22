package com.yakubashko.electromark.controller;

import com.yakubashko.electromark.model.*;
import com.yakubashko.electromark.repositories.ProductRepository;
import com.yakubashko.electromark.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final TypenameService typenameService;
    private final ClassnameService classnameService;
    private final PropertyService propertyService;
    private final ValueService valueService;
    private final BasketService basketService;


    @GetMapping("/")
    public String products(@RequestParam(name = "searchWord", required = false) String title,
                           @RequestParam(name = "searchClassId", required = false) Long classnameId,
                           @RequestParam(name = "searchTypenameId", required = false) Long typenameId,
                           Principal principal, Model model) {
        if(title!=null) {
            model.addAttribute("products", productService.listProducts(title));
        }else { if(classnameId!=null) {
            model.addAttribute("products", productService.listProductsByClassname(classnameId));
        }else
            model.addAttribute("products", productService.listProductsByTypename(typenameId));
        }
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        model.addAttribute("typename", typenameService.list());
        model.addAttribute("classname", classnameService.list());
        return "products";
    }

    @GetMapping("/product/basket/{id}")
    public String productSell(@PathVariable Long id) {
        Product product=productRepository.getById(id);
        if(product.getNumber() > 0) {
            product.setNumber(product.getNumber() - 1);
        }
        productRepository.save(product);
        return "redirect:/product/{id}";
    }
    @GetMapping("/product/edit/{id}")
    public String productIEdit(@PathVariable Long id, Model model, Principal principal) {
        Product product = productService.getProductById(id);
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        model.addAttribute("authorProduct", product.getUser());
        model.addAttribute("typename", typenameService.list());
        model.addAttribute("classname", classnameService.list());
        model.addAttribute("property", propertyService.list());
        return "product-edit";
    }

    @GetMapping("/product/add")
    public String productAdd( Model model, Principal principal) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("typename", typenameService.list());
        model.addAttribute("classname", classnameService.list());
        model.addAttribute("property", propertyService.list());
        return "product-add";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal) {
        Product product = productService.getProductById(id);
        Classname classname=classnameService.listClassnameByProduct(id);
        List<Property> properties=propertyService.listByClassname(classname.getId());
        List<Value> values =new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            Value value = valueService.listByProductAndProperty(id,properties.get(i).getId());
            values.add(value);
        }
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        model.addAttribute("authorProduct", product.getUser());
        model.addAttribute("classname", classname);
        model.addAttribute("property", properties);
        model.addAttribute("value",values);
        return "product-info";
    }

    @PostMapping("/product/add")
    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, @RequestParam("typename") Long typename,
                                @RequestParam("classname") Long classname,  @RequestParam("value") List<String> value,
                                @RequestParam("property") List<Long> propertyid,
                                Product product, Principal principal) throws IOException {



        productService.saveProduct(principal, product, file1, file2, file3,typename,classname,value,propertyid);
        return "redirect:/my/products";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id,@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                              @RequestParam("file3") MultipartFile file3, @RequestParam String title,
                              @RequestParam Integer price, @RequestParam String description,
                              @RequestParam Integer number) throws IOException {
      productService.editProduct(id,file1,file2,file3,title,price,number,description);
        return "redirect:/my/products";
    }

    @PostMapping("/product/basket/{id}")
    public String addBasketProduct(@PathVariable Long id,Principal principal) {
        User user = productService.getUserByPrincipal(principal);
        basketService.saveProductinBasket(id,user.getId());
        return "redirect:/product/{id}";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        basketService.deleteBasket(id);
        productService.deleteProduct(id);
        return "redirect:/my/products";
    }
    @GetMapping("/product/property")
    public String productProperty(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        model.addAttribute("typename", typenameService.list());
        model.addAttribute("classname", classnameService.list());
        return "product-property";
    }
    @GetMapping("/my/products")
    public String userProducts(@RequestParam(name = "searchWord", required = false) String title,
                               @RequestParam(name = "searchClassId", required = false) Long classnameId,
                               @RequestParam(name = "searchTypenameId", required = false) Long typenameId,
                               Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        if(title!=null)
        {
            model.addAttribute("products", productService.listProducts(title));
        }else {
            if(classnameId!=null) {

                model.addAttribute("products", productService.listProductsByClassname(classnameId));

            }else {
                if(typenameId!=null)
                {
                    model.addAttribute("products", productService.listProductsByTypename(typenameId));
                }
                else {

                    model.addAttribute("products", user.getProducts());
                }
            }

            }
        model.addAttribute("typename", typenameService.list());
        model.addAttribute("classname", classnameService.list());
        model.addAttribute("property", propertyService.list());
        return "my-products";
    }

    @PostMapping("/typename")
    public String creatTypename(Typename typename) {
        typenameService.saveTypename(typename);
        return "redirect:/product/property";
    }

    @PostMapping("/classname")
    public String creatClassname(Classname classname,@RequestParam("typename") Long typename) {
        classnameService.saveClassname(classname,typename);
        return "redirect:/product/property";
    }

    @PostMapping("/property")
    public String creatTypename(Property property,@RequestParam("classname") Long classname) {
        propertyService.saveProperty(property,classname);
        return "redirect:/product/property";
    }
}
