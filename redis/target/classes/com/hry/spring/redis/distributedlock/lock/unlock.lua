
-- unlock key
local key     = KEYS[1]
local content = KEYS[2]
local value = redis.call('get', key)
if value == content then
--  redis.call('decr', "count")
  return redis.call('del', key);
end
return 0