package br.com.devsrsouza.redissed.delegates

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.path
import kotlin.reflect.KProperty

class RedisObjectDelegate<T : RedisObject>(val factory: (String) -> T) {
    operator fun getValue(ref: RedisObject, property: KProperty<*>): T {
        val path = path(ref, property)
        return factory(path)
    }
}