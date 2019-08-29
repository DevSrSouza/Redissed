# ![.](logo.png) Redissed

Redis Kotlin wrapper inspired on Exposed

## Getting started

### Gradle

```kotlin
dependencies {
    compile("br.com.devsrsouza:redissed:1.1.0")
}
```

### Maven

```xml
<dependency>
    <groupId>br.com.devsrsouza</groupId>
    <artifactId>redissed</artifactId>
    <version>1.1.0</version>
</dependency>
```

## Sample

```kotlin
import br.com.devsrsouza.redissed.RedisObject

class MyObject(database: String, commands: RedissedCommands) : RedisObject(database, commands) {
  var hello: String? by string()
  var hi: String by string("Hi")

  val other: OtherObject by obj { OtherObject(it, commands) }
}

class OtherObject(database: String, commands: RedissedCommands) : RedisObject(database, commands) {
  var something = string()

  var points: Int = int(0)
}

val database = "my:db"
val commands: RedissedCommands = myClient.redissed

val my = MyObject("$database:obj", commands)

my.hello = "Hello" // setting "Hello" to my:db:obj:hello
my.hi // getting my:db:obj:hi from Redis if not exist return default "Hi"

my.hello = null // removing data from my:db:obj:hello

my.other.something = "Anything" // setting "Anything" to my:db:obj:other:something
my.other.points = 10 // setting 10 to my:db:obj:other:points
```

## Supported Redis Clients

- Jedis
- Lettuce

### Jedis

```kotlin
import br.com.devsrsouza.redissed.clients.redissed
import br.com.devsrsouza.redissed.RedissedCommands

val jedis = Jedis("localhost", 6379)
jedis.connect()

val commands: RedissedCommands = jedis.redissed
```

### Lettuce

```kotlin
import br.com.devsrsouza.redissed.clients.redissed
import br.com.devsrsouza.redissed.RedissedCommands

val redis = RedisClient.create(RedisURI.create("localhost", 6379))
val conn = redis.connect()
val sync = conn.sync()

val commands: RedissedCommands = sync.redissed
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

### Expire
Auto expires a key

```kotlin
var your_key: String? by string().expire(5) // in seconds
```

You can use ``withExpire`` to tell the expiration time for a specific value or get the expiration time of a value
```kotlin
var my_key: Pair<String, Int> by string("my default key").withExpire()

my_key = "my new value expiring in 10 sec" to 10

// get the expiration time of a key
val (value, time) = my_key
// returns the expiration time in seconds or
// returns -2 if the key does not exist.
// returns -1 if the key exists but has no associated expire. 
```