package br.com.devsrsouza.redissed.delegates

import br.com.devsrsouza.redissed.RedisObject
import kotlin.reflect.KProperty

interface RedissedDelegate<T> {
    operator fun getValue(ref: RedisObject, property: KProperty<*>): T
    operator fun setValue(ref: RedisObject, property: KProperty<*>, value: T)
}