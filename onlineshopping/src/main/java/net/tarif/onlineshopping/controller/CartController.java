package net.tarif.onlineshopping.controller;

import net.tarif.onlineshopping.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/show")
	public ModelAndView showCart(
			@RequestParam(name = "result", required = false) String result) {
		ModelAndView mv = new ModelAndView("page");

		if (result != null) {
			switch (result) {
			case "added":
				mv.addObject("message",
						"Product has been successfully added inside cart!");
				break;

			case "error":
				mv.addObject("message", "Something went wrong!");
				break;

			case "updated":
				mv.addObject("message", "Cart has been updated successfully!");
				break;

			case "deleted":
				mv.addObject("message",
						"CartLine has been successfully removed!");
				break;

			case "maximum":
				mv.addObject("message",
						"Maximum limit for the item has been reached!");
				break;

			case "unavailable":
				mv.addObject("message", "Product quantity is not available!");
				break;

			}
		}

		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines", cartService.getCartLines());
		return mv;
	}

	@RequestMapping("/{cartLineId}/update")
	public String udpateCart(@PathVariable int cartLineId,
			@RequestParam int count) {
		String response = cartService.manageCartLine(cartLineId, count);
		return "redirect:/cart/show?" + response;
	}

	@RequestMapping("/{cartLineId}/remove")
	public String removeCartLine(@PathVariable int cartLineId) {
		String response = cartService.removeCartLine(cartLineId);
		return "redirect:/cart/show?" + response;
	}

	@RequestMapping("/add/{productId}/product")
	public String addCartLine(@PathVariable int productId) {
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show?" + response;
	}

}
