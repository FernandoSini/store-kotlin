package com.fernandosini.storeapikotlin.services

import com.fernandosini.storeapikotlin.adapters.Mapper
import com.fernandosini.storeapikotlin.data.models.Cart
import com.fernandosini.storeapikotlin.data.models.CartItem
import com.fernandosini.storeapikotlin.data.models.DTO.CartDTO
import com.fernandosini.storeapikotlin.data.models.Product
import com.fernandosini.storeapikotlin.exceptions.InternalServerErrorException
import com.fernandosini.storeapikotlin.exceptions.NotFoundException
import com.fernandosini.storeapikotlin.repository.CartItemRepository
import com.fernandosini.storeapikotlin.repository.CartRepository
import com.fernandosini.storeapikotlin.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class CartServiceImpl(
    private val repository: CartRepository,
    private val productRepository: ProductRepository,
    private val cartItemRepository: CartItemRepository
) : CartService {

    override fun findCurrentCart(cartId: Long): Cart? {
        return repository.findCurrentCart(cartId).orElseThrow { NotFoundException("Not found cart with this Id!") }
    }

    override fun addToCart(productId: Long, cartId: Long, quantity: Int): CartDTO {
        //   repository.addToCart(product)
        val cart: Cart = repository.findById(cartId).orElseThrow { NotFoundException("Cart not found") }
        val product: Product =
            productRepository.findById(productId).orElseThrow { NotFoundException("Product not found") }
        var cartItem: CartItem =
            cartItemRepository.findCartItemByProductIdAndCartId(cartId = cartId, productId = productId)
                .orElseThrow { NotFoundException("Product not found") }

        if (cartItem != null) {
            throw InternalServerErrorException("${product.name} already in cart")
        }
        if (product.quantity == 0 || product.quantity!! < quantity) {
            throw InternalServerErrorException("${product.name} is not available")
        }

        val newCartItem = CartItem()
        newCartItem.product = product
        newCartItem.cart = cart
        newCartItem.quantity = quantity
        newCartItem.productPrice = product.price
        cartItemRepository.save(newCartItem)

        product.quantity = product.quantity - quantity
        productRepository.save(product)

        cart.totalPrice = cart.totalPrice + (product.price * quantity)
        var cartDto = Mapper().convert(cart, CartDTO::class.java)
        var products = cart.items.stream().map { i ->
            Mapper().convert(i.product, Product::class.java)
        }
        cartDto.products = products.toList().toMutableSet();
        return cartDto;


    }

    override fun getCart(userId: Long, cartId: Long): CartDTO {
        var cart = repository.findCartByUserIdAndCartId(userId, cartId)
        if (cart == null) {
            throw NotFoundException("Cart not found")
        }

        val cartDTO = Mapper().convert(cart, CartDTO::class.java)
        var products = cart.items.stream().map { i ->
            Mapper().convert(i.product, Product::class.java)
        }
        cartDTO.products = products.toList().toMutableSet();
        return cartDTO;

    }

    override fun updateProductInCart(cartId: Long, productId: Long) {
        var cart = repository.findById(cartId).orElseThrow { throw NotFoundException("Cart not found!") }
        val product =
            productRepository.findById(productId).orElseThrow { throw NotFoundException("Product not found!") }
        var cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cartId = cartId, productId = productId)
            .orElseThrow { throw NotFoundException("Product not found in cart!") };

        var cartPrice = cart.totalPrice - (cartItem.productPrice * cartItem.quantity)

        cartItem.productPrice = product.price
        cart.totalPrice = cartPrice + (cartItem.productPrice * cartItem.quantity)
        cartItem = cartItemRepository.save(cartItem)
    }

    override fun updateProductQuantityInCart(cartId: Long, productId: Long, quantity: Integer): CartDTO {
        var cart = repository.findById(cartId).orElseThrow { throw NotFoundException("Cart not found!") }
        val product =
            productRepository.findById(productId).orElseThrow { throw NotFoundException("Product not found!") }

        if (product.quantity == 0) {
            throw NotFoundException("Product is not available!")

        }
        if (product.quantity < quantity.toInt()) {
            throw InternalServerErrorException("Quantity inválid!")
        }
        var cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cartId = cartId, productId = productId)
            .orElseThrow { throw NotFoundException("Product not found in cart!") };


        var cartPrice = cart.totalPrice - (cartItem.productPrice * cartItem.quantity)
        product.quantity = product.quantity + (cartItem.quantity - quantity.toInt())
        cartItem.productPrice = product.price
        cartItem.quantity = quantity.toInt()
        cart.totalPrice = cartPrice + (cartItem.productPrice * quantity.toInt());
        cartItemRepository.save(cartItem)
        var cartDTO = Mapper().convert(cart, CartDTO::class.java)
        var products = cart.items.stream().map { i ->
            Mapper().convert(i.product, Product::class.java)
        }
        cartDTO.products = products.toList().toMutableSet()

        return cartDTO;

    }

    override fun deleteProductInCart(cartId: Long, productId: Long) {
        var cart = repository.findById(cartId).orElseThrow { throw NotFoundException("Cart not found!") }

        var cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cartId = cartId, productId = productId)
            .orElseThrow { throw NotFoundException("Product not found in cart!") };

        cart.totalPrice = cart.totalPrice - (cartItem.productPrice * cartItem.quantity)

        val product: Product = cartItem.product
        product.quantity = product.quantity + cartItem.quantity
        productRepository.save(product)
        cartItemRepository.deleteCartItemByProductIdAndCartId(cartId = cartId, productId = productId)

    }
}