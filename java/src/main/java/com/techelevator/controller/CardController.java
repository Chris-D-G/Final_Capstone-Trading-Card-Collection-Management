package com.techelevator.controller;


import com.techelevator.dao.CardDao;
import com.techelevator.model.Card;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
       try{
           return cardDao.getCardById(searchID);
       }catch (RuntimeException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unable to locate card!");
       }
    }

    @GetMapping(path= "/cards/search/title")
    public List<Card> getMultipleCardsByTitle (String searchTerm, @RequestParam (defaultValue= "false")  boolean isExactMatch ){
        try {
            return cardDao.getCardsByTitle(searchTerm, isExactMatch);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unable to locate any cards that match!");
        }
    }



}
