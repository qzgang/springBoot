package com.hurbao.sso.common.redis;

import com.hurbao.sso.common.util.SerializeUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service("iCached")
public class RedisCacheImpl implements ICached {
    public static final String CHARSET = "UTF-8";
    @Resource
    private JedisCluster jedisCluster;

    public static void main(String[] args) {
        try {
            System.out.println("liqinqin".getBytes(CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setjedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    @Override
    public void put(String key, Object value) throws Exception {
        jedisCluster.set(key.getBytes(CHARSET), SerializeUtils.serialize(value));
    }

    @Override
    public void put(String key, Object value, int expireSec) throws Exception {
        jedisCluster.setex(key.getBytes(CHARSET), expireSec, SerializeUtils.serialize(value));
    }

    @Override
    public void expireSec(String key, int expireSec) throws Exception {
        jedisCluster.expire(key, expireSec);
    }

    @Override
    public void remove(String key) throws Exception {
        jedisCluster.del(key);
    }

    @Override
    public Object get(String key) throws Exception {
        return SerializeUtils.deserialize(jedisCluster.get(key.getBytes(CHARSET)));
    }

    @Override
    public Object lget(String key, long index) throws Exception {
        return SerializeUtils.deserialize(jedisCluster.lindex(key.getBytes(CHARSET), index));
    }

    @Override
    public Set hgetKeys(String key) throws Exception {
        return jedisCluster.hkeys(key);
    }

    @Override
    public void hset(String key, String hkey, Object value) throws Exception {
        jedisCluster.hset(key.getBytes(CHARSET), hkey.getBytes(CHARSET), SerializeUtils.serialize(value));
    }

    @Override
    public Object hget(String key, String hkey) throws Exception {
        return SerializeUtils.deserialize(jedisCluster.hget(key.getBytes(CHARSET), hkey.getBytes(CHARSET)));
    }

    @Override
    public Long hremove(String key, String... hkey) throws Exception {
        return jedisCluster.hdel(key, hkey);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Set<T> hvalues(String key) throws Exception {
        Collection<byte[]> tmp = this.jedisCluster.hvals(key.getBytes(CHARSET));
        Set<T> list = new HashSet<T>();
        for (byte[] bs : tmp) {
            list.add((T) SerializeUtils.deserialize(bs));
        }
        return list;
    }

    public Map<String, Object> hget(String key) throws Exception {
        Map<byte[], byte[]> map = this.jedisCluster.hgetAll(key.getBytes(CHARSET));
        Map<String, Object> values = new HashMap<String, Object>();
        for (Map.Entry<byte[], byte[]> entry : map.entrySet()) {
            values.put(new String(entry.getKey(), CHARSET), SerializeUtils.deserialize(entry.getValue()));
        }
        return values;
    }

    public void hset(String key, Map<String, Object> vs) throws Exception {
        Map<byte[], byte[]> saveValue = new HashMap<byte[], byte[]>();
        for (Map.Entry<String, Object> entry : vs.entrySet()) {
            saveValue.put(entry.getKey().getBytes(CHARSET), SerializeUtils.serialize(entry.getValue()));
        }
        this.jedisCluster.hmset(key.getBytes(CHARSET), saveValue);
    }

    @Override
    public Long incrAndGetForExpire(String key, int expireSec) throws Exception {
        Long value = this.jedisCluster.incr(key);
        this.jedisCluster.expire(key, expireSec);
        return value;
    }

    @Override
    public Long incrAndGet(String key) throws Exception {
        return this.jedisCluster.incr(key);
    }

    @Override
    public Long decrAndGet(String key) throws Exception {
        return this.jedisCluster.decr(key);
    }

    @Override
    public long lpush(String key, Object value) throws Exception {
        return this.jedisCluster.lpush(key.getBytes(CHARSET), SerializeUtils.serialize(value));
    }

    @Override
    public Object lpop(String key) throws Exception {
        return SerializeUtils.deserialize(this.jedisCluster.lpop(key.getBytes(CHARSET)));
    }

    @Override
    public Object rpop(String key) throws Exception {
        return SerializeUtils.deserialize(this.jedisCluster.rpop(key.getBytes(CHARSET)));
    }

    @Override
    public boolean exists(String key) throws Exception {
        return this.jedisCluster.exists(key.getBytes(CHARSET));
    }

    public Long getIncrOrDecrValue(String key) throws Exception {
        String val = this.jedisCluster.get(key);
        if (val == null) {
            return 0l;
        } else {
            return Long.parseLong(val);
        }
    }

    @Override
    public Long llen(String key) throws Exception {
        return this.jedisCluster.llen(key);
    }

    @Override
    public Long incrByVal(String key, Long val) throws Exception {
        return this.jedisCluster.incrBy(key, val);
    }

    @Override
    public Long decrByVal(String key, Long val) throws Exception {
        return this.jedisCluster.decrBy(key, val);
    }

    @Override
    public Long setnx(String key, String value) {

        return jedisCluster.setnx(key, value);
    }

    @Override
    public List<Object> lrange(String key, long start, long end) throws Exception {
        List<byte[]> bytesList = this.jedisCluster.lrange(key.getBytes(CHARSET), start, end);
        List<Object> result = new ArrayList<>();
        for (byte[] bytes : bytesList) {
            Object obj;
            try {
                ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
                ObjectInputStream oi = new ObjectInputStream(bi);
                obj = oi.readObject();
                bi.close();
                oi.close();
                result.add(obj);
            } catch (Exception e) {
                System.out.println("translation" + e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }
}
