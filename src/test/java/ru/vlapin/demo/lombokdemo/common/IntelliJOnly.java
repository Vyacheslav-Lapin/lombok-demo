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
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * A custom annotation used to execute specific tests or methods only when running within IntelliJ IDEA.
 * This annotation includes an internal condition that evaluates the environment to verify whether
 * the testing process was initiated from IntelliJ IDEA.
 * <p>
 * The annotation uses an {@link IntelliJOnlyCondition} to check the stack trace for IntelliJ-specific
 * class names. If detected, the test is enabled; otherwise, it is disabled.
 * <p>
 * Usage:
 * - Apply this annotation to a test method to restrict its execution to IntelliJ IDEA environments.
 * <p>
 * An error is thrown if the annotation is applied incorrectly or cannot be located.
 *
 * @see <a href="https://www.javacodegeeks.com/2021/05/creating-a-junit-5-executioncondition.html">issue</a>
 */
@Retention(RUNTIME)
@SuppressWarnings("unused")
@Target({ANNOTATION_TYPE, METHOD})
@ExtendWith(IntelliJOnly.IntelliJOnlyCondition.class)
public @interface IntelliJOnly {

    @ExtensionMethod(value = Arrays.class,
                     suppressBaseMethods = false)
    class IntelliJOnlyCondition implements ExecutionCondition {
        /**
         * Evaluates condition based on runtime environment
         */
        @Override
        public @NonNull ConditionEvaluationResult evaluateExecutionCondition(@NotNull ExtensionContext context) {
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
