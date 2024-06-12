package com.example.ecommerce.utils;

public class EmailUtils {

    public static final String FORGOT_PASSWORD = "Quên Mật Khẩu";
    public static String bodyMessageForgotPassword(String username, String link) {
        String body = "<h2>Tên đăng nhập: " + username + "</h2><br/><p>Để lấy lại mật khẩu vui lòng click vào đường link bên dưới.</p>\n" +
                "<a href=\"" + link + "\">Lấy lại mật khẩu</a>";
        return body;
    }


}
