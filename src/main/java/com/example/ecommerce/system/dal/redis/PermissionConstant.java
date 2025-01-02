package com.example.ecommerce.system.dal.redis;

public interface PermissionConstant {
    String USER_LIST_ROLE = "user_list_role"; //hash -> user with list roles 1:n
    String MENU_ROLE_LIST = "role_menu_list"; //hash -> role with menu 1:1
    String MENU_LIST_ROLE = "menu_list_role";

}
