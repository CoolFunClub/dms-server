package com.coolfunclub.dms.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor  
@Entity
@Setter
@Getter
@ToString
public class Account {
    @Column(name = "openDate")
    private Date openDate;
    @Column(name = "closeDate")
    private Date closeDate;
    @Column(name = "status")
    private String status;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

      
}
