/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import group_iar_5.Offers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fawad
 */
public class TestOffers {

    public TestOffers() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void OffersTest() {

        Offers testOfeers = new Offers();
        /*only 1,2,3 is allowed so i try test it and the result if test failed because we used 
    here assertFalse, if we try any number except 1,2,3, the result of  test will pass*/
        assertFalse(testOfeers.checkOffer(8));
//        assertFalse(testOfeers.checkOffer(1));
    }

    // we also try assertTrue which result will be the opposite of assertFalse
    public void OffersTest2() {
        Offers testOfeers = new Offers();
//        assertTrue(testOfeers.checkOffer(6));
        assertTrue(testOfeers.checkOffer(2));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
