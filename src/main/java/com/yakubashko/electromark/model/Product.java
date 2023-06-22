package com.yakubashko.electromark.model;


import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

    @Entity
    @Table(name = "products")
    @Data
    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;

        private String description;


        private Integer price;

        private Integer number;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
                mappedBy = "product")
        private List<Image> images = new ArrayList<>();

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
                mappedBy = "product")
        private List<Value> values = new ArrayList<>();

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
                mappedBy = "product")
        private List<Basket> baskets = new ArrayList<>();




        @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
        @JoinColumn
        private User user;

        @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
        private Classname classname;

        @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
        private Typename typename;

        private Long previewImageId;

        private LocalDateTime dateOfCreated;

        @PrePersist
        private void onCreate() { dateOfCreated = LocalDateTime.now(); }


        public void addImageToProduct(Image image) {
            image.setProduct(this);
            images.add(image);
        }
    }