package aporosin.csvLoaderWithSpringBatch.insurance;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CashFlowIn extends AbstractInsuranceItem  {


    private BigDecimal amount;
    private String currencyCode;
    private CashFlowType cashFlowType;
    private LocalDateTime cashFlowTiming;
    private boolean discountedFlag;
    private BigDecimal discountedAmount;
    private String processingStatus;
    private LocalDateTime inputTime;
    private LocalDateTime creationTime; // record creation time in external system
    private String inputBy;
    private String taskID;
    private String remittingSystemId;
    private Long lpgId;
    private long inId;

    @Column("IN_ID")
    public long getInId() {
        return inId;
    }

    @Column("amount")
    public BigDecimal getAmount() {
        return amount;
    }

    @Column("currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @Column("cash_flow_type")
    public String getCashFlowTypeString() {
        return cashFlowType.name();
    }

    public void setCashFlowTypeString(String cashFlowTypeString) {
        cashFlowType = CashFlowType.valueOf(cashFlowTypeString);
    }

    public CashFlowType getCashFlowType() {
        return cashFlowType;
    }

    @Column("cash_flow_timing")
    public LocalDateTime getCashFlowTiming() {
        return cashFlowTiming;
    }

    @Column("discounted_flag")
    public boolean isDiscountedFlag() {
        return discountedFlag;
    }

    @Column("discount_amount")
    public BigDecimal getDiscountedAmount() {
        return discountedAmount;
    }

    @Column("processing_status")
    public String getProcessingStatus() {
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


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setCashFlowType(CashFlowType cashFlowType) {
        this.cashFlowType = cashFlowType;
    }

    public void setCashFlowTiming(LocalDateTime cashFlowTiming) {
        this.cashFlowTiming = cashFlowTiming;
    }

    public void setDiscountedFlag(boolean discountedFlag) {
        this.discountedFlag = discountedFlag;
    }

    public void setDiscountedAmount(BigDecimal discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public void setProcessingStatus(String processingStatus) {
        this.finalStatus = ProcessingStatus.forCode(processingStatus);
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

    public void setInId(long inId) {
        this.inId = inId;
    }

    @Column("lpg_id")
    public Long getLpgId() {
        return lpgId;
    }

    private ProcessingStatus finalStatus;


    public ProcessingStatus getFinalStatus() {

        return this.finalStatus;
    }

    public void setFinalStatus(ProcessingStatus status) {
        this.finalStatus = status;

    }

}
