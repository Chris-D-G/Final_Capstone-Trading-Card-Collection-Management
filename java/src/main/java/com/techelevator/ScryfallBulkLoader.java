package com.techelevator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.dao.CardDao;
import com.techelevator.dao.JdbcCardDao;
import com.techelevator.model.Card;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


@Component
public class ScryfallBulkLoader {
    private static final String FILE_PATH = "default-cards-20230925090459.json";
    final int MTG_ID = 1;
    CardDao cardDao;
    ObjectMapper objectMapper;
    File jsonPath;


    /**
     * Loads cards from the FILE_PATH json card array
     *
     * @param args redundant, leave as empty
     */
    public static void main(String[] args) {
        ScryfallBulkLoader scryfallBulkLoader = new ScryfallBulkLoader();
        scryfallBulkLoader.run();
    }

    /**
     * parses through the json file and fills
     * in the postgresql database
     */
    public void run(){
        try {
            JsonNode cardArray = objectMapper.readTree(jsonPath);
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

    /**
     * constructor to instantiate the ScryfallBulkLoader object
     */
    private ScryfallBulkLoader() {
        this.objectMapper = new ObjectMapper();
        this.jsonPath = new File(FILE_PATH);
        BasicDataSource datasource = new BasicDataSource();
        datasource.setUsername("final_capstone_appuser");
        datasource.setPassword("finalcapstone");
        datasource.setUrl("jdbc:postgresql://localhost:5432/final_capstone");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);

        this.cardDao = new JdbcCardDao(jdbcTemplate);
    }

    /**
     * creates a java Card object from a Scryfall json card object
     *
     * @param cardJson <a href="https://scryfall.com/docs/api/cards">Scryfall card object</a> from a json
     * @return java Card object based on the fields in the json card object
     */
    private Card mapJsonCardToCard(JsonNode cardJson) {
        String id = cardJson.get("id").asText();
        String scryfallUrl = cardJson.get("scryfall_uri").asText();
        String name = cardJson.get("name").asText();
        String imgUrl = null;
        String smallImgUrl = null;
        if (cardJson.has("image_uris")) {
            JsonNode imgUris = cardJson.get("image_uris");
            if (imgUris.has("normal")) {
                imgUrl = imgUris.get("normal").asText();
            }
            if (imgUris.has("small")) {
                smallImgUrl = imgUris.get("small").asText();
            }
        }
        return new Card(id, MTG_ID, name, imgUrl, smallImgUrl, scryfallUrl);
    }

    /**
     * updates the postgresql database with a card
     *
     * @param card the java card object to upload onto the postgresql server
     */
    private void uploadCard(Card card){
        if (cardDao.getCardById(card.getId()) == null) {
            Card uploadedCard = cardDao.addCard(card);
            System.out.println(uploadedCard.getName() + " has been added! Card id: " + uploadedCard.getId());
        } else {
            System.out.println(card.getName() + " already exists! Card id: " + card.getId());
        }
    }

}
