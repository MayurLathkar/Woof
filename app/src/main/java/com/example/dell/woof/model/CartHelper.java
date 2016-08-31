package com.example.dell.woof.model;

/**
 * A helper class to retrieve the static shopping cart. Call {@code getCart()} to retrieve the shopping cart before you perform any operation on the shopping cart.
 *
 * @author Tony
 */
public class CartHelper {
    private static com.example.dell.woof.model.Cart cart = new com.example.dell.woof.model.Cart();

    /**
     * Retrieve the shopping cart. Call this before perform any manipulation on the shopping cart.
     *
     * @return the shopping cart
     */
    public static com.example.dell.woof.model.Cart getCart() {
        if (cart == null) {
            cart = new com.example.dell.woof.model.Cart();
        }

        return cart;
    }
}
