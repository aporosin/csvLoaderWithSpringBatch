package aporosin.csvLoaderWithSpringBatch.insurance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    /**
     * @return name of the column to be mapped
     */
    String value();
}
