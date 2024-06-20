package com.truxx.user_v1.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxx.user_v1.entity.Cart;
import com.truxx.user_v1.entity.CartItems;
import com.truxx.user_v1.entity.Product;
import com.truxx.user_v1.entity.User;
import com.truxx.user_v1.repository.CartItemsRepository;
import com.truxx.user_v1.repository.CartRepository;
import com.truxx.user_v1.repository.UserRepository;
import com.truxx.user_v1.service.CartService;
@Service
public class CartServiceImpl implements CartService {
     @Autowired
       UserRepository userRepo;
     @Autowired
      CartRepository cartRepo;
     @Autowired
      CartItemsRepository cartItemsRepo;
	@Override
	public Cart addToCart(int userId, Product product) {
	      Optional<User> optUser =userRepo.findById(userId);
	         if(optUser.isEmpty()) {
	        	 return null;
	         }
	         User user=optUser.get();
	         Cart cart=null;
	         List<Cart> carts= cartRepo.findAll();
	            for(Cart c:carts) {
	            	if(c.getUser().getUser_id()==userId) {
	            		cart=c;
	            		break;
	            	}
	            }
	            if(cart==null) {
	            	cart=new Cart();
	            	cart.setUser(user);
	            	cartRepo.save(cart);
	            	
	            	List<CartItems> cartItems = cart.getCartItem();
	            	CartItems cartItem=new CartItems();
	            	cartItem.setQuantity(1);
	            	cartItem.setPrice(product.getPrice()*1.0);
	            	cartItem.setProduct(product);
	            	cartItem.setCart(cart);
	            	
	            	cartItemsRepo.save(cartItem);
	            	cartItems.add(cartItem);
	            	cart.setTotalPrice(product.getPrice()*1.0);
	            	cart.setCartItem(cartItems);
	            	
	            }
	            else {
	            	double price=0;
	            	double initialTotalPrice=0;
	            	boolean flag=true;   //to check whether product is already there or not
	            	List<CartItems> cartItems=cart.getCartItem();
	            	for(CartItems cartItem:cartItems) {
	            		if(cartItem.getProduct().getProductId()==product.getProductId()) {
	            			cartItem.setQuantity(cartItem.getQuantity()+1);
	            			price+=cartItem.getPrice();
	            			flag=false;
	            			cartItemsRepo.save(cartItem);
	            		}
	            	}
	            	if(!flag) {
	            		initialTotalPrice=cart.getTotalPrice();
	            		cart.setTotalPrice(price+initialTotalPrice);
	            	}
	            	if(flag) {
	            		CartItems newCartItem = new CartItems();
	            	     newCartItem.setQuantity(1);
	            	     newCartItem.setPrice(product.getPrice());
	            	     newCartItem.setProduct(product);
	            	     newCartItem.setCart(cart);
	            	     cartItemsRepo.save(newCartItem);
	            	     cartItems.add(newCartItem);
	            	     cart.setCartItem(cartItems);
	            	     initialTotalPrice=cart.getTotalPrice();
	            	     cart.setTotalPrice(product.getPrice()+initialTotalPrice);
	            	     
	            	}
	            	
	            	
	            }
	          cartRepo.save(cart);
	         return cart;
	}

	@Override
	public Cart getUserCart(int userId) {
		  Optional<User> optUser =userRepo.findById(userId);
	         if(optUser.isEmpty()) {
	        	 return null;
	         }
//	        User user=optUser.get();
	         Cart userCart=null;
	         List<Cart> carts = cartRepo.findAll();
	         for(Cart cart:carts) {
	        	 if(cart.getUser().getUser_id()==userId) {
	        		 userCart=cart;
	        		 break;
	        	 }
	         }
		return userCart;
	}

	@Override
	public Cart removeProductFromCart(int userId, Product product) {
	        Cart cart=getUserCart(userId);
	        List<CartItems> cartItems = cart.getCartItem();
	        boolean flag=false;
	        double priceToBeReduced=0;
	        for(CartItems cartItem:cartItems) {
	        	if(cartItem.getProduct().getProductId()==product.getProductId()) {
	        		priceToBeReduced=cartItem.getPrice()*cartItem.getQuantity();
	        		cartItems.remove(cartItem);
	        		cart.setCartItem(cartItems);
	        		flag=true;
	        		break;
	        	}
	        }
	        if(!flag) {
	        	return null; //throw error here
	        }
	        double cartTotalAmount = cart.getTotalPrice();
	        double latestTotalAmount =cartTotalAmount-priceToBeReduced;
	        cart.setTotalPrice(latestTotalAmount);
	      Cart latestCart=  cartRepo.save(cart);
		return latestCart;
	}

	@Override
	public Cart removeAllProduct(int userId) {
		  Cart cart=getUserCart(userId);
		  if(cart.getCartItem().isEmpty() || cart==null) {
			  return null ;  //throw exception here
		  }
		  cart.getCartItem().clear();
		  cart.setTotalPrice(0);
		return  cartRepo.save(cart);
		
	}

	@Override
	public Cart incrementProductQuantity(int userId, Product product) {
		   Cart cart=getUserCart(userId);
		   if(cart.getCartItem().isEmpty() || cart==null) {
				  return null ;  //throw exception here
			  }
		   List<CartItems> cartItems = cart.getCartItem();
		   boolean flag=false;
	        double priceToBeIncreased=0;
	        for(CartItems cartItem:cartItems) {
	        	if(cartItem.getProduct().getProductId()==product.getProductId()) {
	        		priceToBeIncreased=product.getPrice()*1.0;
	        	      cartItem.setQuantity(cartItem.getQuantity()+1);
	        	      cartItemsRepo.save(cartItem);
	        		flag=true;
	        		break;
	        	}
	        }
	        if(!flag) {
	        	return null; //throw exception
	        }
	        double cartTotalAmount = cart.getTotalPrice();
	        double latestTotalAmount =cartTotalAmount+priceToBeIncreased;
	        cart.setTotalPrice(latestTotalAmount);
	      Cart latestCart=  cartRepo.save(cart);
		return latestCart;
		
	}

	@Override
	public Cart decrementProductQuantity(int userId, Product product) {
		   Cart cart=getUserCart(userId);
		   if(cart.getCartItem().isEmpty() || cart==null) {
				  return null ;  //throw exception here
			  }
		   List<CartItems> cartItems = cart.getCartItem();
		   boolean flag=false;
	        double priceToBeDecreased=0;
	        for(CartItems cartItem:cartItems) {
	        	if(cartItem.getProduct().getProductId()==product.getProductId()) {
	        		priceToBeDecreased=product.getPrice()*1.0;
	        		if(cartItem.getQuantity()>1) {
	        	      cartItem.setQuantity(cartItem.getQuantity()-1);
	        	      cartItemsRepo.save(cartItem);
	        		}
	        		else {
	        		return	removeProductFromCart(userId, product);
	        		     
	        		}
	        	     
	        		flag=true;
	        		break;
	        	}
	        }
	        if(!flag) {
	        	return null; //throw exception
	        }
	        double cartTotalAmount = cart.getTotalPrice();
	        double latestTotalAmount =cartTotalAmount-priceToBeDecreased;
	        cart.setTotalPrice(latestTotalAmount);
	      Cart latestCart=  cartRepo.save(cart);
		return latestCart;
	}

	@Override
	public List<Cart> getAllCarts() {
		 List<Cart> carts = cartRepo.findAll();
		 if(carts.isEmpty()) {
			 return null;  //throw exception
		 }
		return carts;
	}

}
