package com.quan.shoppingcart.Service;

import java.util.List;

import com.quan.shoppingcart.Entity.Item;

public interface ItemService {
    List<Item> getItems();
    Item getItem(Long id);
    Item getItemByCode(String productcode);
    void saveItem(Item item);
    void deleteItem(Long id);
    Item updateItem(Long id, Item item);

}
