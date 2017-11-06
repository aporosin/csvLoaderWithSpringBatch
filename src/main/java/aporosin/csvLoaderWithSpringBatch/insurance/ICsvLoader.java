package aporosin.csvLoaderWithSpringBatch.insurance;

import java.io.File;
import java.util.List;

public interface ICsvLoader<T> {
    public List<T> loadCsv(File csvFile, Class<T> targetType);
}
