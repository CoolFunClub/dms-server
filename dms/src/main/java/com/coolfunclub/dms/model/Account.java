package com.coolfunclub.dms.model;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor  
// @Entity
@Setter
@Getter
public class Account {

    private Date mOpenDate;
    private Date mCloseDate;
    private String mStatus;
    
    // @Id
    // @OneToOne
    // @JoinColumn(name = "mPersonID")
    // private Person person;  
}