package br.com.devsrsouza.redissed.delegates.nullable

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegateNullable
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.setNullable
import kotlin.reflect.KProperty

class FloatRedisDelegateNullable : RedisDelegateNullable<Float> {
    override fun getValue(ref: RedisObject, property: KProperty<*>): Float? {
        return get(ref, property)?.toFloat()
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Float?) {
        setNullable(value?.toString(), ref, property)
    }
}