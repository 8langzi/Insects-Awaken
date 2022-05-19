package com.insects.designmode.builder.simplebuilder;

public class Test {

    public static void main(String[] args) {
        Director director = new Director();
        Builder builder = new ConcreteBuilder();
        director.construct(builder);
        System.out.println(builder.getResult());
    }

}
