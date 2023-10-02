BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('jewels','$2a$10$zleuKnq3kh7jTyvQie9soeYac621lAZJx0d4ZGLPzyDN1cj46.P36','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('firagablast','$2a$10$6yhs5fKNIFWH.ssF6FnPwuhLcREv6mLkmDwExuA1EHSGvKOY2Ytv2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('jayburd','$2a$10$FZeGVV496ohC4UafZ.VohOoS0F0mvvJ/zQuCj76oC9jeUH6tcpHgi','ROLE_USER');
INSERT INTO tcg (tcg_name) VALUES ('Magic The Gathering');


INSERT INTO default_profile_img (pic_id, img_loc)
VALUES(1, 'https://drive.google.com/file/d/1an0YmG4T_Mv_Rtz3oxFoE9wpn1SZNJwT/view?usp=sharing'),
(2, 'https://drive.google.com/file/d/1X0Vqv-TyFfZM1xQGOnA2pluoIq51UMcm/view?usp=sharing'),
(3, 'https://drive.google.com/file/d/1VykS7Ga0bFHdrp5TX1XBQtn5k-OkBbVC/view?usp=sharing'),
(4,'https://drive.google.com/file/d/1s-R2hNLaYRC5COJKKQmFGwXGtgycikRS/view?usp=sharing'),
(5,'https://drive.google.com/file/d/11jHbfoApvemMovMy4WkzEIouyVB8nOHP/view?usp=sharing'),
(6,'https://drive.google.com/file/d/1b1RC15_G1ASibnC58snTN4omNaORTdD8/view?usp=sharing');



INSERT INTO users_profile (user_id, pic_id, about_me)
VALUES((SELECT user_id FROM users where username='user'), 1, 'I''m the default example user! I''m whatever the admin wants me to be' ),
((SELECT user_id FROM users where username='admin'), 10, 'I''m the default admin user! I have the power?!' );

INSERT INTO users_friends (user_id, friend_id)
VALUES(1,2),
(1,3),
(1,4);

COMMIT TRANSACTION;
