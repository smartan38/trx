package com.truxx.user_v1.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxx.user_v1.entity.Cart;
import com.truxx.user_v1.entity.CartItems;

import com.truxx.user_v1.entity.OrderItems;
import com.truxx.user_v1.entity.Orders;
import com.truxx.user_v1.entity.User;
import com.truxx.user_v1.repository.CartRepository;
import com.truxx.user_v1.repository.OrderItemsRepository;
import com.truxx.user_v1.repository.OrdersRepository;
import com.truxx.user_v1.repository.UserRepository;
import com.truxx.user_v1.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrdersRepository ordersRepo;
	@Autowired
	OrderItemsRepository orderItemsRepo;
	@Autowired
	CartRepository cartRepo;
	@Autowired
	UserRepository userRepo;
	@Override
	public Orders placeOrder(int userId) {
		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isEmpty()) {
			return null;
		}
		User user = optUser.get();
		Cart cart = null;
		List<Cart> carts = cartRepo.findAll();
		for (Cart c : carts) {
			if (c.getUser().getUser_id() == userId) {
				cart = c;
				break;
			}
		}

		Orders order = null;
		List<Orders> orders = ordersRepo.findAll();

		for (Orders o : orders) {
			if (o.getUser().getUser_id() == userId) {
				order = o;
				break;
			}
		}
	
		if (order == null) {
			order = new Orders();
			order.setUser(user);

			ordersRepo.save(order);
			List<CartItems> cartItems = cart.getCartItem();
			if (cartItems.isEmpty()) {
				return null; // throw exception
			}
			List<OrderItems> orderItems = order.getOrderItems();
			for (CartItems cartItem : cartItems) {
				OrderItems orderItem = new OrderItems();
				orderItem.setDate(LocalDateTime.now());
				orderItem.setOrder(order);
				orderItem.setOrderAmount(cartItem.getPrice() * cartItem.getQuantity());
				orderItem.setProduct(cartItem.getProduct());
				orderItem.setOrderStatus("processing");
				orderItemsRepo.save(orderItem);
				orderItems.add(orderItem);
			}
			order.setOrderItems(orderItems);
		}

		else {
			List<CartItems> cartItems = cart.getCartItem();
			List<OrderItems> orderItems = order.getOrderItems();
			if (cartItems.isEmpty()) {
				return null; // throw exception
			}
			for (CartItems cartItem : cartItems) {
				OrderItems orderItem = new OrderItems();
				orderItem.setDate(LocalDateTime.now());
				orderItem.setOrder(order);
				orderItem.setOrderAmount(cartItem.getPrice() * cartItem.getQuantity());
				orderItem.setProduct(cartItem.getProduct());
				orderItem.setOrderStatus("processing");
				orderItemsRepo.save(orderItem);
				orderItems.add(orderItem);
			}
			order.setOrderItems(orderItems);
		}
		Orders placedOrder = null;
		placedOrder = ordersRepo.save(order);

		if (placedOrder != null) {
			cart.getCartItem().clear();
			cart.setTotalPrice(0);
			cartRepo.save(cart);
			return placedOrder;
		} else {
			return null; // throw exception
		}
	}
	
	


