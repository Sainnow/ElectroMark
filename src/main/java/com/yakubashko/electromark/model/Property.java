package com.yakubashko.electromark.model;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "property")
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Classname classname;


}
