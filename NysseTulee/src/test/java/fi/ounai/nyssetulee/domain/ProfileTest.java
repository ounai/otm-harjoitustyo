/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ounai.nyssetulee.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProfileTest {
    
    private Profile profile;
    
    public ProfileTest() {
        profile = new Profile("test name");
    }
    
    @Test
    public void profileHasCorrectName() {
        assertEquals("test name", profile.getName());
    }
    
}
