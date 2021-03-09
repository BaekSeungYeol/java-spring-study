package me.seungyeol.tobisp.Chapter1.TemplateCallBack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SpringBootTest
class CalcSumTest {

    Calculator calculator;
    String numFilePath;

    @BeforeEach
    void setUp() {
        this.calculator = new Calculator();
        this.numFilePath = "C:\\Users\\백승열\\Desktop\\fil\\Studyy\\TobySpring\\src\\main\\resources\\numbers.txt";
    }



    @Test
    void sumOfNums() throws IOException {
        Assertions.assertThat(calculator.calcSum(this.numFilePath)).isEqualTo(10);
    }

    @Test
    void multiplyOfNumbers() throws IOException {
        Assertions.assertThat(calculator.calcMultiply(this.numFilePath)).isEqualTo(24);
    }


}