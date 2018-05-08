package org.foi.nwtis.dlackovi2.zadaca_1;

import java.io.Serializable;

/**
 * Klasa služi za evidenciju stanja sustava u određenom trenutku
 * @author dlackovi2
 */
public class Evidencija implements Serializable
{
    private long ukupanBrojZahtjeva = 0;
    private long brojNeispravnihZahtjeva = 0;
    private long brojNedozvoljenihZahtjeva = 0;
    private long brojUspjesnihZahtjeva = 0;
    private long brojPrekinutihZahtjeva = 0;
    private long ukupnoVrijemeRadaRadnihDretvi = 0;
    private long brojObavljenihSerijalizacija = 0;

    /**
     * Metoda vraća ukupan broj zahtjeva 
     * @return ukupanBrojZahtjeva
     */
    public long getUkupanBrojZahtjeva()
    {
        return ukupanBrojZahtjeva;
    }

    /**
     * Metoda postavlja vrijednost ukupanBrojZahtjeva
     * @param ukupanBrojZahtjeva 
     */
    public synchronized void setUkupanBrojZahtjeva()
    {
        this.ukupanBrojZahtjeva++;
    }

    /**
     * Metoda vraća broj neispravnih zahtjeva 
     * @return brojNeispravnihZahtjeva
     */
    public long getBrojNeispravnihZahtjeva()
    {
        return brojNeispravnihZahtjeva;
    }

    /**
     * Metoda postavlja vrijednost brojNeispravnihZahtjeva
     * @param brojNeispravnihZahtjeva 
     */
    public synchronized void setBrojNeispravnihZahtjeva()
    {
        this.brojNeispravnihZahtjeva++;
    }

    /**
     * Metoda vraća broj nedozvoljenih zahtjeva 
     * @return brojNedozvoljenihZahtjeva
     */
    public long getBrojNedozvoljenihZahtjeva()
    {
        return brojNedozvoljenihZahtjeva;
    }

    /**
     * Metoda postavlja vrijednost brojNedozvoljenihZahtjeva
     * @param brojNedozvoljenihZahtjeva 
     */
    public synchronized void setBrojNedozvoljenihZahtjeva()
    {
        this.brojNedozvoljenihZahtjeva++;
    }

    /**
     * Metoda vraća broj uspjesnih zahtjeva 
     * @return brojUspjesnihZahtjeva
     */
    public long getBrojUspjesnihZahtjeva()
    {
        return brojUspjesnihZahtjeva;
    }

    /**
     * Metoda postavlja vrijednost brojUspjesnihZahtjeva
     * @param brojUspjesnihZahtjeva 
     */
    public synchronized void setBrojUspjesnihZahtjeva()
    {
        this.brojUspjesnihZahtjeva++;
    }

    /**
     * Metoda vraća broj prekinutih zahtjeva 
     * @return brojPrekinutihZahtjeva
     */
    public long getBrojPrekinutihZahtjeva()
    {
        return brojPrekinutihZahtjeva;
    }

    /**
     * Metoda postavlja vrijednost brojPrekinutihZahtjeva
     * @param brojPrekinutihZahtjeva 
     */
    public synchronized void setBrojPrekinutihZahtjeva()
    {
        this.brojPrekinutihZahtjeva++;
    }

    /**
     * Metoda vraća ukupno vrijeme rada radnih dretvi 
     * @return ukupnoVrijemeRadaRadnihDretvi
     */
    public long getUkupnoVrijemeRadaRadnihDretvi()
    {
        return ukupnoVrijemeRadaRadnihDretvi;
    }

    /**
     * Metoda postavlja vrijednost ukupnoVrijemeRadaRadnihDretvi
     * @param ukupnoVrijemeRadaRadnihDretvi 
     */
    public synchronized void setUkupnoVrijemeRadaRadnihDretvi(long vrijeme)
    {
        this.ukupnoVrijemeRadaRadnihDretvi += vrijeme;
    }

    /**
     * Metoda vraća broj obavljenih serijalizacija 
     * @return brojObavljenihSerijalizacija
     */
    public long getBrojObavljenihSerijalizacija()
    {
        return brojObavljenihSerijalizacija;
    }

    /**
     * Metoda postavlja vrijednost brojObavljenihSerijalizacija
     * @param brojObavljenihSerijalizacija 
     */
    public synchronized void setBrojObavljenihSerijalizacija()
    {
        this.brojObavljenihSerijalizacija++;
    }
}
