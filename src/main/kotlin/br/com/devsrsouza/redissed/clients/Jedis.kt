package br.com.devsrsouza.redissed.clients

import br.com.devsrsouza.redissed.RedissedCommands
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.commands.JedisCommands

val JedisCommands.redissed get() = JedisRedissedCommands(this)
val JedisPool.redissed get() = JedisPoolRedissedCommands(this)

class JedisRedissedCommands(val commands: JedisCommands) : RedissedCommands {

    override fun set(key: String, value: String) {
        commands.set(key, value)
    }

    override fun get(key: String): String? {
        return commands.get(key)
    }

    override fun contains(key: String): Boolean {
        return commands.exists(key)
    }

    override fun del(key: String): Boolean {
        return commands.del(key) >= 1
    }

    override fun expire(key: String, seconds: Int): Boolean {
        return commands.expire(key, seconds) == 1.toLong()
    }

    override fun ttl(key: String): Long {
        return commands.ttl(key)
    }

}

class JedisPoolRedissedCommands(val pool: JedisPool) : RedissedCommands {

    val jedis: Jedis get() = pool.resource

    override fun set(key: String, value: String) {
        jedis.use { it.set(key, value) }
    }

    override fun get(key: String): String? {
        return jedis.use { it.get(key) }
    }

    override fun contains(key: String): Boolean {
        return jedis.use { it.exists(key) }
    }

    override fun del(key: String): Boolean {
        return jedis.use { it.del(key) >= 1 }
    }

    override fun expire(key: String, seconds: Int): Boolean {
        return jedis.use { it.expire(key, seconds) == 1.toLong() }
    }

    override fun ttl(key: String): Long {
        return jedis.use { it.ttl(key) }
    }
}