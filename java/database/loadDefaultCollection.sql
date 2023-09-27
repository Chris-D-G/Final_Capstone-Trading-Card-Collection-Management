INSERT INTO collections (collection_id, collection_name, tcg_id) VALUES
    (1, 'default', 1),
    (2, 'default2', 1);
INSERT INTO collections_user (collection_id,user_id) VALUES (2,1);

INSERT INTO collections_cards (collection_id, card_id, quantity) VALUES
    (1, (SELECT card_id FROM cards WHERE card_title = 'Abbot of Keral Keep' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Acolyte of the Inferno' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Blightning' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Browbeat' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Crimson Muckwader' LIMIT 1), 2),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Evolving Wilds' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Goblin Deathraiders' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Goblin Glory Chaser' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Grave Consequences' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Hero''s Downfall' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Lightning Strike' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Magma Jet' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Mountain' LIMIT 1), 9),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Peak Eruption' LIMIT 1), 3),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Read the Bones' LIMIT 1), 2),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Satyr Firedancer' LIMIT 1), 2),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Searing Spear' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Shambling Remains' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Sign in Blood' LIMIT 1), 2),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Swamp' LIMIT 1), 5),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Temple of Malice' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Terminate' LIMIT 1), 4),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Tormod''s Crypt' LIMIT 1), 3),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Tymaret, the Murder King' LIMIT 1), 2),
    (1, (SELECT card_id FROM cards WHERE card_title = 'Vampire Nighthawk' LIMIT 1), 4),


    (2, (SELECT card_id FROM cards WHERE card_title = 'Squirrel Mob' LIMIT 1), 3),
    (2, (SELECT card_id FROM cards WHERE card_title = 'Nut Collector' LIMIT 1), 4),
    (2, (SELECT card_id FROM cards WHERE card_title = 'Satyr Wayfinder' LIMIT 1), 3),
    (2, (SELECT card_id FROM cards WHERE card_title = 'Negate' LIMIT 1), 4),
    (2, (SELECT card_id FROM cards WHERE card_title = 'Forest' LIMIT 1), 6),
    (2, (SELECT card_id FROM cards WHERE card_title = 'Island' LIMIT 1), 5),
    (2, (SELECT card_id FROM cards WHERE card_title = 'Mana Confluence' LIMIT 1), 1),
    (2, (SELECT card_id FROM cards WHERE card_title = 'Temple of Mystery' LIMIT 1), 4),
    (2, (SELECT card_id FROM cards WHERE card_title = 'Chatter of the Squirrel' LIMIT 1), 4),
    (2, (SELECT card_id FROM cards WHERE card_title = 'Thragtusk' LIMIT 1), 4);