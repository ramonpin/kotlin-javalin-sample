@file:JvmName("Main")

package orange.mkat.rest

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import io.javalin.Javalin

import java.util.Date

val moshi = Moshi.Builder()
  .add(KotlinJsonAdapterFactory())
  .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
  .build()

data class Person(val name: String, val age: Int)
fun Person.json() = moshi.adapter(Person::class.java).toJson(this)

fun main(args: Array<String>) {
    val app = Javalin.start(7000)
    app.get("/") { ctx -> ctx.result("Hello World") }
    app.get("/person") { ctx -> ctx.result(Person("Tobías Perez Lopez", 34).json()) }
}

