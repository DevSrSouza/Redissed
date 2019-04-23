package br.com.devsrsouza.redissed.delegates

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.setNullable
import br.com.devsrsouza.redissed.parsers.Parser
import kotlin.reflect.KProperty

class ParserDelegateNullable<T>(val parser: Parser<T>) {
    operator fun getValue(ref: RedisObject, property: KProperty<*>): T? {
        return get(ref, property)?.let { parser.parse(it) }
    }

    operator fun setValue(ref: RedisObject, property: KProperty<*>, value: T?) {
        val render = value?.let { parser.render(it) }
        setNullable(render, ref, property)
    }
}