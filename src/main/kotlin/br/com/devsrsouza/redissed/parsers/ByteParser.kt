package br.com.devsrsouza.redissed.parsers

object ByteParser : Parser<Byte> {
    override fun parse(data: String) = data.toByte()
    override fun render(element: Byte) = element.toString()
}