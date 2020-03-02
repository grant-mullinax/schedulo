package com.example.schedulo;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void constructorTest() {
        User user = new User("test", "1234", "1234");
        assert(user.getName().equals("test"));
        assert(user.getPassword().equals("1234"));
        assert(user.getPhone().equals("1234"));
    }
}