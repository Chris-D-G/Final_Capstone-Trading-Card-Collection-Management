package com.techelevator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Card;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class ScryfallBulkLoader {
    ObjectMapper objectMapper;
    File jsonPath;
    int mtgId = 1;

    public static void main(String[] args) {
        ScryfallBulkLoader scryfallBulkLoader = new ScryfallBulkLoader();
        scryfallBulkLoader.run();
    }
    public void run(){
        JsonNode cardArray = null;
        try {
            cardArray = objectMapper.readTree(jsonPath);
            System.out.println("Enter amount of cards to parse through (-1 for all): ");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine();
            int max = Integer.parseInt(response);
            max = (max == -1)? cardArray.size() : max;
            for (int i = 0; i < max; i++) {
                JsonNode cardJson = cardArray.get(i);
                Card currentCard = mapJsonCardToCard(cardJson);
                uploadCard(currentCard);
            }
        } catch (IOException e){
            System.out.println("failed to process file!");
            System.out.println(e.getMessage() + " " + e.getCause());
            System.exit(1);
        }
    }

    public ScryfallBulkLoader () {
        this.objectMapper = new ObjectMapper();
        this.jsonPath = new File("default-cards-20230925090459.json");
    }

    private Card mapJsonCardToCard(JsonNode cardJson) {
        String id = cardJson.get("id").asText();
        String scryfallUrl = cardJson.get("scryfall_uri").asText();
        String name = cardJson.get("name").asText();
        String imgUrl;
        if (!cardJson.has("image_uris")){
            imgUrl = null;
        } else {
            JsonNode imgUris = cardJson.get("image_uris");
            if (imgUris.has("png")) {
                imgUrl = imgUris.get("png").asText();
            } else if (imgUris.has("border_crop")) {
                imgUrl = imgUris.get("border_crop").asText();
            } else if (imgUris.has("large")) {
                imgUrl = imgUris.get("large").asText();
            } else if (imgUris.has("normal")) {
                imgUrl = imgUris.get("normal").asText();
            } else if (imgUris.has("small")) {
                imgUrl = imgUris.get("small").asText();
            } else imgUrl = null;
        }
        return new Card(id, mtgId, name, imgUrl, scryfallUrl);
    }
    private void uploadCard(Card card){
        System.out.println(card.getName());
        // to be implemented!
    }

}
