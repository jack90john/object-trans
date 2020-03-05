package object.trans;

import java.lang.annotation.*;

/**
 * 目标对象注解
 *
 * @author jack
 * @version 1.1.1
 * @since 1.0.0.RELEASE
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TransTarget {

    String value();   //目标对象变量名

}
