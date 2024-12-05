package com.example.ecommerce.product.service.spu.notify;

public interface ProductObserver {

    /**
     * Phuong thuc thong bao:
     * 1, Qua he thong(tren web)
     * 2, Qua email
     */

    /**
     * Khi product sku het hang(quantity = 0) va khi seller
     * update quantity, he thong se tu dong thong bao
     * toi cac user da thich(like) san pham nay
     */
    void onProductSkuStockUpdate();

    /**
     * Khi seller them 1 san pham moi, thi se tu dong thong
     * bao toi tat ca customer da thich(like) seller
     */
    void onNewProductSpuWasAdded();

    /**
     * Thong bao toi customer chi tiet giao hang(chua lam)
     */
    void onOrderDelivery();

    /**
     * Thong bao customer da dat hang thanh cong,
     * Thong bao toi seller la co customer da dat hang, vui long check
     */
    void onCreatedOrder();
}
