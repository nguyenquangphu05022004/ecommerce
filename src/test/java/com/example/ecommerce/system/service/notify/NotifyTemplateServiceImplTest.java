package com.example.ecommerce.system.service.notify;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.common.string.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NotifyTemplateServiceImplTest extends TestBase {


    @Test
    void test_notify() {
        String orderNotifyTemplate = "<div className=\"d-flex align-items-center mb-3\">\n" +
                "                                            <div className=\"col-2\">\n" +
                "                                                <img src=\"https://www.shutterstock.com/image-vector/shopping-cart-check-mark-icon-600nw-1708233319.jpg\" height={\"70px\"} width={\"65px\"}></img>\n" +
                "                                            </div>\n" +
                "                                            <div className=\"d-flex flex-column col-10\">\n" +
                "                                                <div className=\"mb-4 d d-flex\">\n" +
                "                                                    <div> <b>Bạn </b> đã đặt hàng thanh công với các sản phẩm: <b>{{productNames}}</b>, với  <b>mã đơn hàng: </b>{{orderId}}</div>\n" +
                "                                                </div>                                                <div className=\"d-flex justify-content-around align-items-center\">\n" +
                "                                                    <div><span className=\"text-mute\">{{timeLine}} trước</span></div>\n" +
                "                                                    <a href=\"/my-orders/{{orderId}}\">Xem đơn hàng</a>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "                                        <hr />";

        Map<String, Object> map = new HashMap<>();
        map.put("productNames", "Ao liverpool");
        map.put("orderId", 2);
        map.put("timeLine", "15 phút");
        String result = StringUtils.formatContent(orderNotifyTemplate, map);
        System.out.println(result);
    }

}