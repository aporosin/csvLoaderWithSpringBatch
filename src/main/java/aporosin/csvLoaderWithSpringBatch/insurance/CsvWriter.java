package aporosin.csvLoaderWithSpringBatch.insurance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Exports to CSV file fields of T annotated by @Column annotation
 *
 * @param <T>
 */
public class CsvWriter<T> {

    private static String DELIMITER = ";";
    DateTimeFormatter DEFAULT_DATEFORMATTER = DateTimeFormatter.ofPattern("yy/MM/dd");

    private String delimiter;

    public CsvWriter(){
        delimiter = DELIMITER;
    }
    public CsvWriter(String delimiter){
        this.delimiter = delimiter;
    }

    /*
        Exports all fields that have getters marked by @Column annotation (uses Column name as Csv column name)
     */
    public void writeCsvFile(Path csvFilePath, List<T> entities, Class<T> entityClass, boolean exportWithHeader) {
        writeCsvFile(csvFilePath, entities, entityClass, exportWithHeader, null);
    }

    /*
        Exports only columns defined columnNamesToExport list in defined order
        Column names must match those defined in @Column annotation
     */
    public void writeCsvFile(Path csvFilePath, List<T> entities, Class<T> entityClass, boolean exportWithHeader, List<String> columnNamesToExport) {

        SortedMap<String, Method> columnToGetter = new TreeMap<>();

        for (Method m : entityClass.getMethods()) {
            Column c = m.getAnnotation(Column.class);

            if (c != null)
                if(columnNamesToExport != null) {
                    if(containsIgnoreCase(columnNamesToExport, c.value()))
                    //if(columnNamesToExport.contains(c.value()))
                        columnToGetter.put(c.value(), m);
                }
                else
                    columnToGetter.putIfAbsent(c.value(), m);
        }

        String header = null;
        if(exportWithHeader) {
            header = columnToGetter.keySet().stream()
                    //.map(s -> String.format("\"%s\"", s))
                    .collect(Collectors.joining(delimiter));
        }

        try (BufferedWriter writer = Files.newBufferedWriter(csvFilePath)) {

            if(header != null) {
                writer.write(header);
                writer.newLine();
            }

            for( T entity: entities) {
                List<String> parts = new ArrayList<>();

                for(String columnName : columnToGetter.keySet()) {
                    Object valueToExport = columnToGetter.get(columnName).invoke(entity);
                    String valueToExportString = formatObjectForCsv(valueToExport);
                    parts.add(valueToExportString);
                }

                String line = parts.stream().collect(Collectors.joining(delimiter));
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private boolean containsIgnoreCase(List<String> columnNamesToExport, String value) {
        return columnNamesToExport.stream().filter(s -> s.equalsIgnoreCase(value)).findFirst().isPresent();
    }

    private String formatObjectForCsv(Object value) {

        String exportValue = null;
        if (value instanceof LocalDateTime)
                exportValue = ((LocalDateTime) value).format(DEFAULT_DATEFORMATTER);
            else if (value != null)
                exportValue = value.toString();

        return exportValue;
    }
}
