package br.com.devsrsouza.redissed

import br.com.devsrsouza.redissed.delegates.RedisObjectDelegate
import br.com.devsrsouza.redissed.delegates.notnull.*
import br.com.devsrsouza.redissed.delegates.nullable.*
import redis.clients.jedis.commands.JedisCommands

abstract class RedisObject(val key: String, val connection: JedisCommands) {
    fun string() = StringRedisDelegateNullable()
    fun string(default: String) = StringRedisDelegate(default)

    fun boolean() = BooleanRedisDelegateNullable()
    fun boolean(default: Boolean) = BooleanRedisDelegate(default)

    fun byte() = ByteRedisDelegateNullable()
    fun byte(default: Byte) = ByteRedisDelegate(default)

    fun short() = ShortRedisDelegateNullable()
    fun short(default: Short) = ShortRedisDelegate(default)

    fun int() = IntRedisDelegateNullable()
    fun int(default: Int) = IntRedisDelegate(default)

    fun long() = LongRedisDelegateNullable()
    fun long(default: Long) = LongRedisDelegate(default)

    fun float() = FloatRedisDelegateNullable()
    fun flaot(default: Float) = FloatRedisDelegate(default)

    fun double() = DoubleRedisDelegateNullable()
    fun double(default: Double) = DoubleRedisDelegate(default)

    fun <T : RedisObject> obj(factory: (String) -> T) = RedisObjectDelegate(factory)
}

