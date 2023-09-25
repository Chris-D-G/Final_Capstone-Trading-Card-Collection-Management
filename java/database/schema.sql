BEGIN TRANSACTION;

DROP TABLE IF EXISTS mtg_collections;
DROP TABLE IF EXISTS mtg_cards;
DROP TABLE IF EXISTS tcg;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE tcg (
	tgc_id SERIAL,
	game_name varchar(30) NOT NULL UNIQUE,
	CONSTRAINT PK_tcg PRIMARY KEY (tgc_id)
);

CREATE TABLE mtg_cards (
	card_id int NOT NULL,
	card_name varchar(30) NOT NULL,
	img_url varchar(120) NOT NULL,
	scryfall_url varchar(60) NOT NULL,
	CONSTRAINT PK_mtg_cards PRIMARY KEY (card_id)
);

CREATE TABLE card_collections (
	collection_id SERIAL,
	card_id int NOT NULL REFERENCES mtg_cards(card_id),
	user_id int NOT NULL REFERENCES users(user_id),
	tgc_id int NOT NULL REFERENCES tcg(tgc_id),
	quantity int NOT NULL,
	CONSTRAINT PK_mtg_collections PRIMARY KEY(collection_id, card_id, user_id, tgc_id)
);


COMMIT TRANSACTION;
