package com.insects.designmode.builder.simplebuilder;

public interface Builder {

    void buildPartA(int partA);

    void buildPartB(String partB);

    void buildPartC(int partC);

    Product getResult();

}
