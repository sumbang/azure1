package tv.wouri.azure.models;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface TPretMutualiste {
    @Value(value = "#{target.PRNUMERO}")
    Integer getPRNUMERO();
    @Value(value = "#{target.PRDATEPAI}")
    Date getPRDATEPAI();
    @Value(value = "#{target.MUMATRICULE}")
    String getMUMATRICULE();
    @Value(value = "#{target.MUNOM}")
    String getMUNOM();
    @Value(value = "#{target.MUPRENOM}")
    String getMUPRENOM();
    @Value(value = "#{target.PRMONTANTACCORDE}")
    Double getPRMONTANTACCORDE();
    @Value(value = "#{target.PRFRAISGESTION}")
    Double getPRFRAISGESTION();
    @Value(value = "#{target.NOM}")
    String getNOM();
    @Value(value = "#{target.TPLIBELLE}")
    String getTPLIBELLE();
    @Value(value = "#{target.REMBOURSE}")
    Double getREMBOURSE();
    @Value(value = "#{target.ENCOURS}")
    Double getENCOURS();
    @Value(value = "#{target.ECHEANCE}")
    String getECHEANCE();
}
