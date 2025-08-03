package com.shop.ShopApplication.Strategies.Interface;

import com.shop.ShopApplication.Interface.Registerable;

public interface RegisterableStrategy {
    boolean supports(String user);
    Registerable process(Registerable user);
    Registerable save(Registerable user);
}
