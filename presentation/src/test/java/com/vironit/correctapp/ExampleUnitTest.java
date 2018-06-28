package com.vironit.correctapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
       /* Observable.range(0,10)
                .flatMap(aInt ->{
                    Observable.zip(Observable.timer(10, TimeUnit.MILLISECONDS(10)));
                })*/

    }
}