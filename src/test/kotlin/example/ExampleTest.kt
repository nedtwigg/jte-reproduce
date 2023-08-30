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
}