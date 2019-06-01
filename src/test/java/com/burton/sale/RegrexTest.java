package com.burton.sale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Burton
 * @location：com.burton.sale.RegrexTest
 * @title: RegrexTest
 * @projectName sale
 * @description:
 * @date 2019/6/1 19:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegrexTest {
    private static final Pattern patternGreedy = Pattern.compile("a.*b");
    private static final Pattern patternReluctant = Pattern.compile("a.*?b");
    private static final Pattern patternReluctant2 = Pattern.compile("[{].*?[}]");

    @Test
    public void greedy() {
//        String a = "a=======b-----b--===";
//        Matcher matcher = patternGreedy.matcher(a);
//        while (matcher.find()) {
//            System.out.println(matcher.group());
//            //RESULT:a=======b-----b
//        }

        String a = "a=======b---a--b--===";
        Matcher matcher = patternGreedy.matcher(a);
        while (matcher.find()) {
            System.out.println(matcher.group());
            //RESULT:a=======b---a--b
        }
    }

    @Test
    public void reluctant() {
//        String a = "a=======b-----b--===";
//        Matcher matcher = patternReluctant.matcher(a);
//        while (matcher.find()) {
//            System.out.println(matcher.gr1oup());
//            //RESULT:a=======b
//        }

        String a = "a=======b---a--b--===";
        Matcher matcher = patternReluctant.matcher(a);
        while (matcher.find()) {
            System.out.println(matcher.group());
            //RESULT:
            // a=======b
            //a--b
        }
    }


    @Test
    public void reluctant2() {
//        String a = "a=======b-----b--===";
//        Matcher matcher = patternReluctant.matcher(a);
//        while (matcher.find()) {
//            System.out.println(matcher.gr1oup());
//            //RESULT:a=======b
//        }

        String a = "a=======b---a--b--===";
        Matcher matcher = patternReluctant2.matcher(a);
        while (matcher.find()) {
            System.out.println(matcher.group());
            //RESULT:
            // a=======b
            //a--b
        }
    }

}
