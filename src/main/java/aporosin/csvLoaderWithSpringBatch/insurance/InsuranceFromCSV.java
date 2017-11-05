package aporosin.csvLoaderWithSpringBatch.insurance;

import java.time.LocalDateTime;

public class InsuranceFromCSV {
    private long inId;
    private String entityCode;
    private LocalDateTime measurementDate1;
    private String portfolioCode;
    private String groupCode;
    private String policyCode;

    public long getInId() {
        return inId;
    }

    public void setInId(long inId) {
        this.inId = inId;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public LocalDateTime getMeasurementDate1() {
        return measurementDate1;
    }

    public void setMeasurementDate1(LocalDateTime measurementDate1) {
        this.measurementDate1 = measurementDate1;
    }

    public String getPortfolioCode() {
        return portfolioCode;
    }

    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    public String getCoverageCode() {
        return coverageCode;
    }

    public void setCoverageCode(String coverageCode) {
        this.coverageCode = coverageCode;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getCalculationFrequency() {
        return calculationFrequency;
    }

    public void setCalculationFrequency(String calculationFrequency) {
        this.calculationFrequency = calculationFrequency;
    }

    private String coverageCode;
    private String issueDate;
    private String contractStartDate;
    private String calculationFrequency;

}
