package com.example.ecommerce.system.dal.repository.user;

import com.example.ecommerce.system.dal.dataobject.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByUserIdAndDefaultAddress(Long userId, Boolean defaultAddress);
}
