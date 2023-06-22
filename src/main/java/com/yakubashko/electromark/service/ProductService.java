package com.yakubashko.electromark.service;

import com.yakubashko.electromark.model.*;
import com.yakubashko.electromark.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final TypenameRepository typenameRepository;
    private final ClassnameRepository classnameRepository;
    private  final ValueService valueService;
    private final ImageRepository imageRepository;

    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public List<Product> listProductsByClassname(Long classnameId) {
        if (classnameId != null) return productRepository.findProductByClassnameId(classnameId);
        return productRepository.findAll();
    }

    public List<Product> listProductsByTypename(Long typenameId) {
        if (typenameId != null) return productRepository.findProductByTypenameId(typenameId);
        return productRepository.findAll();
    }

    public void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3,
                            Long typeid, Long classid, List<String> value,List<Long> propertyid) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        Typename typename= typenameRepository.getById(typeid);
       product.setTypename(typename);
       Classname classname =classnameRepository.getById(classid);
       product.setClassname(classname);

        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        log.info("Saving new Product. Title: {}; Author email: {}", product.getTitle(), product.getUser().getEmail());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
        for (int i = 0; i < value.size(); i++) {
            valueService.saveValue(value.get(i),propertyid.get(i),product.getId());
        }
        log.info(String.valueOf(product.getId()));
    }

    public void editProduct(Long productid,MultipartFile file1, MultipartFile file2, MultipartFile file3,
                            String title,Integer price, Integer number ,String description
                         ) throws IOException {
        Product product = productRepository.getById(productid);
        if (title != null) {
            product.setTitle(title);
        }
        if (price != null)
        {
            product.setPrice(price);
        }
        if(number!=null)
        {
            product.setNumber(number);
        }
        if(description!=null)
        {
            product.setDescription(description);
        }
        Image image1;
        Image image2;
        Image image3;
        List<Image> images=imageRepository.findByProductId(productid);
        if (file1.getSize() != 0) {
            for (Image image : images) {
                if (image.getName().equals("file1")) {

                    image.setProduct(null);
                }
            }
        }
        if (file2.getSize() != 0) {
            for (Image image : images) {
                if (image.getName().equals("file2")) {
                    image.setProduct(null);
                }
            }
        }
        if (file3.getSize() != 0) {
            for (Image image : images) {
                if (image.getName().equals("file3")) {
                    image.setProduct(null);
                }
            }
        }
        for (Image image : images) {
            if (image.getProduct() == null) {
                imageRepository.delete(image);
            }
        }
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        log.info("Saving new Product. Title: {}; Author email: {}", product.getTitle(), product.getUser().getEmail());
        Product productFromDb = productRepository.save(product);
        List<Image> imageList=imageRepository.findByProductId(productid);
        for (Image image : imageList) {
            if (image.isPreviewImage()) {
                productFromDb.setPreviewImageId(image.getId());
            }
        }
        productRepository.save(product);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }



    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}