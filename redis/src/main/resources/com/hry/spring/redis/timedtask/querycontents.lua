-- querycontents

-- ZSET key
local zSetKey = KEYS[1]
local zSetMin = KEYS[2]
local zSetMax = KEYS[3]
-- hash
local hashKey = KEYS[4]

-- run ZRANGEBYSCORE  : 获取所有已经到了需要执行的定时任务
local zSetValues = redis.call('ZRANGEBYSCORE', zSetKey, zSetMin, zSetMax)
local rtnContentTables = {}
for k, v in pairs(zSetValues) do
    -- run HGET : 获取定时任务的内容值
    local hashField = v
    local hashValue = redis.call('HGET', hashKey, hashField)
    table.insert(rtnContentTables,hashValue)
    redis.log(redis.LOG_DEBUG,hashField)
end
return rtnContentTables