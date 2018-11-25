select
    /*%expand*/*
from
    comment
where
    customer_id = /* customerId */1
order by
    id desc