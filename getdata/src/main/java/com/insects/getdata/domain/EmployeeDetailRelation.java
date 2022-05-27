package com.insects.getdata.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailRelation {

    private String PPP_ID;

    private String RPI_ID;

    private String STATE;

}
