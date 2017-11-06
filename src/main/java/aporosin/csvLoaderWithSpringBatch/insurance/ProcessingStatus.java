package aporosin.csvLoaderWithSpringBatch.insurance;

public enum ProcessingStatus {
    Unprocessed("U"), Processed("P"), Errored("E"), Critical("X");


    private final String dbCode;

    ProcessingStatus(String code) {
        this.dbCode = code;
    }

    public static ProcessingStatus forCode(String code) {
        switch (code) {
            case "U":
                return Unprocessed;
            case "P":
                return Processed;
            case "E":
                return Errored;
            case "X":
                return Critical;
        }
        return null;
    }

    public String getCode() {
        return this.dbCode;
    }
}
