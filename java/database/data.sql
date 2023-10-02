BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('jewels','$2a$10$zleuKnq3kh7jTyvQie9soeYac621lAZJx0d4ZGLPzyDN1cj46.P36','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('firagablast','$2a$10$6yhs5fKNIFWH.ssF6FnPwuhLcREv6mLkmDwExuA1EHSGvKOY2Ytv2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('jayburd','$2a$10$FZeGVV496ohC4UafZ.VohOoS0F0mvvJ/zQuCj76oC9jeUH6tcpHgi','ROLE_USER');
INSERT INTO tcg (tcg_name) VALUES ('Magic The Gathering');


INSERT INTO default_profile_img (pic_id, img_loc)
VALUES(1, 'https://randomuser.me/api/portraits/lego/1.jpg'),
(2, 'https://randomuser.me/api/portraits/lego/2.jpg'),
(3, 'https://randomuser.me/api/portraits/lego/3.jpg'),
(4,'https://randomuser.me/api/portraits/lego/4.jpg'),
(5,'https://randomuser.me/api/portraits/lego/5.jpg'),
(6,'https://randomuser.me/api/portraits/lego/6.jpg'),
(7,'https://randomuser.me/api/portraits/lego/7.jpg'),
(8,'https://randomuser.me/api/portraits/lego/8.jpg'),
(9,'https://randomuser.me/api/portraits/lego/9.jpg'),
(10,'https://randomuser.me/api/portraits/lego/0.jpg');

INSERT INTO users_profile (user_id, pic_id, about_me)
VALUES((SELECT user_id FROM users where username='user'), 1, 'I''m the default example user! I''m whatever the admin wants me to be' ),
((SELECT user_id FROM users where username='admin'), 10, 'I''m the default admin user! I have the power?!' );

INSERT INTO users_friends (user_id, friend_id)
VALUES(1,2),
(1,3),
(1,4);

COMMIT TRANSACTION;
