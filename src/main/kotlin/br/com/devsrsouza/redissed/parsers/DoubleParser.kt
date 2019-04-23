package br.com.devsrsouza.redissed.parsers

object DoubleParser : Parser<Double> {
    override fun parse(data: String) = data.toDouble()
    override fun render(element: Double) = element.toString()
}