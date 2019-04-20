# Redissed
Redis Kotlin wrapper using Jedis based on Exposed

## Sample
```kotlin
import br.com.devsrsouza.redissed.RedisObject

class MyObject(database: String, jedis: Jedis) : RedisObject(database, jedis) {
  var hello: String? by string()
  var hi: String by string("Hi")
  
  val other: OtherObject by obj { OtherObject(it, jedis) }
}

class OtherObject(database: String, jedis: Jedis) : RedisObject(database, jedis) {
  var something = string()
  
  var points: Int = int(0)
}

val database = "my:db"
val jedis = myJedisInstance

val my = MyObject("$database:obj", jedis)

my.hello = "Hello" // setting "Hello" to my:db:obj:hello
my.hi // getting my:db:obj:hi from Redis if not exist return default "Hi"

my.hello = null // removing data from my:db:obj:hello

my.other.something = "Anything" // setting "Anything" to my:db:obj:other:something
my.other.points = 10 // setting 10 to my:db:obj:other:points
```
