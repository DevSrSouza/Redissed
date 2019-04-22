# Redissed
Redis Kotlin wrapper using Jedis inspired on Exposed

## Getting started

### Gradle
```kotlin
dependencies {
    compile("br.com.devsrsouza:redissed:0.1.1")
}
```

### Maven
```xml
<dependency>
    <groupId>br.com.devsrsouza</groupId>
    <artifactId>redissed</artifactId>
    <version>0.1.1</version>
</dependency>
```

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

## Supported types
```kotlin
string(): String?
string(default: String): String
byte(): Byte?
byte(default: Byte): Byte
short(): Short?
short(default: Short): Short
int(): Int?
int(default: Int): Int
long(): Long?
long(default: Long): Long
boolean(): Boolean?
boolean(default: Boolean): Boolean
obj(factory: (key: String) -> RedisObject): RedisObject
```
