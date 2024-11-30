package com.example.ecommerce.system.dal.repository.user;

import com.example.ecommerce.system.dal.dataobject.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
