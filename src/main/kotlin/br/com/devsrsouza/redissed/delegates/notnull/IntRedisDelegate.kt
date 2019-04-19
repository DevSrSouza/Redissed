package br.com.devsrsouza.redissed.delegates.notnull

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegate
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.set
import kotlin.reflect.KProperty

class IntRedisDelegate(override val default: Int) : RedisDelegate<Int> {
    override operator fun getValue(ref: RedisObject, property: KProperty<*>): Int {
        return get(ref, property)?.toInt() ?: default
    }

    override operator fun setValue(ref: RedisObject, property: KProperty<*>, value: Int) {
        set(value.toString(), ref, property)
    }
}