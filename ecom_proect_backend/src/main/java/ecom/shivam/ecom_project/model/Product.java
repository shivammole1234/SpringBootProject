package ecom.shivam.ecom_project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity  // to create table h2 database
@Data
@NoArgsConstructor
@AllArgsConstructor  // lombok annotation
public class Product {


    @Id  // to add the primary ley in table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal price;
    private int stockQuantity;
    private String description;
    private String brand;
    private String category;

//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private Date releaseDate;
    private boolean productAvailable = true;

    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;


}
