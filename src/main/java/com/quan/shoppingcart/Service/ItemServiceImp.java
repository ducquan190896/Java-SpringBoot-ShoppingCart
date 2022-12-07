package com.quan.shoppingcart.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.shoppingcart.Entity.Cart;
import com.quan.shoppingcart.Entity.Item;
import com.quan.shoppingcart.Exception.EntityNotFoundException;
import com.quan.shoppingcart.Repository.CartRepos;
import com.quan.shoppingcart.Repository.ItemRepos;

@Service
public class ItemServiceImp implements ItemService{
    @Autowired
    ItemRepos itemRepos;
    @Autowired
    CartRepos cartRepos;

    @Override
    public void deleteItem(Long id) {
        Optional<Item> entity = itemRepos.findById(id);
        Item item = checkItem(entity, id);
        List<Cart> carts = cartRepos.findCartByItems(item);
        for(Cart cart : carts) {
            cart.getItems().remove(item);
        }
        itemRepos.delete(item);
        
    }

    @Override
    public List<Item> getItems() {
        return itemRepos.findAll();
    }

    @Override
    public Item getItem(Long id) {
       Optional<Item> entity = itemRepos.findById(id);
       Item item = checkItem(entity, id);
       return item;
    }

    @Override
    public Item getItemByCode(String productcode) {
        Optional<Item> entity = itemRepos.findItemByProductcode(productcode);
       Item item = checkItem(entity, 404L);
       return item;
    }

    @Override
    public void saveItem(Item item) {
        Optional<Item> entity = itemRepos.findItemByProductcode(item.getProductcode());
        if(entity.isPresent()) {
            throw new EntityNotFoundException("the item with product code " + item.getProductcode() + " already exist");
        }
        itemRepos.save(item);
    }

    @Override
    public Item updateItem(Long id, Item item) {
        Optional<Item> entity1 = itemRepos.findById(id);
        Item item1 = checkItem(entity1, id);
        item1.setName(item.getName());
        item1.setQuantity(item.getQuantity());
        item1.setPrice(item.getPrice());
        Optional<Item> entity = itemRepos.findItemByProductcode(item.getProductcode());
        if(entity.isPresent()) {
            throw new EntityNotFoundException("the item with product code " + item.getProductcode() + " already exist");
        }
        item1.setProductcode(item.getProductcode());
        return itemRepos.save(item1);
    }

    private Item checkItem(Optional<Item> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the item with id " + id + " not found");
    }
    
}
