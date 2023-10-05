

(103, 'Red/White Deck', 1);

INSERT INTO collections_user (collection_id,user_id) VALUES (103,3);


(103, (SELECT card_id FROM cards WHERE card_title = "Chandra's Embercat" LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'A-Skyclave Shadowcat' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Cats' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Cat Warrior' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Cat Warriors' LIMIT 1), 2),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Canyon Wildcat' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Blistering Firecat' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Colossification' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Falthis, Shadowcat Familiar' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Firecat Blitz' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Mirri, Cat Warrior' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Dinosaur Cat' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Nacatl Savage' LIMIT 1), 9),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Black Cat' LIMIT 1), 3),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Garrison Cat' LIMIT 1), 2),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Nacatl War-Pride' LIMIT 1), 2),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Nacatl Outlander' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Pardic Firecat' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Canyon Wildcat' LIMIT 1), 2),
    (103, (SELECT card_id FROM cards WHERE card_title = 'A-Mischievous Catgeist // A-Catlike Curiosity' LIMIT 1), 5),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Sanctuary Cat' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Savage Firecat' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Penumbra Bobcat' LIMIT 1), 3),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Sacred Cat' LIMIT 1), 2),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Scoria Cat' LIMIT 1), 4),
    (103, (SELECT card_id FROM cards WHERE card_title = 'Skycat Sovereign' LIMIT 1), 4),
        (103, (SELECT card_id FROM cards WHERE card_title = 'Regathan Firecat' LIMIT 1), 4),
        (103, (SELECT card_id FROM cards WHERE card_title = 'Territorial Scythecat' LIMIT 1), 2),
        (103, (SELECT card_id FROM cards WHERE card_title = 'Cat Beast' LIMIT 1), 5),
        (103, (SELECT card_id FROM cards WHERE card_title = 'Uktabi Wildcats' LIMIT 1), 4),
        (103, (SELECT card_id FROM cards WHERE card_title = 'Cat' LIMIT 1), 4),
        (103, (SELECT card_id FROM cards WHERE card_title = 'Cat Dragon' LIMIT 1), 3),
        (103, (SELECT card_id FROM cards WHERE card_title = 'Cat Soldier' LIMIT 1), 2),
        (103, (SELECT card_id FROM cards WHERE card_title = 'Cat Bird' LIMIT 1), 4),
        (103, (SELECT card_id FROM cards WHERE card_title = "White Sun's Twilight // White Sun's Twilight" LIMIT 1), 2),
                (103, (SELECT card_id FROM cards WHERE card_title = "White Sun's Zenith" LIMIT 1), 4);