// "Change type of 'f' to '(Int, Int) -> (String) -> Int'" "true"
fun foo() {
    val f: () -> Long = {
        a: Int, b: Int ->
        val x = {s: String -> 42}
        if (true) x
        else if (true) x else {
            var y = 42
            if (true) x<caret> else x
        }
    }
}