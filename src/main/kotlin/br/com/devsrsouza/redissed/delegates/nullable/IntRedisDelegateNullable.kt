package br.com.devsrsouza.redissed.delegates.nullable

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegateNullable
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.setNullable
import kotlin.reflect.KProperty

class IntRedisDelegateNullable : RedisDelegateNullable<Int> {
    override operator fun getValue(ref: RedisObject, property: KProperty<*>): Int? {
        return get(ref, property)?.toInt()
    }

    override operator fun setValue(ref: RedisObject, property: KProperty<*>, value: Int?) {
        return setNullable(value?.toString(), ref, property)
    }
}