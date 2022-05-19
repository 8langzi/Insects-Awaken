package com.insects.designmode.builder.other;

public class MigrantWorker {

    private String name;    //姓名

    private int age;        //年龄

    private String phone;   //电话

    private String gender;  //性别

    public MigrantWorker(){}

    public static MigrantWorker builder(){
        return new MigrantWorker();
    }

    public MigrantWorker name(String name){
        this.name = name;
        return this;
    }

    public MigrantWorker age(int age){
        this.age = age;
        return this;
    }

    public MigrantWorker phone(String phone){
        this.phone = phone;
        return this;
    }

    public MigrantWorker gender(String gender){
        this.gender = gender;
        return this;
    }

    public MigrantWorker build(){
        validateObject(this);
        return this;
    }

    private void validateObject(MigrantWorker migrantWorker) {
    }

    @Override
    public String toString() {
        return "MigrantWorker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}