package net.tarif.onlineshopping.handler;

import net.tarif.onlineshopping.model.RegisterModel;
import net.tarif.shoppingbackend.dao.UserDAO;
import net.tarif.shoppingbackend.dto.Address;
import net.tarif.shoppingbackend.dto.Cart;
import net.tarif.shoppingbackend.dto.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

@Component
public class RegisterHandler {
	
	
 @Autowired
 private UserDAO userDAO;
 
	public RegisterModel init() { 
		  return new RegisterModel();
		 } 
	
	public void addUser(RegisterModel registerModel, User user) {
		  registerModel.setUser(user);
		 } 
	 public void addBilling(RegisterModel registerModel, Address billing) {
		  registerModel.setBilling(billing);
		 }
	 
	 //check if pasword matches with confirm password
	 public String validateUser(User user, MessageContext error) {
		  String transitionValue = "success";
		   if(!user.getPassword().equals(user.getConfirmPassword())) {
		    error.addMessage(new MessageBuilder().error().source(
		      "confirmPassword").defaultText("Password does not match confirm password!").build());
		    transitionValue = "failure";    
		   }  
		   if(userDAO.getByEmail(user.getEmail())!=null) {
		    error.addMessage(new MessageBuilder().error().source(
		      "email").defaultText("Email address is already taken!").build());
		    transitionValue = "failure";
		   }
		  return transitionValue;
		 }
	 
	 public String saveAll(RegisterModel model) {
		  String transitionValue = "success";
		  User user = model.getUser();
		  if(user.getRole().equals("USER")) {
		   // create a new cart
		   Cart cart = new Cart();
		   cart.setUser(user);
		   user.setCart(cart);
		  }
		   
		  
		  // save the user
		  userDAO.addUser(user);
		  // save the billing address
		  Address billing = model.getBilling();
		  billing.setUser(user);
		//  setUserId(user.getId());
		  billing.setBilling(true);  
		  userDAO.addAddress(billing);
		  return transitionValue ;
		 } 
}
