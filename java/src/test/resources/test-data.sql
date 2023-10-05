BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO users_profile (user_id, pic_id, about_me)
VALUES((SELECT user_id FROM users where username='user1'), 1, 'Test User 1' ),
((SELECT user_id FROM users where username='user2'), 2, 'Test User 2' ),
((SELECT user_id FROM users where username='user3'), 3, 'Test User 3' );

INSERT INTO users_friends (user_id, friend_id)
VALUES(1,2),
(1,3),
(2,1);

INSERT INTO messages (message_text,message_sender_user_id,message_receiver_user_id, message_timestamp, message_read_status) VALUES
('Wow youre really cool!',2,1,'2023-10-4 12:23:34', false),
('Can we trade cards? You have some interesting pieces!',1,2,'2023-09-10 04:35:31', true),
('Lets connect!',1,3,'2023-10-2 01:10:10', true);

INSERT INTO collections (collection_id, collection_name, tcg_id) VALUES
    (100, 'test1', 1),
    (101, 'test2', 1),
    (102, 'test3', 1);

INSERT INTO collections_user (collection_id,user_id) VALUES (101,1);
INSERT INTO collections_user (collection_id,user_id) VALUES (100,2);
INSERT INTO collections_user (collection_id,user_id) VALUES (102,3);

INSERT INTO collections_cards (collection_id, card_id, quantity) VALUES
    (100, 1, 4),
    (100, 2, 4),
    (100, 3, 4),
    (100, 4, 4),
    (100, 5, 2),



    (101, 6, 3),
    (101, 7, 4),
    (101, 8, 3),
    (101, 9, 4),
    (101, 10, 6),


    (102, 11, 1)
    (102, 12, 1)
    (102, 13, 1)
    (102, 14, 1)
    (102, 15, 1);


    INSERT INTO tcg (tcg_id,tcg_name) VALUES (1,'Magic: The Gathering');

    INSERT INTO default_profile_img (pic_id, img_loc) VALUES
    (1, 'Logo-Default-Icon'),
    (2, 'MTG-Symbols'),
    (3, 'Plains-Default-Icon');


    INSERT INTO cards (card_id, tcg_id, card_title)
    VALUES
        (1, 1, 'Abbot of Keral Keep'),
        (2, 1, 'Acolyte of the Inferno'),
        (3, 1, 'Blightning'),
        (4, 1, 'Browbeat'),
        (5, 1, 'Crimson Muckwader'),

        (6, 1, 'Squirrel Mob'),
        (7, 1, 'Nut Collector'),
        (8, 1, 'Satyr Wayfinder'),
        (9, 1, 'Negate'),
        (10, 1, 'Forest'),

        (11, 1, 'Aurelia, the Warleader'),
        (12, 1, 'Moraug, Fury of Akoum'),
        (13, 1, 'Jeska’s Will'),
        (14, 1, 'Helm of the Host'),
        (15, 1, 'Sword of Hearth and Home');



COMMIT TRANSACTION;
