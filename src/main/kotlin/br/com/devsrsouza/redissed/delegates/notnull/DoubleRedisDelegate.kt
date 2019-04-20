package br.com.devsrsouza.redissed.delegates.notnull

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegate
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.set
import kotlin.reflect.KProperty

class DoubleRedisDelegate(override val default: Double) : RedisDelegate<Double> {
    override fun getValue(ref: RedisObject, property: KProperty<*>): Double {
        return get(ref, property)?.toDouble() ?: default
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Double) {
        set(value.toString(), ref, property)
    }
}