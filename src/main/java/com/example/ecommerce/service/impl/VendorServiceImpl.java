package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.converter.ConversationMapper;
import com.example.ecommerce.domain.VendorFavorite;
import com.example.ecommerce.domain.dto.VendorRequest;
import com.example.ecommerce.domain.dto.chat.ConversationResponse;
import com.example.ecommerce.domain.dto.chat.UserInboxResponse;
import com.example.ecommerce.domain.dto.chat.VendorResponseInbox;
import com.example.ecommerce.domain.dto.UserTrack;
import com.example.ecommerce.domain.dto.VendorDto;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.Vendor;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IVendorService;
import com.example.ecommerce.common.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements IVendorService {
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;


    @Override
    public List<VendorDto> getAll() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> mapToDto(vendor))
                .collect(Collectors.toList());
    }

    public static VendorDto mapToDto(Vendor vendor) {
        VendorDto vendorResponse = SystemUtils.mapper.map(vendor, VendorDto.class)
                .toBuilder()
                .numberOfProduct(vendor.getProducts().size())
                .numberOfUserFavorite(
                        vendor.getVendorFavorite() != null ?
                                vendor.getVendorFavorite().getUsers().size() :
                                0
                )
                .build();
        return vendorResponse;
    }

    @Override
    public void delete(Long id) {
        vendorRepository.deleteById(id);
    }


    @Override
    public VendorDto findById(Long id) {
        return mapToDto(vendorRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                "VendorId",
                                id + ""
                        )
                ));
    }

    @Override
    public VendorDto saveOrUpdate(VendorRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                        .orElseThrow(() -> new NotFoundException(
                                "Username",
                                request.getUsername()
                        ));
        user.setRole(Role.VENDOR);
        Vendor vendor = Vendor.builder()
                .user(user)
                .shopName(request.getShopName())
                .perMoneyDelivery(request.getPerMoneyDelivery())
                .build();
        return mapToDto(vendorRepository.save(vendor));
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
                    .map(conversation -> ConversationMapper.mapperToConversationResponse(conversation, username))
                    .collect(Collectors.toList());
            return VendorResponseInbox.builder()
                    .user(
                            UserInboxResponse.builder()
                                    .conversationResponses(
                                            conversationsConnectedBetweenVendorAndUser.isEmpty()
                                                    ? null
                                                    : conversationsConnectedBetweenVendorAndUser
                                    )
                                    .fullName(vendor.getUser()
                                            .getUserContactDetails() != null
                                            ?
                                            vendor.getUser()
                                                    .getUserContactDetails()
                                                    .getFullName()
                                            : "ANONYMOUS")
                                    .username(vendor.getUser().getUsername())
                                    .active(UserTrack.getInstance().getMap().get(
                                            vendor.getUser().getUsername() != null
                                    ))
                                    .image(vendor.getUser().defaultImage())
                                    .build()
                    )
                    .shopName(vendor.getShopName())
                    .id(vendor.getId())
                    .build();
        }).toList();
        return vendorResponses;
    }

    @Override
    @Transactional
    public void followUser(Long vendorId) {
        User user = userRepository.findByUsername(
                SecurityUtils.username()
        ).orElseThrow();
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new NotFoundException(
                        "VendorId",
                        vendorId.toString()
                ));
        if(vendor.getVendorFavorite() == null) {
            vendor.setVendorFavorite(new VendorFavorite());
        }
        vendor.getVendorFavorite().addUser(user);
        vendorRepository.save(vendor);
    }
}
