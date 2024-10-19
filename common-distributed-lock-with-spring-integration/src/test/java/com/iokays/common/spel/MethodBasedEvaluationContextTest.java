package com.iokays.common.spel;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Proxy;
import java.util.Objects;

interface Samer {
    boolean isSame(String name, Integer age);
}

/**
 * 来源： https://www.cnblogs.com/wt20/p/17659683.html
 * <p>
 * MethodBasedEvaluationContext是StandardEvaluationContext的一个子类。
 * 它主要是把方法参数也加到了变量中，使得用户可以直接通过#+参数名来获取值。常常用于解析注解中的SpEL表达式。
 * <p>
 * java编译后通过反射是拿不到真实的方法参数名称的，需要带上-parameters参数编译才行，不过Spring还另外基于ASM的方式解析字节码文件，
 * 获取字节码的本地方法表来获取方法真实参数。DefaultParameterNameDiscoverer实现类同时使用上面所说的两种方式来获取方法参数名。
 */
public class MethodBasedEvaluationContextTest {

    @Test
    public void test() {
        ExpressionParser expressionParser = new SpelExpressionParser();

        // 代理对象
        Samer samer = (Samer) Proxy.newProxyInstance(
                MethodBasedEvaluationContextTest.class.getClassLoader(),
                new Class[]{Samer.class},
                (proxy, method, args) -> {
                    //root 对象, 当表达式使用： #root, #this 需要
                    final var root = new Object();

                    final var evaluationContext = new MethodBasedEvaluationContext(root, method, args, new DefaultParameterNameDiscoverer());

                    // 通过#+方法参数名
                    Expression expression = expressionParser.parseExpression("#name");
                    Assertions.assertEquals("andy", expression.getValue(evaluationContext));

                    // 通过# + 内置的变量名+下标a0
                    expression = expressionParser.parseExpression("#a0");
                    Assertions.assertEquals("andy", expression.getValue(evaluationContext));

                    // 通过# + 内置的变量名+下标p0
                    expression = expressionParser.parseExpression("#p0");
                    Assertions.assertEquals("andy", expression.getValue(evaluationContext));

                    // 通过# + 内置的变量名+下标p0
                    expression = expressionParser.parseExpression("#p1");
                    Assertions.assertEquals(18, expression.getValue(evaluationContext));

                    return Objects.equals("andy", args[0]) && Objects.equals(18, args[1]);
                }
        );

        samer.isSame("andy", 18);
    }
}
