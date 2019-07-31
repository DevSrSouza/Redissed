package br.com.devsrsouza.redissed.test

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.RedissedCommands
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedisObjectTest {

    @Test
    fun `Should set in redis`() {

        val commands = mockk<RedissedCommands>()
        every { commands.set("my:key:to_set:test", "test set value") } answers { nothing }
        every { commands.get("my:key:to_set:test") } returns null

        val key = "my:key:to_set"
        val value = "test set value"

        val redisObject = object : RedisObject(key, commands) {
            var test by string()
        }

        assertNull(redisObject.test)

        redisObject.test = value

        verify { commands.set("my:key:to_set:test", "test set value") }
    }

    @Test
    fun `Should delete from redis`() {
        val commands = mockk<RedissedCommands>()
        every { commands.set("my:key:to_delete:test", "test delete value") } answers { nothing }
        every { commands.get("my:key:to_delete:test") } returns null

        val key = "my:key:to_delete"
        val value = "test delete value"

        val redisObject = object : RedisObject(key, commands) {
            var test by string()
        }

        assertNull(redisObject.test)

        redisObject.test = value

        verify { commands.set("my:key:to_delete:test", "test delete value") }
        every { commands.get("my:key:to_delete:test") } returns "test delete value"

        assertEquals(redisObject.test, value)

        every { commands.del("my:key:to_delete:test") } answers { true }

        redisObject.test = null

        verify { commands.del("my:key:to_delete:test") }

        every { commands.get("my:key:to_delete:test") } returns null

        assertNull(redisObject.test)
    }

    @Test
    fun `Should have default value = 5`() {
        val commands = mockk<RedissedCommands>()

        every { commands.get("my:key:default:points") } returns null
        every { commands.set("my:key:default:points", "250") } answers { nothing }

        val key = "my:key:default"
        val newValue = 250

        val redisObject = object : RedisObject(key, commands) {
            var points by int(5)
        }

        assertEquals(redisObject.points, 5)

        redisObject.points = newValue

        verify { commands.set("my:key:default:points", "250") }
        every { commands.get("my:key:default:points") } returns "250"

        assertEquals(redisObject.points, newValue)
    }

    @Test
    fun `Should have expire time of 5 seconds`() {
        val commands = mockk<RedissedCommands>()

        every { commands.set("my:key:expire:points", "500") } answers { nothing }
        every { commands.expire("my:key:expire:points", 5) } answers { true }

        val key = "my:key:expire"
        val newValue = 500

        val redisObject = object : RedisObject(key, commands) {
            var points by int().withExpire()
        }

        redisObject.points = newValue to 5

        verify { commands.set("my:key:expire:points", "500") }
        verify { commands.expire("my:key:expire:points", 5) }
        every { commands.get("my:key:expire:points") } returns "500" to 5
        every { commands.ttl("my:key:expire:points") } answers { 5 }

        val points = redisObject.points
        assertNotNull(points)
        assertEquals(points!!.first, newValue)
        assertEquals(points!!.second, 5)
    }

    @Test
    fun `Should have expire time of -2 because the key was never set`() {
        val commands = mockk<RedissedCommands>()

        every { commands.get("my:key:expire:points") } returns null
        every { commands.ttl("my:key:expire:points") } returns -2
        every { commands.expire("my:key:expire:points", 5) } answers { true }

        val key = "my:key:expire"

        val redisObject = object : RedisObject(key, commands) {
            var points by int(350).withExpire()
        }

        val (points1, time1) = redisObject.points
        assertEquals(points1, 350)
        assertEquals(time1, -2)
    }

    @Test
    fun `Should set the key with expire time of 10 seconds`() {
        val commands = mockk<RedissedCommands>()

        every { commands.get("my:key:expire:points") } returns null
        every { commands.ttl("my:key:expire:points") } returns -2
        every { commands.set("my:key:expire:points", "150") } answers { nothing }
        every { commands.expire("my:key:expire:points", 10) } answers { true }

        val key = "my:key:expire"
        val newValue = 150

        val redisObject = object : RedisObject(key, commands) {
            var points by int().expire(10)
        }

        redisObject.points = newValue

        verify { commands.set("my:key:expire:points", "150") }
        verify { commands.expire("my:key:expire:points", 10) }
    }

}