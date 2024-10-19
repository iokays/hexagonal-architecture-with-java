package com.iokays.common.spel;

import com.iokays.common.spel.entity.Inventor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpelSampleTest {

    /**
     * SpelExpressionParser:
     * 可以将字符串形式的 SpEL 表达式解析为 SpelExpression 对象。
     * 解析后的 SpelExpression 对象可以用于在运行时评估表达式的结果。
     */
    @Test
    void testValue() {
        final var parser = new SpelExpressionParser();
        //字符串
        var expression = parser.parseExpression("'Hello World'");
        String msgStr = (String) expression.getValue();
        assertEquals("Hello World", msgStr);

        //调用方法
        expression = parser.parseExpression("'Hello World'.concat('!')");
        msgStr = (String) expression.getValue();
        assertEquals("Hello World!", msgStr);

        //bytes
        expression = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) expression.getValue();
        assertArrayEquals(bytes, "Hello World".getBytes());

        //嵌套访问：火车头
        expression = parser.parseExpression("'Hello World'.bytes.length");
        Integer length = (Integer) expression.getValue();
        Assertions.assertEquals(length, "Hello World".getBytes().length);

        //构造函数 toUpperCase可以不用加()
        expression = parser.parseExpression("new String('Hello World').toUpperCase()");
        msgStr = (String) expression.getValue();
        Assertions.assertEquals(msgStr, "HELLO WORLD");
    }

    /**
     * 测试根对象
     */
    @Test
    void testRootObject() {
        final var c = new GregorianCalendar();
        c.set(1956, Calendar.AUGUST, 9);

        final var tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
        final var parser = new SpelExpressionParser();

        // test value
        Expression exp = parser.parseExpression("name"); // Parse name as an expression
        String name = (String) exp.getValue(tesla);
        Assertions.assertEquals(name, "Nikola Tesla");

        //在SpEL中，== 运算符对于引用类型的比较实际上是调用了 equals 方法。
        // 这对于大多数场景来说是非常有用的，因为它允许你进行更灵活的值比较。
        exp = parser.parseExpression("name == new String('Nikola Tesla')");
        Boolean result = exp.getValue(tesla, Boolean.class);
        assertEquals(Boolean.TRUE, result);
        assertSame(name, "Nikola Tesla");
        assertNotSame(name, new String("Nikola Tesla"));
    }

    class Simple {
        public List<Boolean> booleanList = new ArrayList<>();
    }
}
