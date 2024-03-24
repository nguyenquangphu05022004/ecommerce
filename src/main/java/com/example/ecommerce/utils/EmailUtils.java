package com.example.ecommerce.utils;

public class EmailUtils {

    public static final String FORGOT_PASSWORD = "Quên Mật Khẩu";
    public static String bodyMessageForgotPassword(String link) {
        String body = "<p>Để lấy lại mật khẩu vui lòng click vào đường link bên dưới.</p>\n" +
                "<a href=\"" + link + "\">Lấy lại mật khẩu</a>";
        return body;
    }


}
