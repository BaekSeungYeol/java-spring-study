package me.seungyeol.tobisp.Chapter1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class JUnitTest {

    static Set<JUnitTest> testObjects = new HashSet<>();


    @Test
    public void fileTest() {
        System.out.println("test2: "+getClass().getResource("test.css"));
    }

    @Test
    @DisplayName("test1")
    public void test1() {
        Assertions.assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
    }
    @Test
    @DisplayName("test2")
    public void test2() {
        Assertions.assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
    }
    @Test
    @DisplayName("test3")
    public void test3() {
        Assertions.assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
    }

}
