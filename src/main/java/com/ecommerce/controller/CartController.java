package com.ecommerce.controller;

import com.ecommerce.Model.Cart;
import com.ecommerce.Model.Product;
import com.ecommerce.Model.User;
import com.ecommerce.exception.CartSaveFailedException;
import com.ecommerce.service.CartService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CartRequestDto;
import com.ecommerce.dto.CartResponseDto;
import com.ecommerce.dto.CommonApiResponse;
import com.ecommerce.resource.CartResource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

	private final Logger LOG = LoggerFactory.getLogger(CartResource.class);

	@Autowired
	private CartService cartService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<CommonApiResponse> addCategory(@RequestBody CartRequestDto request) {

		LOG.info("Request received for add to cart");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null || request.getUserId() == 0 || request.getProductId() == 0) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		User user = this.userService.getUserById(request.getUserId());

		if (user == null) {
			response.setResponseMessage("Failed to add to cart, Customer not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Product product = this.productService.getProductById(request.getProductId());

		if (product == null) {
			response.setResponseMessage("Failed to add to cart, Product not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if(product.getQuantity() < request.getQuantity()) {
			response.setResponseMessage("Only "+product.getQuantity()+" Quantity is available!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Cart cart = new Cart();
		cart.setUser(user);
		cart.setProduct(product);
		cart.setQuantity(request.getQuantity());
		cart.setAddedTime(
				String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));

		Cart savedCart = this.cartService.updateCart(cart);

		if (savedCart == null) {
			throw new CartSaveFailedException("Failed to add to Cart");
		}

		response.setResponseMessage("Product Added to Cart Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<CartResponseDto> updateCart(@RequestBody CartRequestDto request) {

		LOG.info("Request received for updating the cart");

		CartResponseDto response = new CartResponseDto();

		if (request == null || request.getId() == 0) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CartResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		User user = this.userService.getUserById(request.getUserId());

		if (user == null) {
			response.setResponseMessage("Unauthorized User to update the Cart");
			response.setSuccess(false);

			return new ResponseEntity<CartResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		Cart cart = this.cartService.getCartById(request.getId());

		if (cart == null) {
			response.setResponseMessage("Cart not found:(");
			response.setSuccess(false);

			return new ResponseEntity<CartResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			if (request.getQuantity() == 0) {
				this.cartService.deleteCart(cart);
			} else {
				cart.setQuantity(request.getQuantity());

				this.cartService.updateCart(cart);
			}
		} catch (Exception e) {
			throw new CartSaveFailedException("Failed to update the Cart");
		}

		response.setResponseMessage("User Cart Updated Successful");
		response.setSuccess(true);

		return new ResponseEntity<CartResponseDto>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<CartResponseDto> deleteCart(@RequestBody CartRequestDto request) {
		LOG.info("Request received for deleting the cart");

		CartResponseDto response = new CartResponseDto();

		if (request.getId() == 0) {
			response.setResponseMessage("cart id is missing");
			response.setSuccess(false);

			return new ResponseEntity<CartResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		User user = this.userService.getUserById(request.getUserId());

		if (user == null) {
			response.setResponseMessage("Unauthorized User to delete the Cart");
			response.setSuccess(false);

			return new ResponseEntity<CartResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		Cart cart = this.cartService.getCartById(request.getId());

		if (cart == null) {
			response.setResponseMessage("Cart not found");
			response.setSuccess(false);

			return new ResponseEntity<CartResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			this.cartService.deleteCart(cart);
		} catch (Exception e) {
			throw new CartSaveFailedException("Failed to delete the Cart");
		}

		response.setResponseMessage("User Cart Updated Successful");
		response.setSuccess(true);

		return new ResponseEntity<CartResponseDto>(response, HttpStatus.OK);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<CartResponseDto> fetchUserCart(@RequestParam("userId") int userId) {
		LOG.info("Request received for fetching the user cart");

		CartResponseDto response = new CartResponseDto();

		User user = this.userService.getUserById(userId);

		if (user == null) {
			response.setResponseMessage("Unauthorized User to fetch the Cart");
			response.setSuccess(false);

			return new ResponseEntity<CartResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<Cart> carts = new ArrayList<>();

		carts = this.cartService.findByUser(user);

		if (carts == null) {
			response.setResponseMessage("No Products found in Cart");
			response.setSuccess(false);

			return new ResponseEntity<CartResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		response.setTotalCartAmount(calulateTotalAmountFromCart(carts));
		response.setCarts(carts);
		response.setResponseMessage("User Cart Fetched Successful");
		response.setSuccess(true);

		return new ResponseEntity<CartResponseDto>(response, HttpStatus.OK);
	}
	private BigDecimal calulateTotalAmountFromCart(List<Cart> carts) {

		BigDecimal totalAmount = BigDecimal.ZERO;

		if (CollectionUtils.isEmpty(carts)) {
			return totalAmount;
		}

		for (Cart cart : carts) {

			BigDecimal cartAmount = cart.getProduct().getPrice().multiply(new BigDecimal(cart.getQuantity()));

			totalAmount = totalAmount.add(cartAmount);
		}

		return totalAmount;
	}

}
