package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@Getter
@Data
public class Customer extends Base{
    /*
    customer:
        id, user,
        FullName,
        PhoneNumber,address,
        province, district, ward
     */

    @Column(columnDefinition = "nvarchar(50)")
    private String fullName;
    @Column(columnDefinition = "varchar(12)")
    private String phoneNumber;
    private String address;
    @Column(columnDefinition = "nvarchar(30)")
    private String province;
    @Column(columnDefinition = "nvarchar(30)")
    private String district;
    @Column(columnDefinition = "nvarchar(30)")
    private String ward;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
