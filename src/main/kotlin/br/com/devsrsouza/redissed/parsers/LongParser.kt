package br.com.devsrsouza.redissed.parsers

object LongParser : Parser<Long> {
    override fun parse(data: String) = data.toLong()
    override fun render(element: Long) = element.toString()
}