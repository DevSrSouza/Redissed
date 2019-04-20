package br.com.devsrsouza.redissed.delegates.notnull

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegate
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.set
import kotlin.reflect.KProperty

class BooleanRedisDelegate(override val default: Boolean) : RedisDelegate<Boolean> {
    override fun getValue(ref: RedisObject, property: KProperty<*>): Boolean {
        return get(ref, property)?.toBoolean() ?: default
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Boolean) {
        set(value.toString(), ref, property)
    }

}