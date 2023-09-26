package com.techelevator.controller;


import com.techelevator.dao.CardDao;
import com.techelevator.model.Card;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class CardController{

    private final CardDao cardDao;


    //Constructor needed for Rest Controller
    public CardController( CardDao cardDao){
        this.cardDao = cardDao;
    }
    @GetMapping(path= "/cards/search/id")
    public Card getCardByID (String searchID){
        return cardDao.getCardById(searchID);
    }

    @GetMapping(path= "/cards/search/title")
    public List<Card> getMultipleCardsByTitle (String searchTerm, @RequestParam (defaultValue= "false")  boolean isExactMatch ){
        return cardDao.getCardsByTitle(searchTerm, isExactMatch);
    }



}
