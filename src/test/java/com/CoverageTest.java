package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class CoverageTest {

// 根据你的工程实际获取实例，这里假设有无参构造
    private final Main calculator = new Main();

    @Test
    @DisplayName("正常相加：2 + 3 = 5（命中try成功路径与INFO日志）")
    void add_shouldReturnSum_whenBothNonNegativeAndNoOverflow() {
        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    @DisplayName("边界不溢出：Integer.MAX_VALUE + 0 = MAX_VALUE")
    void add_boundaryNoOverflow_maxPlusZero() {
        int result = calculator.add(Integer.MAX_VALUE, 0);
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    @DisplayName("a<0 触发 IllegalArgumentException（命中负数分支与catch）")
    void add_shouldThrowIAE_whenANegative() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add(-1, 5)
        );
        assertTrue(ex.getMessage().contains("non-negative"));
    }

    @Test
    @DisplayName("b<0 触发 IllegalArgumentException（命中负数分支与catch）")
    void add_shouldThrowIAE_whenBNegative() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add(5, -1)
        );
        assertTrue(ex.getMessage().contains("non-negative"));
    }

    @Test
    @DisplayName("溢出：MAX_VALUE + 1 触发 ArithmeticException（命中溢出分支与catch）")
    void add_shouldThrowAE_whenOverflow() {
        ArithmeticException ex = assertThrows(
                ArithmeticException.class,
                () -> calculator.add(Integer.MAX_VALUE, 1)
        );
        assertTrue(ex.getMessage().contains("overflow"));
    }

}
