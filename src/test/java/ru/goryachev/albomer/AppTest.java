package ru.goryachev.albomer;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void testStarter() {
        Starter starter = new Starter();
        Assert.assertNotNull(starter);
    }
}