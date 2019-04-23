package br.com.devsrsouza.redissed.parsers

interface Parser<T> {
    /**
     * Parses the [data] in a [T] instance and returns it
     */
    fun parse(data: String): T

    /**
     * Serialize [element] in [String] and returns it
     */
    fun render(element: T): String
}