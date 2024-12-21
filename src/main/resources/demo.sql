//countUnreadMessageFromUserId
select m.to_user_id, count(c.*)
from realtime_chat_message m
where m.from_user_id = :fromUserId and c.read_message = false
group by m.to_user_id