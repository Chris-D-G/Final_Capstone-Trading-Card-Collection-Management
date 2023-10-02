package com.techelevator.controller;

import com.techelevator.dao.CollectionsDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class CollectionsController {

    @Autowired
    CollectionsDao cdao;
    private User userForCollectionId;

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/allCollections", method = RequestMethod.GET)
    public List<CollectionsDto> getAllCollections() {
        return cdao.getAllCollections();
    }

    @RequestMapping(path = "/myCollections", method = RequestMethod.GET)
    public List<Collection> getUserCollections(Principal principal) {
        return cdao.getAllUserCollections(principal.getName());
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/allCollections/{tcgId}", method = RequestMethod.GET)
    public List<Collection> getCollectionsByTCG(@PathVariable int tcgId) {
        return cdao.getCollectionsByTCG(tcgId);
    }

    @RequestMapping(path = "/myCollections/{tcgId}", method = RequestMethod.GET)
    public List<Collection> getUserCollectionsByTCG(Principal principal, @PathVariable int tcgId) {
        return cdao.getUserCollectionsByTCG(principal.getName(), tcgId);
    }

    @RequestMapping(path = "/myCollections/{collectionId}/add", method = RequestMethod.POST)
    public int addCardToCollection(@Valid @RequestBody Card card, @PathVariable int collectionId) {
        return cdao.addCardToCollection(card, collectionId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/myCollections/add", method = RequestMethod.POST)
    public int createCollection(@Valid @RequestBody Collection collection, Principal principal) {
        return cdao.addCollection(collection, principal.getName());
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/collections/{collectionId}/cards", method = RequestMethod.GET)
    public List<CardDto> getCardsByCollection(@PathVariable int collectionId) {
        return cdao.getCardsByCollectionId(collectionId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/myCollections/{collectionId}", method = RequestMethod.DELETE)
    public int removeCollection(@PathVariable int collectionId) {
        return cdao.removeCollection(collectionId);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/collections/{collectionId}", method = RequestMethod.GET)
    public Collection getCollectionById(@PathVariable int collectionId) {
        return cdao.getCollectionById(collectionId);
    }


    @PreAuthorize("permitAll")
    @GetMapping(path = "/collections/{collectionId}/user")
    public User getUserByCollection(@PathVariable int collectionId) {
        return userForCollectionId;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/collections/{collectionId}/delete}")
    public void deleteCardFromCollection(@Valid @RequestBody Card card, @PathVariable int collectionId) {
        cdao.deleteCardFromCollection(card, collectionId);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/collections/{collectionId}/cards/a", method = RequestMethod.GET)
    public List<Card> getCardsByCollectionAlph(@PathVariable int collectionId) {
        return cdao.getCardsByCollectionIdAlphabetized(collectionId);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/collections/{collectionId}/cards/c", method = RequestMethod.GET)
    public List<Card> getCardsByCollectionColor(@PathVariable int collectionId) {
        return cdao.getCardsByCollectionIdColor(collectionId);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/collections/{collectionId}/cards/ci", method = RequestMethod.GET)
    public List<Card> getCardsByCollectionColorIdentity(@PathVariable int collectionId) {
        return cdao.getCardsByCollectionIdColorIdentity(collectionId);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/collections/{collectionId}/cards/s", method = RequestMethod.GET)
    public List<Card> getCardsByCollectionSet(@PathVariable int collectionId) {
        return cdao.getCardsByCollectionIdSet(collectionId);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/collections/{collectionId}/cards/l", method = RequestMethod.GET)
    public List<Card> getCardsByCollectionLegality(@PathVariable int collectionId) {
        return cdao.getCardsByCollectionIdLegality(collectionId);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/collections/{collectionId}/cards/cmc", method = RequestMethod.GET)
    public List<Card> getCardsByCollectionCmc(@PathVariable int collectionId) {
        return cdao.getCardsByCollectionIdCmc(collectionId);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/collections/{collectionId}/cards/ed", method = RequestMethod.GET)
    public List<Card> getCardsByCollectionEDHREC(@PathVariable int collectionId) {
        return cdao.getCardsByCollectionIdEDHREC(collectionId);
    }

}


