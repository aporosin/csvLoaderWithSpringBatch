package aporosin.csvLoaderWithSpringBatch.insurance;

import java.time.LocalDateTime;

public class AbstractInsuranceItem {

    protected String entityCode;
    protected LocalDateTime measurementDate;
    protected String portfolioCode;
    protected String groupCode;
    protected String coverageCode;
    protected String policyCode;

    public AbstractInsuranceItem() {
        super();
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public void setMeasurementDate(LocalDateTime measurementDate) {
        this.measurementDate = measurementDate;
    }

    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public void setCoverageCode(String coverageCode) {
        this.coverageCode = coverageCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    @Column("entity_code")
    public String getEntityCode() {
        return entityCode;
    }

    @Column("measurement_date")
    public LocalDateTime getMeasurementDate() {
        return measurementDate;
    }

    @Column("portfolio_code")
    public String getPortfolioCode() {
        return portfolioCode;
    }

    @Column("group_code")
    public String getGroupCode() {
        return groupCode;
    }

    @Column("policy_code")
    public String getCoverageCode() {
        return coverageCode;
    }

    @Column("coverage_code")
    public String getPolicyCode() {
        return policyCode;
    }

}
