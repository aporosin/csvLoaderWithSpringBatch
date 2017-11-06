package aporosin.csvLoaderWithSpringBatch;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomLocalDateTimeEditor extends PropertyEditorSupport {

    DateTimeFormatter DEFAULT_DATEFORMATTER = DateTimeFormatter.ofPattern("yy/MM/dd");

    private final boolean allowEmpty;
    private final int exactDateLength;

    public CustomLocalDateTimeEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
        this.exactDateLength = -1;
    }

    public CustomLocalDateTimeEditor(boolean allowEmpty, int exactDateLength) {
        this.allowEmpty = allowEmpty;
        this.exactDateLength = exactDateLength;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            setValue(null);
        } else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
            throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
        } else {
            try {
                // trick to parse date-only string into LocalDateTime (missing time part causes exception)
                // todo: check string format and allow parsing time as well
                LocalDate ld = LocalDate.parse(text, DEFAULT_DATEFORMATTER);
                LocalDateTime ldt = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());

                setValue(ldt);
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

    @Override
    public String getAsText() {
        LocalDateTime value = LocalDateTime.parse(String.valueOf(getValue()));
        return (value != null ? value.toString() : "");
    }
}