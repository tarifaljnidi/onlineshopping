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


	/* to update the cart count */
	public String udpateCartLine(int cartLineId, int count) {

		CartLine cartLine = cartLineDAO.get(cartLineId);
		if (cartLine == null) {

			return "result=error";
		} else {
			double oldTotal = cartLine.getTotal();

			Product product = cartLine.getProduct();

			// check if that much quantity is available or not
			if (product.getQuantity() < count) {
				return "result=unavailable";
			}

			// update the cart line
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice() * count);
			cartLineDAO.update(cartLine);

			// update the cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal
					+ cartLine.getTotal());
			cartLineDAO.updateCart(cart);

			return "result=updated";
		}
	}


	public String removeCartLine(int cartLineId) {

		CartLine cartLine = cartLineDAO.get(cartLineId);
		// deduct the cart
		// update the cart
		Cart cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() - 1);
		cartLineDAO.updateCart(cart);

		// remove the cartLine
		cartLineDAO.remove(cartLine);

		return "result=deleted";
	}
	
	public String addCartLine(int productId) {
		Cart cart = this.getCart();
		String response = null;
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(),
				productId);
		if (cartLine == null) {
			// add a new cartLine if a new product is getting added
			cartLine = new CartLine();
			Product product = productDAO.get(productId);
			// transfer the product details to cartLine
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setProductCount(1);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice());

			// insert a new cartLine
			cartLineDAO.add(cartLine);

			// update the cart
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() + 1);
			cartLineDAO.updateCart(cart);
			response = "result=added";
		}
		return response;
	}

}
