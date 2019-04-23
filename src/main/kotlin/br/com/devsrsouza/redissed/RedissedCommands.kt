package br.com.devsrsouza.redissed

interface RedissedCommands {

    /**
     * Set the [key] in redis with [value]
     */
    operator fun set(key: String, value: String)

    /**
     * Returns the value of [key] from redis or `null` if does not exist
     */
    operator fun get(key: String): String?

    /**
     * Returns `true` if the [key] exist in redis or `false` if does not
     */
    operator fun contains(key: String): Boolean

    /**
     * Remove [key] from redis and returns `true` if [key] exist or `false` if does not
     */
    fun del(key: String): Boolean

    /**
     * Set the expiration time of [key] in redis and returns `true` if success or `false` if does not
     */
    fun expire(key: String, seconds: Int): Boolean

    /**
     * Returns the seconds to expire of [key] from redis
     */
    fun ttl(key: String): Long

}