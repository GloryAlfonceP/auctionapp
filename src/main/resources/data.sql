ALTER TABLE auction ADD CONSTRAINT fk_auction_Itm FOREIGN KEY  (auction_Itm) REFERENCES Item(itm_id);


ALTER TABLE BID ADD CONSTRAINT fk_bid_Itm FOREIGN KEY (bid_Itm) REFERENCES Item(itm_id);


insert into users (usr_token,usr_id,usr_name,usr_role,usr_balance) values ('US101','101','john','BUYER',700);
insert into users (usr_token,usr_id,usr_name,usr_role,usr_balance) values ('US117','117','gill','SELLER',600);
insert into users (usr_token,usr_id,usr_name,usr_role,usr_balance) values ('US182','182','alice','BUYER',500);

insert into item (min_price,itm_desc,itm_id,itm_image,itm_name,itm_owner) values (550,'Antiq watch. Gold Color.Made in Germany', 'it102' ,'cbss.jpg','Antica' , '101' );
insert into item (min_price,itm_desc,itm_id,itm_image,itm_name,itm_owner) values (550,'MultiColor Renaissence Painting,1890', 'it122' ,'mrps.jpg','Soltitude' , '182' );
insert into item (min_price,itm_desc,itm_id,itm_image,itm_name,itm_owner) values (450,'Marlyin manroe dress from 1950', 'it123' ,'mmds.jpg','Marilyn' , '117' );


insert into bid (bid_price,bid_ts,is_success,bid_id,bid_itm,bid_usr) values (520 , current_timestamp, false , 'b129' , 'it122' , '101' );
insert into bid (bid_price,bid_ts,is_success,bid_id,bid_itm,bid_usr) values (540 , current_timestamp, false , 'b139' , 'it122' , '117' );
insert into bid (bid_price,bid_ts,is_success,bid_id,bid_itm,bid_usr) values (590 , current_timestamp, false , 'b123' , 'it123' , '182' );
insert into bid (bid_price,bid_ts,is_success,bid_id,bid_itm,bid_usr) values (570 , current_timestamp, true , 'b120' , 'it122' , '101' );
insert into bid (bid_price,bid_ts,is_success,bid_id,bid_itm,bid_usr) values (470 , current_timestamp, true , 'b150' , 'it122' , '101' );
insert into bid (bid_price,bid_ts,is_success,bid_id,bid_itm,bid_usr) values (670 , current_timestamp, true , 'b180' , 'it122' , '101' );
insert into bid (bid_price,bid_ts,is_success,bid_id,bid_itm,bid_usr) values (870 , current_timestamp, true , 'b121' , 'it122' , '101' );
insert into bid (bid_price,bid_ts,is_success,bid_id,bid_itm,bid_usr) values (1000 , current_timestamp, true , 'b108' , 'it122' , '101' );

insert into auction (auction_id,auction_itm,auction_status,bid_id,buyer,payment,seller,winning_bid) values ('9154','it122','closed','b129','101','cash','182','b120');
insert into auction (auction_id,auction_itm,auction_status,bid_id,buyer,payment,seller,winning_bid) values ('9134','it123','open','b123',null,null,'117',null);