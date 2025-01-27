package com.example.ecommerce.realtime.service.live;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.service.sku.ProductSkuService;
import com.example.ecommerce.product.service.spu.ProductSpuService;
import com.example.ecommerce.realtime.controller.admin.live.product.vo.LiveProductReqVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveProduct;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveStream;
import com.example.ecommerce.realtime.dal.repo.live.LiveProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.realtime.constants.ErrorCodeConstants.LIVE_PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LiveProductServiceImpl implements LiveProductService{

    private final ProductSpuService productSpuService;
    private final LiveProductRepository liveProductRepository;
    @Override
    public void createLiveProduct(LiveProductReqVO req) {
        LiveProduct liveProduct = LiveProduct.builder()
                .productSpu(ProductSpu.builder().id(req.getProductSpuId()).build())
                .liveStream(LiveStream.builder().id(req.getLivestreamId()).build())
                .pin(false).display(false)
                .build();

        this.liveProductRepository.save(liveProduct);
        //maybe sent to user(through websocket)
    }

    @Override
    public void updateLiveProductDisplay(Long productLiveId, Long liveStreamId) {
        List<LiveProduct> liveProducts = getListLiveProductByLiveStreamId(liveStreamId);

        CollUtils.convertList(liveProducts, l -> {
            if(l.getId().equals(productLiveId)) {
                l.setDisplay(true);
            } else {
                l.setDisplay(false);
            }
            return l;
        });

        this.liveProductRepository.saveAll(liveProducts);


    }

    @Override
    public LiveProduct updatePinLiveProduct(Long productLiveId, Boolean pin) {
        LiveProduct liveProduct = getLiveProductById(productLiveId);
        liveProduct.setPin(pin);
        this.liveProductRepository.save(liveProduct);
        return liveProduct;
    }

    @Override
    public LiveProduct getLiveProductById(Long productLiveId) {
        return this.liveProductRepository.findById(productLiveId)
                .orElseThrow(() -> exception(LIVE_PRODUCT_NOT_FOUND));
    }

    @Override
    public List<LiveProduct> getListLiveProductByLiveStreamId(Long liveStreamId) {
        return this.liveProductRepository.findAllByLiveStreamId(liveStreamId);
    }

    @Override
    public void deleteLiveProduct(Long liveProductId) {
        this.liveProductRepository.deleteById(liveProductId);
    }
}
