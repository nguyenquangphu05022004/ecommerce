package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.enums.Role;
import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.Vendor;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IVendorService;
import com.example.ecommerce.service.dto.VendorDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.VendorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements IVendorService {
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    @Qualifier("vendorMapper")
    private final IMapper<Vendor, VendorRequest, VendorDto> mapper;



    @Override
    public void saveOrUpdate(VendorRequest request) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getUsername(), "username");
        ValidationUtils.fieldCheckNullOrEmpty(request.getShopName(), "shopName");
        ValidationUtils.fieldCheckNullOrEmpty(request.getPerMoneyDelivery(), "perMoneyDelivery");

        User user = userRepository.findByUsernameIgnoreCase(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Username: %s not found", request.getUsername())
                ));
        user.setRole(Role.VENDOR);

        Vendor vendor =  mapper.toEntity(request).toBuilder()
                .user(user)
                .build();

        vendorRepository.save(vendor);
    }

//    @Override
//    public List<VendorResponseInbox> findAllByName(String vendorName) {
//        String username = SecurityUtils.username();
//        System.out.println("Username: " + username);
//        List<Vendor> vendors = vendorRepository
//                .findAllByVendorNameAndNotMySelf(vendorName, username);
//        List<VendorResponseInbox> vendorResponses = vendors.stream().map(vendor -> {
//            List<ConversationResponse> conversationsConnectedBetweenVendorAndUser
//                    = vendor.getUser()
//                    .getConversations()
//                    .stream()
//                    .filter(conversation -> conversation.getUsers()
//                            .stream()
//                            .anyMatch(user -> user.getUsername().equals(username)))
//                    .map(conversation -> ConversationMapper.mapperToConversationResponse(conversation, username))
//                    .collect(Collectors.toList());
//            return VendorResponseInbox.builder()
//                    .user(
//                            UserInboxResponse.builder()
//                                    .conversationResponses(
//                                            conversationsConnectedBetweenVendorAndUser.isEmpty()
//                                                    ? null
//                                                    : conversationsConnectedBetweenVendorAndUser
//                                    )
//                                    .fullName(vendor.getUser()
//                                            .getUserContactDetails() != null
//                                            ?
//                                            vendor.getUser()
//                                                    .getUserContactDetails()
//                                                    .getFullName()
//                                            : "ANONYMOUS")
//                                    .username(vendor.getUser().getUsername())
//                                    .active(UserTrack.getInstance().getMap().get(
//                                            vendor.getUser().getUsername() != null
//                                    ))
//                                    .build()
//                    )
//                    .shopName(vendor.getShopName())
//                    .id(vendor.getId())
//                    .build();
//        }).toList();
//        return vendorResponses;
//    }

    @Override
    @Transactional
    public void userFollow(Long vendorId) {
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.username())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Username: %s not found", SecurityUtils.username())
                ));

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new GeneralException(
                        String.format("Vendor with id %s not found", vendorId)
                ));

        if(vendor.getUser().equals(user)) {
            throw new GeneralException(
                    String.format("You can't follow myself")
            );
        }

        vendor.getUsers().add(user);
        vendorRepository.save(vendor);

    }
}
