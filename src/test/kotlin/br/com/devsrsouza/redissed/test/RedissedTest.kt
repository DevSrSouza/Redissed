package br.com.devsrsouza.redissed.test

import br.com.devsrsouza.redissed.RedisObject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RedissedTest {

    @Test
    fun `Should set in redis`() {
        val redis = TestRedissedCommands()

        val key = "my:key"
        val value = "test value"
        val redisObject = object : RedisObject(key, redis) {
            var test by string()
        }

        assertNull(redisObject.test)

        redisObject.test = value

        assertEquals(redisObject.test, value)
    }

    @Test
    fun `Should delete from redis`() {
        val redis = TestRedissedCommands()

        val key = "my:key"
        val value = "test value"
        val redisObject = object : RedisObject(key, redis) {
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
        val redis = TestRedissedCommands()

        val key = "my:key"
        val newValue = 250
        val redisObject = object : RedisObject(key, redis) {
            var points by int(5)
        }

        assertEquals(redisObject.points, 5)

        redisObject.points = newValue

        assertEquals(redisObject.points, newValue)
    }

}