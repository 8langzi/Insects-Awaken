package com.insects.designmode.builder.other;

public class MigrantWorkerOld {

    private String name;    //姓名

    private int age;        //年龄

    private String phone;   //电话

    private String gender;  //性别

    public MigrantWorkerOld(String name, int age, String phone, String gender) {

        this.name = name;

        this.age = age;

        this.phone = phone;

        this.gender = gender;

    }

    public MigrantWorkerOld(String name, int age, String phone) {

        this.name = name;

        this.age = age;

        this.phone = phone;

    }

    public MigrantWorkerOld(String name, int age) {

        this.name = name;

        this.age = age;

    }



}

