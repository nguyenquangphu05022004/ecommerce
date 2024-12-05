package com.example.ecommerce.product.service.sort.price;

import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;

class Common {
    /**
     *
     * @param s1: spu1
     * @param s2: spu2
     * @param type: asc: 0, desc:1
     * @return: Integer
     */
    public static int compareTo(ProductSpu s1, ProductSpu s2, int type) {
        int cmp = 0;

        if(type == 1) return cmp * -1;
        return cmp;
    }
}
