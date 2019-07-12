package br.com.devsrsouza.redissed.test

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.clients.JedisRedissedCommands
import br.com.devsrsouza.redissed.clients.redissed
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import redis.clients.jedis.Jedis

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedissedTest {

    lateinit var jedisCommands: JedisRedissedCommands

    @BeforeAll
    fun connectRedisWithJedis() {
        val jedis = Jedis("localhost")
        jedis.connect()

        jedisCommands = jedis.redissed
    }

    @Test
    fun `Should set in redis`() {
        val key = "my:key:to_set"
        val value = "test set value"

        val redisObject = object : RedisObject(key, jedisCommands) {
            var test by string()
        }

        assertNull(redisObject.test)

        redisObject.test = value

        assertEquals(redisObject.test, value)
    }

    @Test
    fun `Should delete from redis`() {
        val key = "my:key:to_delete"
        val value = "test delete value"

        val redisObject = object : RedisObject(key, jedisCommands) {
            var test by string()
        }

        assertNull(redisObject.test)

        redisObject.test = value

        assertEquals(redisObject.test, value)

        redisObject.test = null

        assertNull(redisObject.test)
    }

    @Test
    fun `Should have default value = 5`() {
        val key = "my:key:default"
        val newValue = 250

        val redisObject = object : RedisObject(key, jedisCommands) {
            var points by int(5)
        }

        assertEquals(redisObject.points, 5)

        redisObject.points = newValue

        assertEquals(redisObject.points, newValue)
    }

}