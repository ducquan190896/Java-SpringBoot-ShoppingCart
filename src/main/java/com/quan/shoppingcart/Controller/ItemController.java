package com.quan.shoppingcart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.shoppingcart.Entity.Item;
import com.quan.shoppingcart.Service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getItems() {
        return new ResponseEntity<List<Item>>(itemService.getItems(), HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        return new ResponseEntity<>(itemService.getItem(id), HttpStatus.OK);
    }
    @GetMapping("/productcode/{productcode}")
    public ResponseEntity<Item> getItemByProductcode(@PathVariable String productcode) {
        return new ResponseEntity<>(itemService.getItemByCode(productcode), HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<HttpStatus> createItem(@RequestBody Item item) {
        itemService.saveItem(item);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @PutMapping("/id/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        return new ResponseEntity<>(itemService.updateItem(id, item), HttpStatus.OK);
    }
}
