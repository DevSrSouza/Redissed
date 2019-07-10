package br.com.devsrsouza.redissed.delegates

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.expire
import kotlin.reflect.KProperty

class RedissedExpireDelegateNullable<T>(
    val seconds: Int,
    private val delegate: RedissedDelegateNullable<T>
) : RedissedDelegateNullable<T> by delegate {

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: T?) {
        delegate.setValue(ref, property, value)
        if(value != null) expire(ref, property, seconds)
    }
}