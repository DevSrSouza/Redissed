package br.com.devsrsouza.redissed.parsers

object IntParser : Parser<Int> {
    override fun parse(data: String) = data.toInt()
    override fun render(element: Int) = element.toString()
}