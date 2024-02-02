package com.kdu.Assesment2.controller;
import com.kdu.Assesment2.exceptions.NotFoundException;
import com.kdu.Assesment2.model.ShoppingCart;
import com.kdu.Assesment2.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService)
    {
        this.shoppingCartService=shoppingCartService;
    }

    @PostMapping("/shoppingCart")
    public ResponseEntity<String> saveShift(@RequestBody ShoppingCart shoppingCart) {
        shoppingCartService.saveShoppingCart(shoppingCart);
        return new ResponseEntity<>("ShoppingCart saved successfully", HttpStatus.CREATED);
    }
    @GetMapping("/shoppingCart/{shoppingCartId}")
    public ResponseEntity<ShoppingCart> getShiftById(@PathVariable Integer shoppingCartId) {
        ShoppingCart retrievedShift=shoppingCartService.getShoppingCartById(shoppingCartId);
        if (retrievedShift != null) {
            return new ResponseEntity<>(retrievedShift, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/shoppingCart")
    public ResponseEntity<List<ShoppingCart>> getAllUsers() {
        List<ShoppingCart> allShifts = shoppingCartService.getAllShoppingCart();
        return new ResponseEntity<>(allShifts, HttpStatus.OK);
    }
    @DeleteMapping("/{shiftUserId}")
    public ResponseEntity<String> deleteShiftUser(@PathVariable Integer shiftUserId) throws NotFoundException {
        shoppingCartService.deleteShiftUser(shiftUserId);
        return new ResponseEntity<>("ShiftUser deleted successfully", HttpStatus.OK);
    }
}
