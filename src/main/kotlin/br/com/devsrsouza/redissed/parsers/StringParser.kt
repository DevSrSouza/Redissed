package br.com.devsrsouza.redissed.parsers

object StringParser : Parser<String> {
    override fun parse(data: String) = data
    override fun render(element: String) = element
}