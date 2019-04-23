package br.com.devsrsouza.redissed

import br.com.devsrsouza.redissed.delegates.ParserDelegate
import br.com.devsrsouza.redissed.delegates.ParserDelegateNullable
import br.com.devsrsouza.redissed.delegates.RedisObjectDelegate
import br.com.devsrsouza.redissed.parsers.*

abstract class RedisObject(val key: String, val commands: RedissedCommands) {
    fun <T> parser(parser: Parser<T>) = ParserDelegateNullable(parser)
    fun <T> parser(parser: Parser<T>, default: T) = ParserDelegate(parser, default)

    fun string() = parser(StringParser)
    fun string(default: String) = parser(StringParser, default)

    fun boolean() = parser(BooleanParser)
    fun boolean(default: Boolean) = parser(BooleanParser, default)

    fun byte() = parser(ByteParser)
    fun byte(default: Byte) = parser(ByteParser, default)

    fun short() = parser(ShortParser)
    fun short(default: Short) = parser(ShortParser, default)

    fun int() = parser(IntParser)
    fun int(default: Int) = parser(IntParser, default)

    fun long() = parser(LongParser)
    fun long(default: Long) = parser(LongParser, default)

    fun float() = parser(FloatParser)
    fun flaot(default: Float) = parser(FloatParser, default)

    fun double() = parser(DoubleParser)
    fun double(default: Double) = parser(DoubleParser, default)

    fun <T : RedisObject> obj(factory: (String) -> T) = RedisObjectDelegate(factory)
}

