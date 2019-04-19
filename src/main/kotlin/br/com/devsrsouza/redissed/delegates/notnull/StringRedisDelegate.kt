package br.com.devsrsouza.redissed.delegates.notnull

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegate
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.set
import kotlin.reflect.KProperty

open class StringRedisDelegate(override val default: String) : RedisDelegate<String> {
    override operator fun getValue(ref: RedisObject, property: KProperty<*>): String {
        return get(ref, property) ?: default
    }

    override operator fun setValue(ref: RedisObject, property: KProperty<*>, value: String) {
        set(value, ref, property)
    }
}