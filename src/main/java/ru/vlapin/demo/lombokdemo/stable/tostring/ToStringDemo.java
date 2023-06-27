package ru.vlapin.demo.lombokdemo.stable.tostring;

import lombok.Setter;
import lombok.ToString;

/**
 * Нет необходимости запускать отладчик, чтобы просмотреть ваши поля: просто позвольте lombok
 * сгенерировать <code>toString</code> для вас!
 *
 * <p>Аннотирование класса с помощью @{@link lombok.ToString} приведет к тому, что lombok
 * сгенерирует реализацию метода <code>toString()</code>. Вы можете использовать параметры
 * конфигурации, чтобы указать, следует ли включать имена полей, но в остальном формат
 * фиксирован: имя класса, за которым следуют круглые скобки, содержащие поля, разделенные
 * запятыми.</p>
 *
 * <p>Например:
 * <pre>{@code
 *   MyClass(foo=123, bar=234)
 * }</pre></p>
 */
@ToString
@Setter // lombok.accessors.chain = true
public class ToStringDemo {
  private int x;
  private String s;

  // Зачем писать или генерировать?
//@Override
//@Generated
//@SuppressWarnings("all")
//@SuppressFBWarnings(
//       justification = "generated code")
//public java.lang.String toString() {
//  return "ToStringCallSuperDemo(x=" +
//         this.x + ", s=" + this.s + ")";
//}
}