//	@Override
//	public Order placeOrder(int userId) {
//		Optional<User> optUser = userRepo.findById(userId);
//		if (optUser.isEmpty()) {
//			return null;
//		}
//		User user = optUser.get();
//		Cart cart = null;
//		List<Cart> carts = cartRepo.findAll();
//		for (Cart c : carts) {
//			if (c.getUser().getUser_id() == userId) {
//				cart = c;
//				break;
//			}
//		}
//
//		Order order = null;
//		List<Order> orders = orderRepo.findAll();
//
//		for (Order o : orders) {
//			if (o.getUser().getUser_id() == userId) {
//				order = o;
//				break;
//			}
//		}
//		System.out.println(order);
//		if (order == null) {
//			order = new Order();
//			order.setUser(user);
//
//			orderRepo.save(order);
//			List<CartItems> cartItems = cart.getCartItem();
//			if (cartItems.isEmpty()) {
//				return null; // throw exception
//			}
//			List<OrderItems> orderItems = order.getOrderItems();
//			for (CartItems cartItem : cartItems) {
//				OrderItems orderItem = new OrderItems();
//				orderItem.setDate(LocalDateTime.now());
//				orderItem.setOrder(order);
//				orderItem.setOrderAmount(cartItem.getPrice() * cartItem.getQuantity());
//				orderItem.setProduct(cartItem.getProduct());
//				orderItem.setOrderStatus("processing");
//				orderItemsRepo.save(orderItem);
//				orderItems.add(orderItem);
//			}
//			order.setOrderItems(orderItems);
//		}
//
//		else {
//			List<CartItems> cartItems = cart.getCartItem();
//			List<OrderItems> orderItems = order.getOrderItems();
//			if (cartItems.isEmpty()) {
//				return null; // throw exception
//			}
//			for (CartItems cartItem : cartItems) {
//				OrderItems orderItem = new OrderItems();
//				orderItem.setDate(LocalDateTime.now());
//				orderItem.setOrder(order);
//				orderItem.setOrderAmount(cartItem.getPrice() * cartItem.getQuantity());
//				orderItem.setProduct(cartItem.getProduct());
//				orderItem.setOrderStatus("processing");
//				orderItemsRepo.save(orderItem);
//				orderItems.add(orderItem);
//			}
//			order.setOrderItems(orderItems);
//		}
//		Order placedOrder = null;
//		placedOrder = orderRepo.save(order);
//
//		if (placedOrder != null) {
//			cart.getCartItem().clear();
//			cart.setTotalPrice(0);
//			cartRepo.save(cart);
//			return placedOrder;
//		} else {
//			return null; // throw exception
//		}
//
//	}

	@Override
	public OrderItems updateOrder(int orderItemId) {
		Optional<OrderItems> optOrderItem =orderItemsRepo.findById(orderItemId);
		if(optOrderItem.isEmpty()) {
			return null; //throw exception
		}
		OrderItems orderItem=optOrderItem.get();
		orderItem.setOrderStatus("delivered");
		OrderItems updatedOrderItems=null;
		updatedOrderItems = orderItemsRepo.save(orderItem);
		if(updatedOrderItems != null) {
			return updatedOrderItems;
		}
		else {
			return null; //throw exception
		}
		
	}

	@Override
	public OrderItems viewOrder(int userId, int orderItemId) {
		 Optional<User> optUser =userRepo.findById(userId);
         if(optUser.isEmpty()) {
        	 return null;
         }
         int exixtingOrderItemId=0;
         User user=optUser.get();
         Orders order=null;
         List<Orders> orders = ordersRepo.findAll();
         for(Orders o :orders) {
        	 if(o.getUser().getUser_id()==user.getUser_id()) {
        		  order =o;
        		  break;
        	 }
         }
         List<OrderItems> orderItems = order.getOrderItems();
         for(OrderItems orderItem:orderItems) {
        	 if(orderItem.getOrderItemId()==orderItemId) {
        		 exixtingOrderItemId=orderItemId;
        		 break;
        	 }
         }
         if(exixtingOrderItemId==0) {
        	 return null;  // throw exception
         }
         Optional<OrderItems> optOrderItem=orderItemsRepo.findById(exixtingOrderItemId);
         if(optOrderItem.isEmpty()) {
        	 return null; //throw exception
         }
		return optOrderItem.get();
	}

	@Override
	public List<OrderItems> viewAllOrders() {
		List<OrderItems> orderItems = orderItemsRepo.findAll();
		if(orderItems.isEmpty()) {
			return null; //throw exception
		}
		return orderItems;
	}

	@Override
	public List<OrderItems> viewAllOrderByUserId(int userId) {
		 Optional<User> optUser =userRepo.findById(userId);
         if(optUser.isEmpty()) {
        	 return null;
         }
         Orders order=null;
         List<Orders> orders = ordersRepo.findAll();
         for(Orders o :orders) {
        	 if(o.getUser().getUser_id()==userId) {
        		  order =o;
        		  break;
        	 }
         }
         List<OrderItems> orderItems = order.getOrderItems();
         if(orderItems.isEmpty()) {
 			return null; //throw exception
 		}
 		return orderItems;
	}




	@Override
	public List<OrderItems> viewAllOrderBySellerId(int sellerId) {
		List<OrderItems> orderItems = orderItemsRepo.findAll();
		List<OrderItems> sellersOrder =new ArrayList<>();
		for(OrderItems orderItem:orderItems) {
			if(orderItem.getProduct().getSeller().getSellerId()==sellerId) {
				sellersOrder.add(orderItem);
			}
		}
		if(sellersOrder.isEmpty()) {
			return null; //throw exception
		}
		return sellersOrder;
	}

}
