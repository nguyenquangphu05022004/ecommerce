package com.example.ecommerce.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vendor_favorites")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendorFavorite extends BaseEntity implements Mediator{

    @OneToMany(cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();
    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }
}
