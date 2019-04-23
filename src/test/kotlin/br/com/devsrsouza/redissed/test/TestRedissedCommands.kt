package br.com.devsrsouza.redissed.test

import br.com.devsrsouza.redissed.RedissedCommands

class TestRedissedCommands : RedissedCommands {
    val redisKeys = mutableMapOf<String, String>()
    val redisKeysTimeout = mutableMapOf<String, Long>()

    override fun set(key: String, value: String) {
        redisKeys[key] = value
    }

    override fun get(key: String): String? {
        return redisKeys[key]
    }

    override fun contains(key: String): Boolean {
        return key in redisKeys
    }

    override fun del(key: String): Boolean {
        return redisKeys.remove(key) != null
    }

    override fun expire(key: String, seconds: Int): Boolean {
        if(key in redisKeys) {
            redisKeysTimeout[key] = seconds.toLong()
            return true
        } else return false
    }

    override fun ttl(key: String): Long {
        return redisKeysTimeout[key] ?: -1
    }

}