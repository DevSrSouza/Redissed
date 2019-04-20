package br.com.devsrsouza.redissed.delegates.notnull

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegate
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.set
import kotlin.reflect.KProperty

class ByteRedisDelegate(override val default: Byte) : RedisDelegate<Byte> {
    override fun getValue(ref: RedisObject, property: KProperty<*>): Byte {
        return get(ref, property)?.toByte() ?: default
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Byte) {
        set(value.toString(), ref, property)
    }
}