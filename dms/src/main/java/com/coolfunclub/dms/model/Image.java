package com.coolfunclub.dms.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Original file name
    private String url;  // URL or file path
    // private String type; 
    // ^^^^^ use that for the content type that the controller returns!
    // i think controller would throw error if we have another type 
    // and we set the content type to jpeg

    // private Long size;   
    // private String title;       
    // private String description; 

}  

