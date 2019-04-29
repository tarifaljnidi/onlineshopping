package net.tarif.shoppingbackend.dao;
import java.util.List;

import net.tarif.shoppingbackend.dto.Address;
import net.tarif.shoppingbackend.dto.Cart;
import net.tarif.shoppingbackend.dto.User;

public interface UserDAO {

	// user related operation
	User getByEmail(String email);


	boolean addUser(User user);
	
	// adding and updating a new address
	boolean addAddress(Address address);
	
	//  updating a cart
	boolean updateCart(Cart cart);
	
	Address getBillingAddress(User user);
	List<Address> listShippingAddresses(User user);

	
}
