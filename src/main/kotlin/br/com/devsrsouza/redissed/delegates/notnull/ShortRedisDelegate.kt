package br.com.devsrsouza.redissed.delegates.notnull

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegate
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.set
import kotlin.reflect.KProperty

class ShortRedisDelegate(override val default: Short) : RedisDelegate<Short> {
    override fun getValue(ref: RedisObject, property: KProperty<*>): Short {
        return get(ref, property)?.toShort() ?: default
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Short) {
        set(value.toString(), ref, property)
    }
}