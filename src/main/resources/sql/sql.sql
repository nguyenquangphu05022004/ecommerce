/**
  --AUTO CREATE BILL EVERY USER CREATE ORDER
create trigger TR_Orders_AfterInsert
    after INSERT
    on orders
    for each row
begin
    /**
      CREATE BILL WHEN ORDER WAS CREATED
     */
    insert into bills(created_by, created_date,
                      modified_by, modified_date,
                      name, status, order_id)
        values (NEW.created_by,
                NEW.created_date,
                NEW.modified_by,
                NEW.modified_date,
                concat('Hóa đơn sản phẩm: ', (select p.name
                                              from products p
                                              where p.id = NEW.product_id)),
                'PROCESSING',
                NEW.id);
        /**
          DELETE BASKET IF ORDER OF PRODUCT WAS CREATED
         */
    DELETE basket
    from basket where basket.product_id = NEW.product_id;

end;

*/

/**
  AUTO INCREASE NUMBER OF SELLER EVERY BILL UPDATE TO STATUS = 'SUCCESS'
  create trigger TR_Bills_AfterUpdate
    after update on bills
    for each row
    begin
        if NEW.status = 'SUCCESS' THEN
            UPDATE track_product_sellers
                set number_of_products_sold = number_of_products_sold + 1
            WHERE product_id = (
                    select
                        p.id
                    from products p inner join orders o
                        on p.id = o.product_id and o.id = NEW.order_id
                );
        end if;
    end;
 */

