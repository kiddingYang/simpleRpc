package com.gs.rpc.core.serializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

public class JdkSerializerTest {

    @Before
    public void init() {

    }


    @Test
    public void serialize() {
        JdkSerializer jdkSerializer = new JdkSerializer();
        byte[] jyangs = jdkSerializer.serialize(new Person("jyang", 18));
        Person deserialize = jdkSerializer.deserialize(jyangs, Person.class);
        System.out.println(deserialize);
    }

    @Test
    public void deserialize() {


    }


    @Data
    @ToString
    @AllArgsConstructor
    public static class Person implements Serializable {
        private String name;

        private int age;
    }
}