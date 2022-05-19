package com.insects.designmode.builder.simplebuilder;

public class ConcreteBuilder implements Builder {

    private int partA;

    private String partB;

    private int partC;

    @Override

    public void buildPartA(int partA) {

        this.partA = partA;

    }

    @Override

    public void buildPartB(String partB) {

        this.partB = partB;

    }

    @Override

    public void buildPartC(int partC) {

        this.partC = partC;

    }

    @Override
    public Product getResult(){

        return new Product(partA,partB,partC);

    }

}
