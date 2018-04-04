-- save 
-- hash info
local hashKey = KEYS[1]
local hashField = KEYS[2]
local hashFieldValue = KEYS[3]
-- zset info
local zSetKey = KEYS[4]
local zSetScore = KEYS[5]
local zSetMember = KEYS[6]

-- save hash
local result_1 = redis.call('HSET', hashKey, hashField, hashFieldValue)
-- save zset
local result_2 = redis.call('ZADD', zSetKey, zSetScore, zSetMember)
return result_1 + result_2