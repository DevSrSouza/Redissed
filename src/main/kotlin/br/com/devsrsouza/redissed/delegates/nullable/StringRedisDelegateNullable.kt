package br.com.devsrsouza.redissed.delegates.nullable

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegateNullable
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.setNullable
import kotlin.reflect.KProperty

open class StringRedisDelegateNullable : RedisDelegateNullable<String> {
    override operator fun getValue(ref: RedisObject, property: KProperty<*>): String? {
        return get(ref, property)
    }

    override operator fun setValue(ref: RedisObject, property: KProperty<*>, value: String?) {
        setNullable(value, ref, property)
    }
}