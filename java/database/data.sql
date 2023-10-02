BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('jewels','$2a$10$zleuKnq3kh7jTyvQie9soeYac621lAZJx0d4ZGLPzyDN1cj46.P36','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('firagablast','$2a$10$6yhs5fKNIFWH.ssF6FnPwuhLcREv6mLkmDwExuA1EHSGvKOY2Ytv2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('jayburd','$2a$10$FZeGVV496ohC4UafZ.VohOoS0F0mvvJ/zQuCj76oC9jeUH6tcpHgi','ROLE_USER');
INSERT INTO tcg (tcg_name) VALUES ('Magic The Gathering');


INSERT INTO default_profile_img (pic_id, img_loc)
VALUES(1, 'https://lh3.googleusercontent.com/drive-viewer/AK7aPaDmJfUDUZbsBpN10kDEy2KpYbYBqQA6rxvVfJ_bw81_FE-uk44VtHxKZME4z04yjGimLJThDrA8JZ4FmZjEQMs6mhsm=s2560'),
(2, 'https://lh3.googleusercontent.com/drive-viewer/AK7aPaD8A7yCRqzKz1bsZHCyIWaxiiKKDw6W1hLyWtGDtgwMTW9RLuoJ_F0MWFTYqL7maf8H5fIgkm1etd-Vpt7Hjs-S_HK9=s2560'),
(3, 'https://lh3.googleusercontent.com/drive-viewer/AK7aPaCMd8isbQeG5kgmgltM1TpPWSqXgWqsWlAGfIfPA7BW5lQnFUpeY8FAyLK8i9g06AHPkiyavfygxCbtVnX0m5lCd77kmA=s2560'),
(4,'https://lh3.googleusercontent.com/drive-viewer/AK7aPaBY5_Nbk4NHhh1QoPjBu0leEWKCUUyjVA8i3MS3ZWBTj9Y8XAMVV9ZPdwB55uxzYsFCVPWLG3nDqxtTa3cC_oXVWU1UHw=s2560'),
(5,'https://lh3.googleusercontent.com/drive-viewer/AK7aPaAcAEAgGxoqETOpWEwU71rskFqj7RRSn-aEB2cs-KzL7R8l5Zay4lLWcvEr4Hq05KJ016uICSUEzDwLhyeHIYZUBbN1=s2560'),
(6,'https://lh3.googleusercontent.com/drive-viewer/AK7aPaC6I79iJGrj65PiYDGyLd9FdCC_LGYTID8yB8YxKRrJHKOWMsENPMcGy33Zuww15eBw7nWCcBlX_M9vTXIFeQulXDWXuw=s2560');


INSERT INTO users_profile (user_id, pic_id, about_me)
VALUES((SELECT user_id FROM users where username='user'), 1, 'I''m the default example user! I''m whatever the admin wants me to be' ),
((SELECT user_id FROM users where username='admin'), 1, 'I''m the default admin user! I have the power?!' );


INSERT INTO users_friends (user_id, friend_id)
VALUES(1,2),
(1,3),
(1,4);

COMMIT TRANSACTION;
