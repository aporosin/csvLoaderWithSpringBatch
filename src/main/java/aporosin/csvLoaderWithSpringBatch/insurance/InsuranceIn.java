package aporosin.csvLoaderWithSpringBatch.insurance;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class InsuranceIn {
    private long inId;
    private LocalDateTime issueDate;
    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;
    private LocalDateTime coverageStartDate;
    private LocalDateTime coverageEndDate;
    private CalculationFrequency calculationFrequency;
    private String currencyCode;
    private BigDecimal discountRateAnnual;
    private String discountCurveCode;
    private BigDecimal riskAdjustmentRate;
    private String riskCurveCode;
    private BigDecimal riskAdjustmentTotalAmount;

    private LocalDateTime inputTime;
    private LocalDateTime creationTime;// record creation time in external system
    private String inputBy;
    private String taskID;
    private String remittingSystemId;
    private Long lpgId;
    private Boolean initialMeasurementFlag;

    public void setInId(long inId) {
        this.inId = inId;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public void setContractStartDate(LocalDateTime contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public void setContractEndDate(LocalDateTime contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public void setCoverageStartDate(LocalDateTime coverageStartDate) {
        this.coverageStartDate = coverageStartDate;
    }

    public void setCoverageEndDate(LocalDateTime coverageEndDate) {
        this.coverageEndDate = coverageEndDate;
    }

    public void setCalculationFrequency(CalculationFrequency calculationFrequency) {
        this.calculationFrequency = calculationFrequency;
    }


    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setDiscountRateAnnual(BigDecimal discountRateAnnual) {
        this.discountRateAnnual = discountRateAnnual;
    }

    public void setDiscountCurveCode(String discountCurveCode) {
        this.discountCurveCode = discountCurveCode;
    }

    public void setRiskAdjustmentRate(BigDecimal riskAdjustmentRate) {
        this.riskAdjustmentRate = riskAdjustmentRate;
    }

    public void setRiskCurveCode(String riskCurveCode) {
        this.riskCurveCode = riskCurveCode;
    }

    public void setRiskAdjustmentTotalAmount(BigDecimal riskAdjustmentTotalAmount) {
        this.riskAdjustmentTotalAmount = riskAdjustmentTotalAmount;
    }


    public void setInputTime(LocalDateTime inputTime) {
        this.inputTime = inputTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void setInputBy(String inputBy) {
        this.inputBy = inputBy;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public void setRemittingSystemId(String remittingSystemId) {
        this.remittingSystemId = remittingSystemId;
    }

    public void setLpgId(Long lpgId) {
        this.lpgId = lpgId;
    }

    public long getInId() {
        return inId;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public LocalDateTime getContractStartDate() {
        return contractStartDate;
    }

    public LocalDateTime getContractEndDate() {
        return contractEndDate;
    }

    public LocalDateTime getCoverageStartDate() {
        return coverageStartDate;
    }

    public LocalDateTime getCoverageEndDate() {
        return coverageEndDate;
    }

    public String getCalculationFrequencyString() {
        return calculationFrequency.name();
    }

    public void setCalculationFrequencyString(String calculationFrequencyString) {
        CalculationFrequency.valueOf(calculationFrequencyString);
    }

    public CalculationFrequency getCalculationFrequency() {
        return calculationFrequency;
    }


    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getDiscountRateAnnual() {
        return discountRateAnnual;
    }

    public String getDiscountCurveCode() {
        return discountCurveCode;
    }

    public BigDecimal getRiskAdjustmentRate() {
        return riskAdjustmentRate;
    }

    public String getRiskCurveCode() {
        return riskCurveCode;
    }

    public BigDecimal getRiskAdjustmentTotalAmount() {
        return riskAdjustmentTotalAmount;
    }



    public LocalDateTime getInputTime() {
        return inputTime;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getInputBy() {
        return inputBy;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getRemittingSystemId() {
        return remittingSystemId;
    }

    public Long getLpgId() {
        return lpgId;
    }

    public Boolean getInitialMeasurementFlag() {
        return initialMeasurementFlag;
    }

    public void setInitialMeasurementFlag(Boolean initialMeasurementFlag) {
        this.initialMeasurementFlag = initialMeasurementFlag;
    }


}
