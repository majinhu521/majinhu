local key = KEYS[1];
local times = ARGV[1];
local expire = ARGV[2];
local afterval = redis.call('incr',key);
if afterval == 1 then
redis.call('expire',key,tonumber(expire))
return 1;
end
if afterval > tonumber(times) then
return 0;
end
return 1;

