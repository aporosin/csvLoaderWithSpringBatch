package aporosin.csvLoaderWithSpringBatch;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class CustomLocalDateTimeEditor extends PropertyEditorSupport {

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
                setValue(LocalDateTime.parse(text));
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