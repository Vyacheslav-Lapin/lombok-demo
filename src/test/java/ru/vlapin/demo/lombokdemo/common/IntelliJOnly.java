package ru.vlapin.demo.lombokdemo.common;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Optional;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * @see <a href="https://www.javacodegeeks.com/2021/05/creating-a-junit-5-executioncondition.html">issue</a>
 */
@Retention(RUNTIME)
@Target({ANNOTATION_TYPE, METHOD})
@ExtendWith(IntelliJOnly.IntelliJOnlyCondition.class)
public @interface IntelliJOnly {

    @ExtensionMethod(value = Arrays.class,
                     suppressBaseMethods = false)
    class IntelliJOnlyCondition implements ExecutionCondition {
        @Override
        public ConditionEvaluationResult evaluateExecutionCondition(@NotNull ExtensionContext context) {
            val method = context.getRequiredTestMethod();
            Optional.ofNullable(method.getDeclaredAnnotation(IntelliJOnly.class))
                    .orElseThrow(() -> new ExtensionConfigurationException(
                            "Could not find @%s annotation on the method %s".formatted(
                                    IntelliJOnly.class,
                                    method)));
            val isIntelliJStarted = new Exception().getStackTrace().stream()
                                                   .map(StackTraceElement::getClassName)
                                                   .anyMatch(className -> className.contains("Idea"));
            return isIntelliJStarted ?
                           ConditionEvaluationResult.enabled("started from IntelliJ")
                           : ConditionEvaluationResult.disabled("not started from IntelliJ");
        }
    }
}
