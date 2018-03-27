package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoOnAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(39);
        assertEquals(49, kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeKunSitaOnTarpeeksi() {
        kortti.otaRahaa(10);
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void saldoEiVaheneKunSitaEiOleTarpeeksi() {
        kortti.otaRahaa(11);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void saldonVahentaminenPalauttaaOikeanTuloksenKunSitaOnTarpeeksi() {
        assertTrue(kortti.otaRahaa(10));
    }
    
    @Test
    public void saldonVahentaminenPalauttaaOikeanTuloksenKunSitaEiOleTarpeeksi() {
        assertFalse(kortti.otaRahaa(11));
    }
    
    @Test
    public void tulostusToimiiAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void tulostusToimiiOikeinLataamisenJalkeen() {
        kortti.lataaRahaa(3929);
        assertEquals("saldo: 39.39", kortti.toString());
    }
    
    @Test
    public void tulostusToimiiOikeinSaldonVahentamisenJalkeen() {
        kortti.otaRahaa(10);
        assertEquals("saldo: 0.0", kortti.toString());
    }
}
