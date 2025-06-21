package com.agilesolutions.mongo.model;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("Share")
@AllArgsConstructor
public class Share {

    @Id
    private Long id;
    private String company;
    private int quantity;
    private LocalDate publishDate;

}