package tv.wouri.azure.models;

import org.springframework.beans.factory.annotation.Value;

public interface TSituationMutualiste {
    @Value(value = "#{target.ANNEE}")
    String getANNEE();
    @Value(value = "#{target.R_JANV}")
    Double getR_JANV();
    @Value(value = "#{target.S_JANV}")
    Double getS_JANV();
    @Value(value = "#{target.R_FEV}")
    Double getR_FEV();
    @Value(value = "#{target.S_FEV}")
    Double getS_FEV();
    @Value(value = "#{target.R_MARS}")
    Double getR_MARS();
    @Value(value = "#{target.S_MARS}")
    Double getS_MARS();
    @Value(value = "#{target.R_AVRIL}")
    Double getR_AVRIL();
    @Value(value = "#{target.S_AVRIL}")
    Double getS_AVRIL();
    @Value(value = "#{target.R_MAI}")
    Double getR_MAI();
    @Value(value = "#{target.S_MAI}")
    Double getS_MAI();
    @Value(value = "#{target.R_JUIN}")
    Double getR_JUIN();
    @Value(value = "#{target.S_JUIN}")
    Double getS_JUIN();
    @Value(value = "#{target.R_JUIL}")
    Double getR_JUIL();
    @Value(value = "#{target.S_JUIL}")
    Double getS_JUIL();
    @Value(value = "#{target.R_AOUT}")
    Double getR_AOUT();
    @Value(value = "#{target.S_AOUT}")
    Double getS_AOUT();
    @Value(value = "#{target.R_SEPT}")
    Double getR_SEPT();
    @Value(value = "#{target.S_SEPT}")
    Double getS_SEPT();
    @Value(value = "#{target.R_OCT}")
    Double getR_OCT();
    @Value(value = "#{target.S_OCT}")
    Double getS_OCT();
    @Value(value = "#{target.R_NOV}")
    Double getR_NOV();
    @Value(value = "#{target.S_NOV}")
    Double getS_NOV();
    @Value(value = "#{target.R_DECE}")
    Double getR_DECE();
    @Value(value = "#{target.S_DECE}")
    Double getS_DECE();
    @Value(value = "#{target.TOTALRETRAITE}")
    Double getTOTALRETRAITE();
    @Value(value = "#{target.TOTALSECOURS}")
    Double getTOTALSECOURS();
    @Value(value = "#{target.TOTALCOTISE}")
    Double getTOTALCOTISE();
}
