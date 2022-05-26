package com.insects.getdata.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetail {

    private String SWSB_ID;

    private String RPI_NAME;

    private String SCO_NAME;

    private String AOI_NAME;

    private String CER_NUM;

    private String PTI_NAME;

    private String ECO_NAME;

    private String OBTAIN_DATE;

    private String RPI_PHOTO_PATH;

    private String RPI_ID;

    private String PTI_ID;

    private String RPI_DELEGATE_START_DATE;

    private String RPI_DELEGATE_END_DATE;

    private String RPI_PRACTICE_GEOGRAPHICAL;

    private String ADI_NAME;

    private String AOI_COMPANY_WWW;

    private String AOI_CHARREF_NO;

    private String RPI_DP_LIST;

}
