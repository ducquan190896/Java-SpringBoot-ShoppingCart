package com.quan.shoppingcart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.quan.shoppingcart.Entity.Account;
import com.quan.shoppingcart.Entity.Cart;
import com.quan.shoppingcart.Entity.Item;
import com.quan.shoppingcart.Entity.Role;
import com.quan.shoppingcart.Repository.AccountRepos;
import com.quan.shoppingcart.Repository.CartRepos;
import com.quan.shoppingcart.Repository.ItemRepos;
import com.quan.shoppingcart.Repository.OrderRepos;
import com.quan.shoppingcart.Service.OrderService;

@SpringBootApplication
public class ShoppingcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CartRepos cartRepos, AccountRepos accountRepos, ItemRepos itemRepos, OrderService orderService) {
		return args -> {


			Account quan = new Account("quan", new BCryptPasswordEncoder().encode("123456"));
			
			Account Khanh = new Account("khanh",  new BCryptPasswordEncoder().encode("123456"));
			quan.setRole(Role.ADMIN);
			accountRepos.save(quan);
			accountRepos.save(Khanh);

			Cart cart1 = new Cart(quan);
			Cart cart2 = new Cart(Khanh);
			cartRepos.save(cart1);
			cartRepos.save(cart2);

			quan.setCart(cart1);
			Khanh.setCart(cart2);

			accountRepos.save(quan);
			accountRepos.save(Khanh);

			Item shirt = new Item("bamboo", 20, 12, "kalk215");
			Item shirt2 = new Item("pt200", 25, 5, "pt200514");
			itemRepos.save(shirt);
			itemRepos.save(shirt2);

			cart1.addItem(shirt);
			cartRepos.save(cart1);

			cart2.addItem(shirt2);
			cartRepos.save(cart2);
			System.out.println(cart1.getItems());
			System.out.println(cartRepos.findCartByAccount(quan).get());
			System.out.println(cartRepos.findCartByItems(shirt));
			
			// System.out.println(orderService.placeOrderFromCart(quan.getId()));
			
		};
	}
}
