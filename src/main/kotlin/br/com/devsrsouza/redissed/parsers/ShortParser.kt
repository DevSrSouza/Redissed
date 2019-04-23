package br.com.devsrsouza.redissed.parsers

object ShortParser : Parser<Short> {
    override fun parse(data: String) = data.toShort()
    override fun render(element: Short) = element.toString()
}