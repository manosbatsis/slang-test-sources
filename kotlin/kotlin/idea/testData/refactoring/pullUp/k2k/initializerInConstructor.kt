open class A

class <caret>B: A {
    // INFO: {"checked": "true"}
    val n: Int

    constructor(a: Int) {
        n = a + 1
    }
}