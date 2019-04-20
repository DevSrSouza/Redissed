package br.com.devsrsouza.redissed.delegates.nullable

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegateNullable
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.setNullable
import kotlin.reflect.KProperty

class ShortRedisDelegateNullable : RedisDelegateNullable<Short> {
    override fun getValue(ref: RedisObject, property: KProperty<*>): Short? {
        return get(ref, property)?.toShort()
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Short?) {
        setNullable(value?.toString(), ref, property)
    }
}