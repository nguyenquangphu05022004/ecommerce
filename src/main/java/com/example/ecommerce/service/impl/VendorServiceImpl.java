package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.response.ConversationResponse;
import com.example.ecommerce.domain.response.UserInboxResponse;
import com.example.ecommerce.domain.response.vendor.VendorResponseInbox;
import com.example.ecommerce.dto.VendorDto;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.Vendor;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IGenericService;
import com.example.ecommerce.service.IVendorService;
import com.example.ecommerce.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements IGenericService<VendorDto>, IVendorService {
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository,
                             UserRepository userRepository) {
        this.vendorRepository = vendorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<VendorDto> records() {
        return GenericService.records(vendorRepository, Convert.VEND);
    }

    @Override
    public void delete(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return vendorRepository.count();
    }

    @Override
    public VendorDto findById(Long id) {
        return (VendorDto) Convert.VEND.toDto(
                GenericService.findById(vendorRepository, id)
        );
    }

    @Override
    public VendorDto saveOrUpdate(VendorDto vendorDto) {
        User user = userRepository.findById(vendorDto.getUser().getId()).get();
        user.setRole(vendorDto.getUser().getRole());
        Vendor vendor = new Vendor();
        vendor.setUser(user);
        vendor.setShopName(vendorDto.getShopName());
        return (VendorDto) Convert.VEND.toDto(vendorRepository.save(vendor));
    }

    @Override
    public List<VendorResponseInbox> findAllByName(String vendorName) {
        String username = SecurityUtils.username();
        System.out.println("Username: " + username);
        List<Vendor> vendors = vendorRepository
                .findAllByVendorNameAndNotMySelf(vendorName, username);
        List<VendorResponseInbox> vendorResponses = vendors.stream().map(vendor -> {
            List<ConversationResponse> conversationsConnectedBetweenVendorAndUser
                    = vendor.getUser()
                    .getConversations()
                    .stream()
                    .filter(conversation -> conversation.getUsers()
                            .stream()
                            .anyMatch(user -> user.getUsername().equals(username)))
                    .map(conversation -> {
                        return ConversationResponse.builder()
                                .conversationName(
                                        conversation.getConversationName(username)
                                )
                                .id(conversation.getId()).build();
                    })
                    .collect(Collectors.toList());
            return VendorResponseInbox.builder()
                    .username(vendor.getUser().getUsername())
                    .shopName(vendor.getShopName())
                    .id(vendor.getId())
                    .conversationResponses(
                            conversationsConnectedBetweenVendorAndUser.isEmpty() ?
                                    null :
                                    conversationsConnectedBetweenVendorAndUser
                    ).build();
        }).toList();
        return vendorResponses;
    }
}
