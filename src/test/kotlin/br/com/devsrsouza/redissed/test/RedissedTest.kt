package br.com.devsrsouza.redissed.test

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.RedissedCommands
import br.com.devsrsouza.redissed.clients.JedisRedissedCommands
import br.com.devsrsouza.redissed.clients.LettuceRedissedCommands
import br.com.devsrsouza.redissed.clients.redissed
import io.lettuce.core.RedisClient
import io.lettuce.core.RedisURI
import io.lettuce.core.api.sync.RedisCommands
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import redis.clients.jedis.Jedis
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedissedTest {

    lateinit var jedis: Jedis
    lateinit var jedisCommands: JedisRedissedCommands

    lateinit var lettuce: RedisCommands<String, String>
    lateinit var lettuceCommands: LettuceRedissedCommands

    @BeforeAll
    fun connectRedisWithJedis() {
        jedis = Jedis("localhost")
        jedis.connect()

        jedisCommands = jedis.redissed
    }

    @BeforeAll
    fun connectRedisWithLettuce() {
        val redis = RedisClient.create(RedisURI.create("localhost", 6379))
        val conn = redis.connect()
        lettuce = conn.sync()

        lettuceCommands = lettuce.redissed
    }

    fun flushallAndProvideRedissedCommands(): Stream<Arguments> {
        return Stream.of(
            Arguments.of({
                jedis.flushAll()
                return@of jedisCommands
            }),
            Arguments.of({
                lettuce.flushall()
                return@of lettuceCommands
            })
        )
    }

    @ParameterizedTest
    @MethodSource("flushallAndProvideRedissedCommands")
    fun `Should set in redis`(callback: () -> RedissedCommands) {
        val commands = callback()

        val key = "my:key:to_set"
        val value = "test set value"

        val redisObject = object : RedisObject(key, commands) {
            var test by string()
        }

        assertNull(redisObject.test)

        redisObject.test = value

        assertEquals(redisObject.test, value)
    }

    @ParameterizedTest
    @MethodSource("flushallAndProvideRedissedCommands")
    fun `Should delete from redis`(callback: () -> RedissedCommands) {
        val commands = callback()

        val key = "my:key:to_delete"
        val value = "test delete value"

        val redisObject = object : RedisObject(key, commands) {
            var test by string()
        }

        assertNull(redisObject.test)

        redisObject.test = value

        assertEquals(redisObject.test, value)

        redisObject.test = null

        assertNull(redisObject.test)
    }

    @ParameterizedTest
    @MethodSource("flushallAndProvideRedissedCommands")
    fun `Should have default value = 5`(callback: () -> RedissedCommands) {
        val commands = callback()

        val key = "my:key:default"
        val newValue = 250

        val redisObject = object : RedisObject(key, commands) {
            var points by int(5)
        }

        assertEquals(redisObject.points, 5)

        redisObject.points = newValue

        assertEquals(redisObject.points, newValue)
    }

}