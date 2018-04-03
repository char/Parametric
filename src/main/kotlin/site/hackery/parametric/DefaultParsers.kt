package site.hackery.parametric

import site.hackery.parametric.parser.*

fun addDefaultParsers(parserRegistry: MutableMap<Class<*>, ArgumentParser<*>>) {
    parserRegistry[String::class.java] = StringParser()

    parserRegistry[Int::class.java] = IntParser()
    parserRegistry[Float::class.java] = FloatParser()
    parserRegistry[Double::class.java] = DoubleParser()
    parserRegistry[Short::class.java] = ShortParser()
    parserRegistry[Long::class.java] = LongParser()
    parserRegistry[Char::class.java] = CharParser()


    // Java interop
    parserRegistry[java.lang.Integer::class.java] = parserRegistry[Int::class.java]!!
    parserRegistry[java.lang.Float::class.java] = parserRegistry[Float::class.java]!!
    parserRegistry[java.lang.Double::class.java] = parserRegistry[Double::class.java]!!
    parserRegistry[java.lang.Short::class.java] = parserRegistry[Short::class.java]!!
    parserRegistry[java.lang.Long::class.java] = parserRegistry[Long::class.java]!!
    parserRegistry[java.lang.Character::class.java] = parserRegistry[Char::class.java]!!
}
