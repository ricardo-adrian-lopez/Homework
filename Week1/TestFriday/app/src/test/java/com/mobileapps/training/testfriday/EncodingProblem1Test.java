package com.mobileapps.training.testfriday;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EncodingProblem1Test {

    String val;

    @Before
    public void setUp(){
        val = "acp";
    }

    @Test
    public void encondedIsReversed(){
        assertEquals("zxk", EncodingProblem.f(val));
    }

    @Test
    public void sizeIsCorrect(){
        assertTrue(val.length()== EncodingProblem.f(val).length());
    }
}
