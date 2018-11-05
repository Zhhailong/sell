package com.imooc.sell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.imooc.sell.dataobject.mapper")
public class SellApplication {

    public static void main(String[] args) {

        SpringApplication.run(SellApplication.class, args);

        Runnable r2 = () -> System.out.println("Hello Lambda!!!!!!!!!!!!!!!!!!");

        r2.run();

        A a = (int x,int y) -> x+y;
        System.out.print(a.sum(5,6)+">>>>>>>>>>>>>>>>>>>>>>>>>");

    }

    interface A{
        int sum(int a,int b);
    }

}
