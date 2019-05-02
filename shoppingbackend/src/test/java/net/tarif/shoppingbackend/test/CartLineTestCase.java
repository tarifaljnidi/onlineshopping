package net.tarif.shoppingbackend.test;

import static org.junit.Assert.assertEquals;
import net.tarif.shoppingbackend.dao.CartLineDAO;
import net.tarif.shoppingbackend.dao.ProductDAO;
import net.tarif.shoppingbackend.dao.UserDAO;
import net.tarif.shoppingbackend.dto.Cart;
import net.tarif.shoppingbackend.dto.CartLine;
import net.tarif.shoppingbackend.dto.Product;
import net.tarif.shoppingbackend.dto.User;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CartLineTestCase {

	

	private static AnnotationConfigApplicationContext context;
	
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	
	private CartLine cartLine = null;
	private Cart cart = null;
	private User user = null;
	private Product product = null;
	
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.tarif.shoppingbackend");
		context.refresh();
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	
	@Test
	public void testAddCartLine() {
		
		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("b@b");		
		Cart cart = user.getCart();
		
		// fetch the product 
		Product product = productDAO.get(2);
		
		// Create a new CartLine
		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount()+1);
		cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());
		
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		assertEquals("Failed to add the CartLine!",true, cartLineDAO.add(cartLine));	
		
	

		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal() );
		
		cart.setCartLines(cart.getCartLines() + 1);
		
		assertEquals("Failed to update the cart!",true, cartLineDAO.updateCart(cart));
		
	}
	
	
	/*	
	@Test
	public void testUpdateCartLine() {

		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("absr@gmail.com");		
		Cart cart = user.getCart();
				
		cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), 2);
		
		cartLine.setProductCount(cartLine.getProductCount() + 1);
				
		double oldTotal = cartLine.getTotal();
				
		cartLine.setTotal(cartLine.getProduct().getUnitPrice() * cartLine.getProductCount());
		
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
		
		assertEquals("Failed to update the CartLine!",true, cartLineDAO.update(cartLine));	

		
	}
	
	*/
	
}
