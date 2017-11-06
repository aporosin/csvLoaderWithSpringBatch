package aporosin.csvLoaderWithSpringBatch.insurance;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class InsuranceIn extends AbstractInsuranceItem  {

    private long inId;
    private LocalDateTime issueDate;
    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;
    private LocalDateTime coverageStartDate;
    private LocalDateTime coverageEndDate;
    private CalculationFrequency calculationFrequency;
    private MeasurementFrequency measurementFrequency;
    private MeasurementModelType measurementModelType;
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

    public void setMeasurementFrequency(MeasurementFrequency measurementFrequency) {
        this.measurementFrequency = measurementFrequency;
    }

    public void setMeasurementModelType(MeasurementModelType measurementModelType) {
        this.measurementModelType = measurementModelType;
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

    public void setProcessingStatusString(String processingStatusString) {

        this.processingStatus = ProcessingStatus.forCode(processingStatusString);
    }

    public void setProcessingStatus(ProcessingStatus processingStatus) {

        this.processingStatus = processingStatus;

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

    @Column("IN_ID")
    public long getInId() {
        return inId;
    }

    @Column("issue_date")
    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    @Column("contract_start_date")
    public LocalDateTime getContractStartDate() {
        return contractStartDate;
    }

    @Column("contract_end_date")
    public LocalDateTime getContractEndDate() {
        return contractEndDate;
    }

    @Column("coverage_start_date")
    public LocalDateTime getCoverageStartDate() {
        return coverageStartDate;
    }

    @Column("coverage_end_date")
    public LocalDateTime getCoverageEndDate() {
        return coverageEndDate;
    }

    @Column("calculation_frequency")
    public String getCalculationFrequencyString() {
        return calculationFrequency.name();
    }

    public void setCalculationFrequencyString(String calculationFrequencyString) {
        CalculationFrequency.valueOf(calculationFrequencyString);
    }

    public CalculationFrequency getCalculationFrequency() {
        return calculationFrequency;
    }

    public MeasurementFrequency getMeasurementFrequency() {
        return measurementFrequency;
    }

    @Column("measurement_frequency")
    public String getMeasurementFrequencyString() {
        return measurementFrequency.name();
    }

    public void setMeasurementFrequencyString(String measurementFrequencyString) {
        measurementFrequency = MeasurementFrequency.valueOf(measurementFrequencyString);
    }

    @Column("measurement_model_type")
    public String getMeasurementModelTypeString() {
        return measurementModelType.name();
    }

    public void setMeasurementModelTypeString(String measurementModelTypeString) {
        measurementModelType = MeasurementModelType.valueOf(measurementModelTypeString);
    }

    public MeasurementModelType getMeasurementModelType() {
        return measurementModelType;
    }

    @Column("currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @Column("discount_rate_annual")
    public BigDecimal getDiscountRateAnnual() {
        return discountRateAnnual;
    }

    @Column("discount_curve_code")
    public String getDiscountCurveCode() {
        return discountCurveCode;
    }

    @Column("risk_adjustment_rate")
    public BigDecimal getRiskAdjustmentRate() {
        return riskAdjustmentRate;
    }

    @Column("risk_curve_code")
    public String getRiskCurveCode() {
        return riskCurveCode;
    }

    @Column("risk_adjustment_total_amount")
    public BigDecimal getRiskAdjustmentTotalAmount() {
        return riskAdjustmentTotalAmount;
    }

    @Column("processing_status")
    public String getProcessingStatusString() {
        return processingStatus.getCode();
    }

    public ProcessingStatus getProcessingStatus() {
        return processingStatus;
    }

    @Column("input_time")
    public LocalDateTime getInputTime() {
        return inputTime;
    }

    @Column("creation_time")
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Column("input_by")
    public String getInputBy() {
        return inputBy;
    }

    @Column("task_id")
    public String getTaskID() {
        return taskID;
    }

    @Column("remitting_system_id")
    public String getRemittingSystemId() {
        return remittingSystemId;
    }

    @Column("lpg_id")
    public Long getLpgId() {
        return lpgId;
    }

    @Column("initial_measurement_flag")
    public Boolean getInitialMeasurementFlag() {
        return initialMeasurementFlag;
    }

    public void setInitialMeasurementFlag(Boolean initialMeasurementFlag) {
        this.initialMeasurementFlag = initialMeasurementFlag;
    }

    private ProcessingStatus processingStatus;

    public ProcessingStatus getFinalStatus() {

        return this.processingStatus;
    }

    public void setFinalStatus(ProcessingStatus status) {
        this.processingStatus = status;

    }

}
