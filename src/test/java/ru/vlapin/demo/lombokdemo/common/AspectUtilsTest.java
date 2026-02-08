package ru.vlapin.demo.lombokdemo.common;

import static java.lang.annotation.RetentionPolicy.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.annotation.Retention;
import java.lang.reflect.Method;
import lombok.SneakyThrows;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * Test class for {@link AspectUtils}.
 * <p>
 * This class contains unit tests for the {@code getImplMethod} method, which retrieves the implemented method (non-abstract) from a
 * {@link JoinPoint} representing an AOP invocation.
 */
@DisplayNameGeneration(ReplaceCamelCase.class)
class AspectUtilsTest {

  @Test
  void testGetImplMethod_withNonAbstractMethod_returnsMethod() throws NoSuchMethodException {
    // Arrange
    val methodSignature = mock(MethodSignature.class);
    val method = TestClass.class.getDeclaredMethod("implementedMethod");
    when(methodSignature.getMethod()).thenReturn(method);
    when(methodSignature.getName()).thenReturn(method.getName());

    val joinPoint = mock(JoinPoint.class);
    when(joinPoint.getSignature()).thenReturn(methodSignature);

    // Act
    val result = AspectUtils.getImplMethod(joinPoint);

    // Assert
    assertTrue(result.isPresent(), "Method should be retrieved successfully.");
    assertEquals(method, result.get(), "Retrieved method should match the test method.");
  }

  @Test
  void testGetImplMethod_withAbstractMethod_returnsImplementedMethod() throws NoSuchMethodException {
    // Arrange
    val METHOD_NAME = "abstractMethod";

    val signature = mock(MethodSignature.class);
    val abstractDeclaration = AbstractClass.class.getDeclaredMethod(METHOD_NAME);
    when(signature.getMethod()).thenReturn(abstractDeclaration);
    when(signature.getName()).thenReturn(METHOD_NAME);

    val joinPoint = mock(JoinPoint.class);
    when(joinPoint.getSignature()).thenReturn(signature);

    val target = new TestClass();
    when(joinPoint.getTarget()).thenReturn(target);

    val expectedImplMethod = TestClass.class.getDeclaredMethod(METHOD_NAME);

    // Act
    val implMethodOpt = AspectUtils.getImplMethod(joinPoint);

    // Assert
    assertTrue(implMethodOpt.isPresent(), "Implemented method should be retrieved for an abstract method.");
    assertEquals(expectedImplMethod, implMethodOpt.get(),
        "Retrieved method should be the concrete implementation of the abstract method.");
  }

// ... existing code ...

  @Test
  void testGetImplMethod_withNonMethodSignature_returnsEmptyOptional() {
    // Arrange
    val joinPoint = mock(JoinPoint.class);
    val nonMethodSignature = mock(Signature.class); // Signature, но НЕ MethodSignature
    when(joinPoint.getSignature()).thenReturn(nonMethodSignature);

    // Act
    val result = AspectUtils.getImplMethod(joinPoint);

    // Assert
    assertTrue(result.isEmpty(), "Result should be empty when the signature is not a method signature.");
    verify(joinPoint, never()).getTarget(); // лишних обращений быть не должно
  }

  @Test
  void testGetImplMethod_withNullSignature_returnsEmptyOptional() {
    // Arrange
    val joinPoint = mock(JoinPoint.class);
    when(joinPoint.getSignature()).thenReturn(null);

    // Act
    val result = AspectUtils.getImplMethod(joinPoint);

    // Assert
    assertTrue(result.isEmpty(), "Result should be empty when the signature is null.");
  }

  @SneakyThrows
  @Test
  void testDestructWithAnnotationAndMethodReturnsCorrectValues() {
    // Arrange
    val annotationClass = TestAnnotation.class;
    val targetMethod = TestClass.class.getDeclaredMethod("implementedMethod");
    val joinPoint = mock(JoinPoint.class);
    val methodSignature = mock(MethodSignature.class);

    when(methodSignature.getMethod()).thenReturn(targetMethod);
    when(joinPoint.getSignature()).thenReturn(methodSignature);
    when(joinPoint.getTarget()).thenReturn(new TestClass());

    // Act
    val result = AspectUtils.destruct(joinPoint, annotationClass,
        (annotation, method) -> method.getName() + " with annotation: " + annotation.value());

    // Assert
    assertEquals("implementedMethod with annotation: testValue", result,
        "destruct with annotation and method resolved correctly");
  }

  @SneakyThrows
  @Test
  void testDestructWithMethodReturnsCorrectValues() {
    // Arrange
    val targetMethod = TestClass.class.getDeclaredMethod("implementedMethod");
    val joinPoint = mock(JoinPoint.class);
    val methodSignature = mock(MethodSignature.class);

    when(methodSignature.getMethod()).thenReturn(targetMethod);
    when(joinPoint.getSignature()).thenReturn(methodSignature);
    when(joinPoint.getTarget()).thenReturn(new TestClass());

    // Act
    val result = AspectUtils.destruct(joinPoint, Method::getName);

    // Assert
    assertEquals("implementedMethod", result, "destruct with method resolved correctly");
  }

  @Test
  void testDestructHandlesMissingAnnotationOrMethodGracefully() {
    // Arrange
    val joinPoint = mock(JoinPoint.class);
    when(joinPoint.getSignature()).thenReturn(null);

    // Act & Assert
    assertThrows(Exception.class,
        () -> AspectUtils.destruct(joinPoint, TestAnnotation.class, (annotation, method) -> null),
        "Should throw exception when method is not resolvable");
    assertThrows(Exception.class,
        () -> AspectUtils.destruct(joinPoint, Method::getName),
        "Should throw exception when method is not resolvable");
  }

  // Classes for mocking and testing
  @Retention(RUNTIME)
  @interface TestAnnotation {

    String value() default "testValue";
  }

  abstract static class AbstractClass {

    public abstract void abstractMethod();
  }

  static class TestClass extends AbstractClass {

    @TestAnnotation
    @Override
    public void abstractMethod() {
      // Concrete implementation
    }

    @TestAnnotation
    public void implementedMethod() {
      // Non-abstract method for testing
    }
  }
}
