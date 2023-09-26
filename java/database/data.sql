BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('jewels','$2a$10$zleuKnq3kh7jTyvQie9soeYac621lAZJx0d4ZGLPzyDN1cj46.P36','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('firagablast','$2a$10$6yhs5fKNIFWH.ssF6FnPwuhLcREv6mLkmDwExuA1EHSGvKOY2Ytv2','ROLE_USER');
INSERT INTO tcg (tcg_name) VALUES ('Magic The Gathering');

COMMIT TRANSACTION;
