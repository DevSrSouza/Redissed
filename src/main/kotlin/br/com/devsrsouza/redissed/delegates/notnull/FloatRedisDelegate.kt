package br.com.devsrsouza.redissed.delegates.notnull

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegate
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.set
import kotlin.reflect.KProperty

class FloatRedisDelegate(override val default: Float) : RedisDelegate<Float> {
    override fun getValue(ref: RedisObject, property: KProperty<*>): Float {
        return get(ref, property)?.toFloat() ?: default
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Float) {
        set(value.toString(), ref, property)
    }
}