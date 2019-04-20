package br.com.devsrsouza.redissed.delegates.nullable

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.delegates.RedisDelegateNullable
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.setNullable
import kotlin.reflect.KProperty

class ByteRedisDelegateNullable : RedisDelegateNullable<Byte> {
    override fun getValue(ref: RedisObject, property: KProperty<*>): Byte? {
        return get(ref, property)?.toByte()
    }

    override fun setValue(ref: RedisObject, property: KProperty<*>, value: Byte?) {
        setNullable(value?.toString(), ref, property)
    }
}