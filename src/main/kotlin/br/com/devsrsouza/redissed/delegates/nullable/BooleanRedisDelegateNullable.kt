package br.com.devsrsouza.redissed.delegates.nullable

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegateNullable
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.setNullable
import kotlin.reflect.KProperty

class BooleanRedisDelegateNullable : RedisDelegateNullable<Boolean> {
    override fun getValue(ref: RedisObject, property: KProperty<*>): Boolean? {
        return get(ref, property)?.toBoolean()
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Boolean?) {
        setNullable(value?.toString(), ref, property)
    }
}