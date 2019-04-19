package br.com.devsrsouza.redissed

import br.com.devsrsouza.redissed.delegates.RedisObjectDelegate
import br.com.devsrsouza.redissed.delegates.notnull.IntRedisDelegate
import br.com.devsrsouza.redissed.delegates.notnull.StringRedisDelegate
import br.com.devsrsouza.redissed.delegates.nullable.IntRedisDelegateNullable
import br.com.devsrsouza.redissed.delegates.nullable.StringRedisDelegateNullable
import redis.clients.jedis.commands.JedisCommands

abstract class RedisObject(val key: String, val connection: JedisCommands) {
    fun string() = StringRedisDelegateNullable()
    fun string(default: String) = StringRedisDelegate(default)

    fun int() = IntRedisDelegateNullable()
    fun int(default: Int) = IntRedisDelegate(default)

    fun <T : RedisObject> obj(factory: (String) -> T) = RedisObjectDelegate(factory)
}

