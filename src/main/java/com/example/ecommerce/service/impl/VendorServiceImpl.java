package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.converter.ConversationMapper;
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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements IVendorService {
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;


    @Override
    public List<VendorDto> getAll() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> mapToDto(vendor))
                .collect(Collectors.toList());
    }

    private VendorDto mapToDto(Vendor vendor) {
        vendor.setUser(null);
        vendor.setProducts(null);
        return mapper.map(vendor, VendorDto.class);
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
    public VendorDto saveOrUpdate(VendorDto vendorDto) {
        User user = userRepository.findById(vendorDto.getUser().getId()).get();
        user.setRole(vendorDto.getUser().getRole());
        Vendor vendor = new Vendor();
        vendor.setUser(user);
        vendor.setShopName(vendorDto.getShopName());
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
}
