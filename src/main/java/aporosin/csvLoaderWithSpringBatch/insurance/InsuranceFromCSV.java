package aporosin.csvLoaderWithSpringBatch.insurance;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InsuranceFromCSV {
    private long inId;
    private String entityCode;
    private LocalDateTime measurementDate1;
    private String portfolioCode;
    private String groupCode;
    private String policyCode;
    private Boolean initialMeasurementFlag;
    private BigDecimal riskAdjustmentRate;
    private String discountCurveCode;
    private BigDecimal discountRateAnnual;
    private String coverageCode;
    private LocalDateTime issueDate;
    private LocalDateTime contractStartDate;
    private CalculationFrequency calculationFrequency;

    public Boolean getInitialMeasurementFlag() {
        return initialMeasurementFlag;
    }

    public void setInitialMeasurementFlag(Boolean initialMeasurementFlag) {
        this.initialMeasurementFlag = initialMeasurementFlag;
    }

    public BigDecimal getRiskAdjustmentRate() {
        return riskAdjustmentRate;
    }

    public void setRiskAdjustmentRate(BigDecimal riskAdjustmentRate) {
        this.riskAdjustmentRate = riskAdjustmentRate;
    }

    public String getDiscountCurveCode() {
        return discountCurveCode;
    }

    public void setDiscountCurveCode(String discountCurveCode) {
        this.discountCurveCode = discountCurveCode;
    }

    public BigDecimal getDiscountRateAnnual() {
        return discountRateAnnual;
    }

    public void setDiscountRateAnnual(BigDecimal discountRateAnnual) {
        this.discountRateAnnual = discountRateAnnual;
    }

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

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDateTime getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(LocalDateTime contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public CalculationFrequency getCalculationFrequency() {
        return calculationFrequency;
    }

    public void setCalculationFrequency(CalculationFrequency calculationFrequency) {
        this.calculationFrequency = calculationFrequency;
    }

}
