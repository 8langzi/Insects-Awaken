package com.insects.getdata.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private String PPP_ID;

    private String RPI_NAME;

    private String SCO_NAME;

    private String ECO_NAME;

    private String AOI_NAME;

    private String PTI_NAME;

    private String CTI_NAME;

    private String CER_NUM;

    private String PPP_GET_DATE;

    private String PPP_END_DATE;

    private String COUNTCER;

    private String COUNTCX;

    private String RNUM;
}
