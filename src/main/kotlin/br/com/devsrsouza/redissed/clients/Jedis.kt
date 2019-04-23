package br.com.devsrsouza.redissed.clients

import br.com.devsrsouza.redissed.RedissedCommands
import redis.clients.jedis.commands.JedisCommands

val JedisCommands.redissed get() = JedisRedissedCommands(this)

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