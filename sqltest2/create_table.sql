create table obsazeni (
    id        integer primary key,
    postava   text not null,
    jmeno     text not null
);

insert into obsazeni(id, postava, jmeno) values (1, 'inspektor',  'Trachta');
insert into obsazeni(id, postava, jmeno) values (2, 'praktikant', 'Hlavacek');
insert into obsazeni(id, postava, jmeno) values (3, 'tovarnik',   'Bierhanzel');
insert into obsazeni(id, postava, jmeno) values (4, 'tovarnik',   'Meyer');
insert into obsazeni(id, postava, jmeno) values (5, 'stevard',    'Vaclav Kotek');

