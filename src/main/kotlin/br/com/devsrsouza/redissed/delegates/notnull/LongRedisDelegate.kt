package br.com.devsrsouza.redissed.delegates.notnull

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegate
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.set
import kotlin.reflect.KProperty

class LongRedisDelegate(override val default: Long) : RedisDelegate<Long> {
    override fun getValue(ref: RedisObject, property: KProperty<*>): Long {
        return get(ref, property)?.toLong() ?: default
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Long) {
        set(value.toString(), ref, property)
    }
}