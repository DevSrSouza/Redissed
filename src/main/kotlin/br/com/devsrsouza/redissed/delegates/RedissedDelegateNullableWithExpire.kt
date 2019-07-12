package br.com.devsrsouza.redissed.delegates

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.ttl
import br.com.devsrsouza.redissed.expire
import kotlin.reflect.KProperty

class RedissedDelegateNullableWithExpire<T>(
    private val delegate: RedissedDelegateNullable<T>
) : RedissedDelegateNullable<Pair<T, Int>> {

    override fun getValue(ref: RedisObject, property: KProperty<*>): Pair<T, Int>? {
        val value = delegate.getValue(ref, property)

        if(value == null) return null
        else {
            val ttl = ttl(ref, property)

            return value to ttl.toInt()
        }
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Pair<T, Int>?) {
        val valueT = value?.first

        delegate.setValue(ref, property, valueT)

        if(value == null) return
        val expire = value.second

        expire(ref, property, expire)
    }
}