class A {
    companion object {
    }
}

class B {
    companion object Named {
    }
}

fun main(args: Array<String>) {
    val a = A
    B.Named
}