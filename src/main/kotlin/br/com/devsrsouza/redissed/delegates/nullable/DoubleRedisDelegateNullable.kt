package br.com.devsrsouza.redissed.delegates.nullable

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegateNullable
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.setNullable
import kotlin.reflect.KProperty

class DoubleRedisDelegateNullable : RedisDelegateNullable<Double> {
    override fun getValue(ref: RedisObject, property: KProperty<*>): Double? {
        return get(ref, property)?.toDouble()
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Double?) {
        setNullable(value?.toString(), ref, property)
    }
}