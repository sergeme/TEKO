package test

import kotlin.reflect.KFunction0

fun main() {
    val test = Command("add", "[name]", ::wurst)
    println(test.call.call())
}

fun wurst() {
    println("wurst")
}

fun keks() {
    println("keks")
}

class Command(val name: String, val desc: String, val call: KFunction0<Unit>) {



}