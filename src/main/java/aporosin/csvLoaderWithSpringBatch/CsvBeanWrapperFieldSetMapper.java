package aporosin.csvLoaderWithSpringBatch;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;

import java.util.List;
import java.util.Properties;

public class CsvBeanWrapperFieldSetMapper<T> extends BeanWrapperFieldSetMapper<T> {

    private List<String> columnNames;
    private Class<? extends T> typeToCreate;

    public CsvBeanWrapperFieldSetMapper(List<String> columnNames)
    {
        this.columnNames = columnNames;
    }

    @Override
    public void setTargetType(Class<? extends T> type) {
        this.typeToCreate = type;
    }

    @Override
    public T mapFieldSet(FieldSet fs) throws BindException {
        T copy = getBean();
        Properties properties = fs.getProperties();

        DataBinder binder = createBinder(copy);
//        binder.bind(new MutablePropertyValues(getBeanProperties(copy, fs.getProperties())));

        if (binder.getBindingResult().hasErrors()) {
            throw new BindException(binder.getBindingResult());
        }

        return copy;
    }

    @SuppressWarnings("unchecked")
    private T getBean() {

        try {
            return typeToCreate.newInstance();
        }
        catch (InstantiationException e) {
            ReflectionUtils.handleReflectionException(e);
        }
        catch (IllegalAccessException e) {
            ReflectionUtils.handleReflectionException(e);
        }
        // should not happen
        throw new IllegalStateException("Internal error: could not create bean instance for mapping.");
    }
}
