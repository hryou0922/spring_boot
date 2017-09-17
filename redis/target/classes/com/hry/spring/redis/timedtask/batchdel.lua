-- del key

local result = 0
-- 参数的传入的规律：4个一组
for k, v in pairs(KEYS) do
  if(k % 4 == 1 ) then
    -- hash 
    local hashKey = KEYS[k];
    local hashField = KEYS[k+1]
    -- zset
    local zSetKey = KEYS[k+2]
    local zSetMember = KEYS[k+3]
    -- run del hash 
    local result_1 = redis.call('HDEL', hashKey, hashField)
    -- run del zset
    local result_2 = redis.call('ZREM', zSetKey, zSetMember)
    result = result_1 + result_2
  end
end
return result