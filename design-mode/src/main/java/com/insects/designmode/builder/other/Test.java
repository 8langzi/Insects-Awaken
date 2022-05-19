package com.insects.designmode.builder.other;

public class Test {
    public static void main(String[] args) {

        MigrantWorker migrantWorker1 = MigrantWorker.builder()
                .name("Spike")
                .age(27)
                .phone("1810000111")
                .gender("男")
                .build();
        System.out.println(migrantWorker1);
        MigrantWorker migrantWorker2 = MigrantWorker.builder()
                .name("Max")
                .age(7)
                .phone("1810000222")
                //没有性别
                .build();
        System.out.println(migrantWorker2);
        MigrantWorker migrantWorker3 = MigrantWorker.builder()
                .name("Mia")
                .age(17)
                //没有 电话
                .gender("女")
                .build();
        System.out.println(migrantWorker3);
        MigrantWorker migrantWorker4 = MigrantWorker.builder()
                .name("Mick")
                //没有 年龄
                //没有 电话
                //没有 性别
                .build();
        System.out.println(migrantWorker4);
    }

}
