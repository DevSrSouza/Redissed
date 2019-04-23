package br.com.devsrsouza.redissed.clients

import br.com.devsrsouza.redissed.RedissedCommands
import io.lettuce.core.api.sync.RedisCommands

val RedisCommands<String, String>.redissed: LettuceRedissedCommands
    get() = LettuceRedissedCommands(this)

class LettuceRedissedCommands(val commands: RedisCommands<String, String>) : RedissedCommands {
    override fun set(key: String, value: String) {
        commands.set(key, value)
    }

    override fun get(key: String): String? {
        return commands.get(key)
    }

    override fun contains(key: String): Boolean {
        return commands.exists(key) >= 1
    }

    override fun del(key: String): Boolean {
        return commands.del(key) >= 1
    }

    override fun expire(key: String, seconds: Int): Boolean {
        return commands.expire(key, seconds.toLong())
    }

    override fun ttl(key: String): Long {
        return commands.ttl(key)
    }
}