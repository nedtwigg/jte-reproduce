package example

import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.output.Utf8ByteOutput
import gg.jte.resolve.DirectoryCodeResolver
import org.junit.Assert
import org.junit.Test
import java.nio.charset.StandardCharsets
import java.nio.file.Paths

class ExampleTest {
    private fun renderDynamic(template: String) : String {
        val codeResolver = DirectoryCodeResolver(Paths.get("src/main/jte"))
        val engine = TemplateEngine.create(codeResolver, ContentType.Plain)
        val output = Utf8ByteOutput()
        engine.render(template, mapOf(), output)
        return String(output.toByteArray(), StandardCharsets.UTF_8)
    }

    @Test
    fun rootsWork() {
        Assert.assertEquals("J", renderDynamic("example/RootJ.jte"))
        Assert.assertEquals("K", renderDynamic("example/RootK.kte"))
    }

    @Test
    fun nomixingWorks() {
        Assert.assertEquals("I am J, and I need J", renderDynamic("example/J_needs_J.jte"))
        Assert.assertEquals("I am K, and I need K", renderDynamic("example/K_needs_K.kte"))
    }

    @Test
    fun K_needs_J() {
        Assert.assertEquals("I am K, but I need J", renderDynamic("example/K_needs_J.kte"))
    }

    @Test
    fun J_needs_K() {
        Assert.assertEquals("I am J, but I need K", renderDynamic("example/J_needs_K.jte"))
    }
}