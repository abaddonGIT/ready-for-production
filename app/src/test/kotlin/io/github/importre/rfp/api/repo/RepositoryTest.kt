package io.github.importre.rfp.api.repo

import io.github.importre.rfp.api.owner.Owner
import org.junit.Test
import kotlin.test.assertEquals

class RepositoryTest {

    @Test
    fun shouldBeDescOrderAfterSort() {
        val owner = Owner("", 0, "")
        val repos = arrayListOf(
                Repository(0, "first", "first", owner, "kotlin", 1, 0, 0),
                Repository(0, "second", "second", owner, "kotlin", 2, 0, 0),
                Repository(0, "third", "third", owner, "kotlin", 3, 0, 0)
        )
        repos.sort()

        assertEquals(repos[0].name, "third")
        assertEquals(repos[1].name, "second")
        assertEquals(repos[2].name, "first")
    }
}