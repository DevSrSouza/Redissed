package br.com.devsrsouza.redissed

import br.com.devsrsouza.redissed.delegates.*
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

    fun <T> RedissedDelegate<T>.expire(seconds: Int) = RedissedExpireDelegate(seconds, this)
    fun <T> RedissedDelegateNullable<T>.expire(seconds: Int) = RedissedExpireDelegateNullable(seconds, this)

    fun <T> RedissedDelegate<T>.withExpire() = RedissedDelegateWithExpire(this)
    fun <T> RedissedDelegateNullable<T>.withExpire() = RedissedDelegateNullableWithExpire(this)
}

