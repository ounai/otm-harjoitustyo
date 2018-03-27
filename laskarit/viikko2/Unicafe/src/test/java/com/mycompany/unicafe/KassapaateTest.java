package com.mycompany.unicafe;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class KassapaateTest {
    Kassapaate paate;

    @Before
    public void setUp() {
        paate = new Kassapaate();
    }
    
    @Test
    public void rahamaaraOnAlussaOikein() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void myydytLounaatOnAlussaOikein() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanOstoKateisellaToimii() {
        int vaihtoraha = paate.syoEdullisesti(241);
        
        assertEquals(1, vaihtoraha);
        assertEquals(100240, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanOstoKateisellaToimii() {
        int vaihtoraha = paate.syoMaukkaasti(401);
        
        assertEquals(1, vaihtoraha);
        assertEquals(100400, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanOstoEpaonnistuuLiianVahallaKateisella() {
        int vaihtoraha = paate.syoEdullisesti(239);
        
        assertEquals(239, vaihtoraha);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanOstoEpaonnistuuLiianVahallaKateisella() {
        int vaihtoraha = paate.syoMaukkaasti(399);
        
        assertEquals(399, vaihtoraha);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanOstoKortillaToimii() {
        Maksukortti kortti = new Maksukortti(240);
        boolean tulos = paate.syoEdullisesti(kortti);
        
        assertTrue(tulos);
        assertEquals(0, kortti.saldo());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanOstoKortillaToimii() {
        Maksukortti kortti = new Maksukortti(400);
        
        boolean tulos = paate.syoMaukkaasti(kortti);
        
        assertTrue(tulos);
        assertEquals(0, kortti.saldo());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanOstoEpaonnistuuLiianVahallaRahallaKortilla() {
        Maksukortti kortti = new Maksukortti(239);
        
        boolean tulos = paate.syoEdullisesti(kortti);
        
        assertFalse(tulos);
        assertEquals(239, kortti.saldo());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanOstoEpaonnistuuLiianVahallaRahallaKortilla() {
        Maksukortti kortti = new Maksukortti(399);
        
        boolean tulos = paate.syoMaukkaasti(kortti);
        
        assertFalse(tulos);
        assertEquals(399, kortti.saldo());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassassaOlevaRahamaaraEiMuutuKortillaMaksettaessa() {
        Maksukortti kortti = new Maksukortti(10000);
        
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void rahanLataaminenKortilleToimii() {
        Maksukortti kortti = new Maksukortti(0);
        
        paate.lataaRahaaKortille(kortti, 39);
        
        assertEquals(39, kortti.saldo());
        assertEquals(100039, paate.kassassaRahaa());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        Maksukortti kortti = new Maksukortti(39);
        
        paate.lataaRahaaKortille(kortti, -1);
        
        assertEquals(39, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
    }
}
