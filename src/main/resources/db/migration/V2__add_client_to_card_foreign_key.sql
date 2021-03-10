alter table clients add column card_pass_id int null;
alter table clients add foreign key (card_pass_id)
    references card_pass(id);

