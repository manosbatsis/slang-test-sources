fun some(f: () -> Unit) { f() }

fun test() {
    some<caret>
}

// ELEMENT: some
// CHAR: {