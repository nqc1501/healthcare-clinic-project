package com.example.healthcareservice.model.item;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@MappedSuperclass
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal price;
    private String unit;
    private Date expiryDate; // Hạn sử dụng
    private Date manufacturingDate; // Ngày sản xuất
    private String usageInstruction;
    private String description;
}
