-- Users:
INSERT INTO user (username, password, email)
VALUES ('gustavo', 'senhadogu', 'gustavo.sobrenome@gmail.com');
INSERT INTO user (username, password, email)
VALUES ('gabriel', 'senhadobiel', 'gabriel_sobrenome@gmail.com');
INSERT INTO user (username, password, email)
VALUES ('teste', 'contateste', 'teste@gmail.com');
INSERT INTO user (username, password, email)
VALUES ('alurapic', '123456', 'adm@alurapic.com');

-- Photos:
INSERT INTO photo (description, allow_comments, url, created, user_id)
VALUES ('leao',
        1,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Sultan_the_Barbary_Lion.jpg/440px-Sultan_the_Barbary_Lion.jpg',
        SYSDATE,
        1);
INSERT INTO photo (description, allow_comments, url, created, user_id)
VALUES ('canario do reino',
        1,
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVN5K_liCFw2_WSGjpe8KrRlkY0ubtn561Ow&usqp=CAU',
        SYSDATE,
        1);
INSERT INTO photo (description, allow_comments, url, created, user_id)
VALUES ('passaro preto',
        0,
        'https://s3.amazonaws.com/media.wikiaves.com.br/images/0802/2080539_55ea38441c551772bcd6457104460e90.jpg',
        SYSDATE,
        1);

INSERT INTO photo (description, allow_comments, url, created, user_id)
VALUES ('canario do reino femea',
        1,
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRa9CEDkwrORYlPjOm-dYLGA63HOjF0AcM5Zw&usqp=CAU',
        SYSDATE,
        2);

INSERT INTO photo (description, allow_comments, url, created, user_id)
VALUES ('peixe palhaco',
        1,
        'https://blog.pescagerais.com.br/wp-content/uploads/2021/02/peixe-palhaco-procurando-nemo.jpg',
        SYSDATE,
        4);
INSERT INTO photo (description, allow_comments, url, created, user_id)
VALUES ('maritaca',
        0,
        'https://i.ytimg.com/vi/jrIivkIhbaY/maxresdefault.jpg',
        SYSDATE,
        4);
INSERT INTO photo (description, allow_comments, url, created, user_id)
VALUES ('periquito',
        1,
        'https://www.petz.com.br/blog/wp-content/uploads/2017/09/periquito-02-1280x720.jpg',
        SYSDATE,
        4);
INSERT INTO photo (description, allow_comments, url, created, user_id)
VALUES ('Ben Te Vi',
        1,
        'https://i.ytimg.com/vi/TYMAB7nKtOs/maxresdefault.jpg',
        SYSDATE,
        4);

-- Photo's comments:
INSERT INTO photo_comment (comment, posted, username, user, photo_id)
VALUES ('The king of the florest!',
        SYSDATE,
        'gustavo',
        1,
        1);
INSERT INTO photo_comment (comment, posted, username, user, photo_id)
VALUES ('Yeah, he is awesome!',
        SYSDATE,
        'gabriel',
        2,
        1);

INSERT INTO photo_comment (comment, posted, username, user, photo_id)
VALUES ('So cute!',
        SYSDATE,
        'alurapic',
        4,
        2);

INSERT INTO photo_comment (comment, posted, username, user, photo_id)
VALUES ('One of the famous bird!',
        SYSDATE,
        'gustavo',
        1,
        7);
INSERT INTO photo_comment (comment, posted, username, user, photo_id)
VALUES ('Yeah, I had one!',
        SYSDATE,
        'gabriel',
        2,
        7);
INSERT INTO photo_comment (comment, posted, username, user, photo_id)
VALUES ('Really?! Nicee!',
        SYSDATE,
        'gustavo',
        1,
        7);
