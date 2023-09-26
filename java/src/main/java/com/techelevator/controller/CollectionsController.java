package com.techelevator.controller;

import com.techelevator.dao.CollectionsDao;
import com.techelevator.model.Card;
import com.techelevator.model.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class CollectionsController {

    @Autowired
  CollectionsDao cdao;
  @RequestMapping(path = "/allCollections", method = RequestMethod.GET)
  public List<Collection> getAllCollections(){
      return cdao.getAllCollections();
  }
  @RequestMapping(path = "/myCollections", method = RequestMethod.GET)
  public List<Collection> getUserCollections(Principal principal){
      return cdao.getAllUserCollections(principal.getName());
  }
  @RequestMapping(path = "/allCollections/{tcgId}", method = RequestMethod.GET)
  public List<Collection> getCollectionsByTCG(@PathVariable int tcgId){
      return cdao.getCollectionsByTCG(tcgId);
  }
  @RequestMapping(path = "/myCollections/{tcgId}", method = RequestMethod.GET)
  public List<Collection> getUserCollectionsByTCG(Principal principal, @PathVariable int tcgId){
      return cdao.getUserCollectionsByTCG(principal.getName(),tcgId);
  }

//  public Card getSingleCollectionItem()
@RequestMapping(path = "/myCollections/{collectionId}/add", method = RequestMethod.POST)
 public int addCardToCollection(@Valid @RequestBody Card card, @PathVariable int collectionId){
      return cdao.addCardToCollection(card, collectionId);
 }

 @RequestMapping(path = "/myCollections/add", method = RequestMethod.POST)
 public int createCollection(@Valid @RequestBody Collection collection, Principal principal){
      return cdao.addCollection(collection,principal.getName());
 }
 @RequestMapping(path = "/myCollections/{collectionId}/cards", method = RequestMethod.GET)
 public List<Card> getCardsByCollection(@PathVariable int collectionId){
      return cdao.getCardsByCollectionId(collectionId);
 }
}
