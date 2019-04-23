package br.com.devsrsouza.redissed.parsers

object FloatParser : Parser<Float> {
    override fun parse(data: String) = data.toFloat()
    override fun render(element: Float) = element.toString()
}