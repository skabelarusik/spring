
create table role
(
    id serial UNIQUE,
    name varchar(45) NOT NULL UNIQUE,
    primary key (id)
);

create table users
(
    id serial UNIQUE,
    name varchar(35) DEFAULT NULL,
    surname  varchar(35) DEFAULT NULL,
    login      VARCHAR(16) NOT NULL UNIQUE,
    password   VARCHAR(60) NOT NULL,
    sex varchar(40) DEFAULT 'male',
    birthday DATE NOT NULL DEFAULT '1970-01-01',
    email varchar(254) DEFAULT NULL,
    status integer NOT NULL DEFAULT '2',
    money decimal(10,0) DEFAULT '0',
    registrdate date DEFAULT NULL,
    avatar varchar(30) DEFAULT '/jsp/picture/user.png',
    active boolean NOT NULL DEFAULT '1',
    primary key (id),
    FOREIGN KEY (status) REFERENCES role (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE advices (
  idadvices serial UNIQUE,
  message varchar(100) NOT NULL UNIQUE,
  PRIMARY KEY (idadvices)
);

CREATE TABLE products
 (
  idproducts serial UNIQUE,
  name varchar(35) NOT NULL UNIQUE,
  calories integer NOT NULL,
  proteins float DEFAULT '-1',
  fats float DEFAULT '-1',
  carbs float DEFAULT '-1',
  PRIMARY KEY (idproducts)
);

CREATE TABLE bucket
 (
  idbucket serial UNIQUE,
  user_b integer NOT NULL,
  prod_b integer NOT NULL,
  portions float NOT NULL DEFAULT '1',
  PRIMARY KEY (idbucket),
  FOREIGN KEY (user_b) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (prod_b) REFERENCES products(idproducts) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE messages (
  idmessages serial UNIQUE,
  sender integer NOT NULL,
  recipient integer NOT NULL,
  topik varchar(40) NOT NULL,
  text varchar(500) NOT NULL,
  date date NOT NULL,
  show_recipient boolean NOT NULL DEFAULT '1',
  show_sender boolean NOT NULL DEFAULT '1',
  PRIMARY KEY (idmessages),
  FOREIGN KEY (recipient) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (sender) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE curator_rate (
  idcurator_rate serial UNIQUE,
  login integer NOT NULL,
  sender_id integer NOT NULL,
  rate integer NOT NULL,
  comment varchar(100),
  PRIMARY KEY (idcurator_rate),
  FOREIGN KEY (login) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (sender_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE programs_name (
  id serial UNIQUE,
  name varchar(40) NOT NULL UNIQUE,
  curator integer NOT NULL,
  duration integer NOT NULL DEFAULT '30',
  cost float NOT NULL DEFAULT '0',
  show_name boolean NOT NULL DEFAULT '1',
  FOREIGN KEY (curator) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE programs (
  id serial UNIQUE,
  name integer NOT NULL,
  product integer NOT NULL,
  portions float DEFAULT '1',
  time varchar(40) DEFAULT NULL,
  day varchar(40) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (product) REFERENCES products(idproducts) ON UPDATE CASCADE ON DELETE CASCADE,
   FOREIGN KEY (name) REFERENCES programs_name(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE review (
  idreview serial UNIQUE,
  name integer NOT NULL,
  date date NOT NULL,
  text varchar(300) NOT NULL,
  show_review boolean NOT NULL DEFAULT '1',
    FOREIGN KEY (name) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE subscriptions (
  idsubscriptions serial UNIQUE,
  program integer NOT NULL,
  subscriber integer NOT NULL,
  start_date date NOT NULL,
  finish_date date NOT NULL,
  active boolean NOT NULL DEFAULT '1',
  FOREIGN KEY (program) REFERENCES programs_name(id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (subscriber) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- add content to advices
insert into advices (idadvices, message) values ('1', 'БУГАГА');
insert into advices (idadvices, message) values ('2', 'testtest');

-- add content to role

insert into role (id, name) values('1', 'admin');
insert into role (id, name) values('2', 'curator');


-- add content to users
insert into users (id, name, surname, login, password, sex, birthday, email, status, money, registrdate, avatar, active) values(
'1', 'Andrey', 'Krupin', 'clockworktimes', '$2a$12$PEpfpTsA6Dokushn2Mq02eurA/Ntu7Cj61KPkuJV6/NfRpyN.9Gg.', 'male', '1987-10-22', 'ber-linn@tut.by', '2', '81', '2019-07-19', '/jsp/picture/user.png', '1');
insert into users (id, name, surname, login, password, sex, birthday, email, status, money, registrdate, avatar, active) values(
'2', 'Татьяная', 'Крупина', 'niaura', '$2a$12$neBeucHLnLq9bG2oz2L0vuNch3XBIVEMpEoWGGJszzp1OedEQ/Zcm', 'female', '1987-10-13', 'ber-linn@tut.by', '1', '30', '2019-07-19', '/jsp/picture/niaura.jpg', '1');

--add content to products
insert into products (idproducts, name, calories, proteins, fats, carbs)
values ('1', 'Арахис', '622', '26.3', '45.2', '9.9');
insert into products (idproducts, name, calories, proteins, fats, carbs)
values ('2', 'Горбуша свежая', '142', '20.5', '6.5', '0');


--add content to bucket
insert into bucket (idbucket, user_b, prod_b, portions) values ('1', '1', '1', '1');
insert into bucket (idbucket, user_b, prod_b, portions) values ('2', '2', '2', '2');


--add content to curator_rate
insert into curator_rate (idcurator_rate, login, sender_id, rate, comment) values
('1', '1', '1', '1', 'test');
insert into curator_rate (idcurator_rate, login, sender_id, rate, comment) values
('2', '2', '2', '2', 'good');


-- add content to messages
insert into messages (idmessages, sender, recipient, topik, text, date, show_recipient,
 show_sender) values ('1', '1', '2', 'Hi', 'How are you?', '2024-07-20', '1', '1');
insert into messages (idmessages, sender, recipient, topik, text, date, show_recipient,
 show_sender) values ('2', '1', '2', 'Hello', 'Im ok? What about you?', '2024-07-20', '1', '1');

-- add content to programs_name
insert into programs_name (id, name, curator, duration, cost, show_name) values(
'1', 'Худеем с ниайрой!', '1', '30', '10', '1');
insert into programs_name (id, name, curator, duration, cost, show_name) values(
'2', 'Эффективное похудение с бендером', '2', '30', '15.5', '1');


--add content to programs
insert into programs (id, name, product, portions, time, day) values(
'1', '1', '1', '1', 'dinner', 'friday');
insert into programs (id, name, product, portions, time, day) values(
'2', '1', '2', '1', 'dinner', 'sunday');


-- add content to review
insert into review (idreview, name, date, text, show_review) values(
'1', '1', '2017-03-24', 'qweqwe', '1');
insert into review (idreview, name, date, text, show_review) values(
'2', '2', '2019-08-03', 'Очень хороший сайт!!!', '1');



-- add content to subscriptions
insert into subscriptions (idsubscriptions, program, subscriber, start_date, finish_date, active) values(
'1', '1', '1', '2019-08-14', '2019-09-13', '1');
insert into subscriptions (idsubscriptions, program, subscriber, start_date, finish_date, active) values(
'2', '2', '2', '2019-08-14', '2019-08-22', '1');







