package com.insects.designmode.builder.simplebuilder;

public class Director {

    public void construct(Builder builder) {

        builder.buildPartA(1);

        builder.buildPartB("test-test");

        builder.buildPartC(2);

    }



}
