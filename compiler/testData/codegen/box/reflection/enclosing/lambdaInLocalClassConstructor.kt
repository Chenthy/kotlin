// IGNORE_BACKEND: JS_IR
// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS, NATIVE

// WITH_REFLECT

open class C

fun box(): String {
    class L : C() {
        val a: Any

        init {
            a = {}
        }
    }
    val l = L()

    val javaClass = l.a.javaClass
    val enclosingMethod = javaClass.getEnclosingConstructor()!!.getName()
    if (enclosingMethod != "LambdaInLocalClassConstructorKt\$box\$L") return "ctor: $enclosingMethod"

    val enclosingClass = javaClass.getEnclosingClass()!!.getName()
    if (enclosingClass != "LambdaInLocalClassConstructorKt\$box\$L") return "enclosing class: $enclosingClass"

    if (enclosingMethod != enclosingClass) return "$enclosingClass != $enclosingMethod"

    val declaringClass = javaClass.getDeclaringClass()
    if (declaringClass != null) return "anonymous function has a declaring class: $declaringClass"

    return "OK"
}
