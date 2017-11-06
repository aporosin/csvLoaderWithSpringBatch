package aporosin.csvLoaderWithSpringBatch;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.function.BiFunction;

public class EnumPropertyEditor<T extends Enum<T>> extends PropertyEditorSupport {

    Class<T> enumClass;
    BiFunction<T, String, Boolean> comparer =  null;

    public EnumPropertyEditor(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    public EnumPropertyEditor(Class<T> enumClass, BiFunction<T, String, Boolean> enumFieldComparer) {
        this.enumClass = enumClass;
        this.comparer = enumFieldComparer;
    }

    @Override
    public String getAsText() {
        return (String) getValue();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {

            T[] enumConstants = enumClass.<T>getEnumConstants();

            for (T e : enumConstants) {
                if (e.toString().equals(text) || comparer != null && comparer.apply(e, text)) {
                    setValue(e);
                    return;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}