package br.com.devsrsouza.redissed.delegates.nullable

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegateNullable
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.setNullable
import kotlin.reflect.KProperty

class LongRedisDelegateNullable : RedisDelegateNullable<Long> {
    override fun getValue(ref: RedisObject, property: KProperty<*>): Long? {
        return get(ref, property)?.toLong()
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Long?) {
        setNullable(value?.toString(), ref, property)
    }
}