package br.com.devsrsouza.redissed.delegates

import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.get
import br.com.devsrsouza.redissed.set
import br.com.devsrsouza.redissed.parsers.Parser
import kotlin.reflect.KProperty

class ParserDelegate<T>(val parser: Parser<T>, val default: T) {
    operator fun getValue(ref: RedisObject, property: KProperty<*>): T {
        return get(ref, property)?.let { parser.parse(it) } ?: default
    }
    operator fun setValue(ref: RedisObject, property: KProperty<*>, value: T) {
        set(parser.render(value), ref, property)
    }
}