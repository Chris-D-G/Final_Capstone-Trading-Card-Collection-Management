BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('jewels','$2a$10$zleuKnq3kh7jTyvQie9soeYac621lAZJx0d4ZGLPzyDN1cj46.P36','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('firagablast','$2a$10$6yhs5fKNIFWH.ssF6FnPwuhLcREv6mLkmDwExuA1EHSGvKOY2Ytv2','ROLE_USER');
INSERT INTO tcg (tcg_name) VALUES ('Magic The Gathering');

INSERT INTO collections (collection_name, tcg_id) VALUES ('template', 1);
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
	(1, (SELECT card_id FROM cards WHERE card_title = 'Hero\'s Downfall' LIMIT 1), 4),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Lightning Strike' LIMIT 1), 4),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Magma Jet' LIMIT 1), 4),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Mountain' LIMIT 1), 9),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Peak Eruption' LIMIT 1), 3),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Read the Bones' LIMIT 1), 2),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Satyr Firedancer' LIMIT 1), 2),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Searing Spear' LIMIT 1), 4),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Shamblin Remains' LIMIT 1), 4),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Sign in Blood' LIMIT 1), 2),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Swamp' LIMIT 1), 5),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Temple of Malice' LIMIT 1), 4),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Terminate' LIMIT 1), 4),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Tormod\'s Crypt' LIMIT 1), 3),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Tymaret, the Murder King' LIMIT 1), 2),
	(1, (SELECT card_id FROM cards WHERE card_title = 'Vampire Nighthawk' LIMIT 1), 4);
	

COMMIT TRANSACTION;
