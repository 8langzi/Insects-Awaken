package com.insects.getdata.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private String AOI_NAME;

    private String AOI_ID;

    private String PR_COUNT_PERSON;

    private String PTI0PERSON;

    private String PTI1PERSON;

    private String PTI2PERSON;

    private String PTI3PERSON;

    private String PTI4PERSON;

    private String PTI5PERSON;

    private String PTI6PERSON;

    private String PTI7PERSON;
}
