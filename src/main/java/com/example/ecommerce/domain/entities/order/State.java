package com.example.ecommerce.domain.entities.order;


public interface State<Context, Message> {
   Message next(Context context);
   Message prev(Context context);
   Message status();
}
