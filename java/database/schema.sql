BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

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
	card_title varchar(50) NOT NULL,
	card_image_url varchar(128),
	card_details_url varchar(128),
	CONSTRAINT PK_cards PRIMARY KEY(card_id),
	CONSTRAINT FK_cards_tcg FOREIGN KEY (tcg_id) REFERENCES tcg (tcg_id)
);

CREATE TABLE collections(
	collection_id SERIAL,
	user_id integer NOT NULL,
	tcg_id integer NOT NULL,
	card_id varchar(128) NOT NULL,		
	quantity integer CHECK (quantity > -1),
	CONSTRAINT PK_collections PRIMARY KEY (collection_id, user_id, tcg_id),
	CONSTRAINT FK_collections_users FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT FK_collections_tcg FOREIGN KEY (tcg_id) REFERENCES tcg (tcg_id),
	CONSTRAINT FK_collections_cards FOREIGN KEY (card_id) REFERENCES cards (card_id)
);


COMMIT TRANSACTION;
