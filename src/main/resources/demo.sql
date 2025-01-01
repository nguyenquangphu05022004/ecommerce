//countUnreadMessageFromUserId
select m.to_user_id, count(c.*)
from realtime_chat_message m
where m.from_user_id = :fromUserId and c.read_message = false
group by m.to_user_id


//getAllOrderFromSeller
select * from orders o
where combination = true and exists (
    select 1
    from line_items l where l.order_id = o.id and l.seller_id = :userMemberId
)