package net.tarif.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tarif.onlineshopping.model.UserModel;
import net.tarif.shoppingbackend.dao.CartLineDAO;
import net.tarif.shoppingbackend.dao.ProductDAO;
import net.tarif.shoppingbackend.dao.UserDAO;
import net.tarif.shoppingbackend.dto.Cart;
import net.tarif.shoppingbackend.dto.CartLine;
import net.tarif.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;
		
	@Autowired
	private HttpSession session;
	
	public List<CartLine> getCartLines() {

		return cartLineDAO.list(getCart().getId());

	}

	
	private Cart getCart() {
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}


	



}
