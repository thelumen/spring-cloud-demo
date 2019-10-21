package com.ri.eurekaclientorder.test

class Outer {                  // 外部类
    class Nested {             // 嵌套类
        fun foo() = bar
    }

    companion object {
        const val bar: Int = 1
    }
}

class Outer2 {                  // 外部类
    inner class Nested {             // 嵌套类
        fun foo() = bar
    }

     val bar: Int = 1
}

fun main() {
    val demo = Outer.Nested().foo() // 调用格式：外部类.嵌套类.嵌套类方法/属性
    println(demo)    // == 2
    val t = Outer2().Nested().foo()
    println(t)
}