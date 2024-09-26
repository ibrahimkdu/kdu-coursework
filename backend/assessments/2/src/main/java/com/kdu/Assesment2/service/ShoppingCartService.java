package com.kdu.Assesment2.service;

import com.kdu.Assesment2.exceptions.NotFoundException;
import com.kdu.Assesment2.model.ShoppingCart;
import com.kdu.Assesment2.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    public final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository)
    {
        this.shoppingCartRepository=shoppingCartRepository;
    }

    public void saveShoppingCart(ShoppingCart shoppingCart)
    {
        shoppingCartRepository.save(shoppingCart);
    }
    public ShoppingCart getShoppingCartById(Integer shoppingCartId)
    {
        return shoppingCartRepository.findById(shoppingCartId).orElse(null);
    }
    public List<ShoppingCart> getAllShoppingCart() {
        return shoppingCartRepository.findAll();
    }
    public void deleteShiftUser(Integer shoppingCartID) throws NotFoundException {
        Optional<ShoppingCart> shiftUsers = shoppingCartRepository.findById(shoppingCartID);

        if (!shiftUsers.isEmpty()) {
            shoppingCartRepository.deleteById(shoppingCartID);
        } else {
            throw new NotFoundException("Shift User not found");
        }
    }

}
