BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, tcg, cards, collections, collections_user, collections_cards CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE tcg(
	tcg_id SERIAL,
	tcg_name varchar(50) NOT NULL UNIQUE,
	CONSTRAINT PK_tcg PRIMARY KEY (tcg_id)
);

CREATE TABLE cards(
	card_id varchar(128) NOT NULL UNIQUE, 
	tcg_id integer NOT NULL,
	card_title varchar(256) NOT NULL,
	card_small_image_url varchar(256),
	card_normal_image_url varchar(256),
	card_reverse_image_url varchar(256),
	card_colors varchar(256),
	card_color_identity varchar(256) NOT NULL,
	card_set_id varchar(256) NOT NULL,
	card_set_code varchar(256) NOT NULL,
	card_set_name varchar(256) NOT NULL,
	card_details_url varchar(256),
	card_collector_number varchar(256) NOT NULL,
	card_legalities varchar(256) NOT NULL,
	card_layout varchar(256),
	card_cmc varchar(256),
	card_edhrec_rank varchar(256),
	CONSTRAINT PK_cards PRIMARY KEY(card_id),
	CONSTRAINT FK_cards_tcg FOREIGN KEY (tcg_id) REFERENCES tcg (tcg_id)
);

CREATE TABLE collections(
	collection_id SERIAL,
	collection_name varchar(50) NOT NULL,
	tcg_id integer NOT NULL,
	CONSTRAINT PK_collections PRIMARY KEY(collection_id),
	CONSTRAINT FK_collections_tcg FOREIGN KEY (tcg_id) REFERENCES tcg (tcg_id)
);

CREATE TABLE collections_user(
	collection_id integer NOT NULL,
	user_id integer NOT NULL,
	CONSTRAINT PK_collections_user PRIMARY KEY (collection_id, user_id),
	CONSTRAINT FK_collections_user_cID FOREIGN KEY (collection_id) REFERENCES collections (collection_id),
	CONSTRAINT FK_collections_user_uID FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE collections_cards(
	collection_id integer NOT NULL,
	card_id varchar(128) NOT NULL,
	quantity integer CHECK (quantity > -1),
	CONSTRAINT PK_collections_cards PRIMARY KEY (collection_id, card_id),
	CONSTRAINT FK_collections_cards_cID FOREIGN KEY (collection_id) REFERENCES collections (collection_id),
	CONSTRAINT FK_collections_cards_cardID FOREIGN KEY (card_id) REFERENCES cards (card_id)
);





COMMIT TRANSACTION;
