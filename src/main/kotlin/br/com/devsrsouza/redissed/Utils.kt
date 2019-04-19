package br.com.devsrsouza.redissed

import kotlin.reflect.KProperty

fun path(ref: RedisObject, property: KProperty<*>) = ref.key + ":" + property.name

fun set(value: String, ref: RedisObject, property: KProperty<*>) {
    ref.connection.set(path(ref, property), value)
}

fun setNullable(value: String?, ref: RedisObject, property: KProperty<*>) {
    val con = ref.connection
    val path = path(ref, property)

    if(value == null) con.del(path)
    else con.set(path, value)
}

fun get(ref: RedisObject, property: KProperty<*>): String? {
    return ref.connection.get(path(ref, property))
}