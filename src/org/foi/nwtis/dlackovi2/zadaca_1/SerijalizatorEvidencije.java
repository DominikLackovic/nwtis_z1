package org.foi.nwtis.dlackovi2.zadaca_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.dlackovi2.konfiguracije.Konfiguracija;

public class SerijalizatorEvidencije extends Thread
{
    String nazivDretve;
    Konfiguracija konfig;
    boolean krajRada = false;
    String nazivDatotekeZaSerijalizaciju;

    public SerijalizatorEvidencije(String nazivDretve, Konfiguracija konfig)
    {
        super(nazivDretve);
        this.nazivDretve = nazivDretve;
        this.konfig = konfig;
    }

    @Override
    public void interrupt()
    {
        super.interrupt();
    }

    @Override
    public void run()
    {
        nazivDatotekeZaSerijalizaciju = konfig.dajPostavku("datoteka.evidencije.rada");
        int intervalZaSerijalizaciju = Integer.parseInt(konfig.dajPostavku("interval.za.serijalizaciju"));

        while (!krajRada)
        {
            long pocetak = System.currentTimeMillis();
            System.out.println("Dretva: " + nazivDretve + " Početak: " + pocetak);
            ObjectOutputStream oos = null;
            try
            {
                File f = new File(nazivDatotekeZaSerijalizaciju);
                oos = new ObjectOutputStream(new FileOutputStream(f));
                // TODO Dozvati objekt evoidencija rada iz ServerSustava i serijaliziraj ga                
            } 
            catch (FileNotFoundException ex)
            {
                Logger.getLogger(SerijalizatorEvidencije.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (IOException ex)
            {
                Logger.getLogger(SerijalizatorEvidencije.class.getName()).log(Level.SEVERE, null, ex);
            } 
            finally
            {
                try
                {
                    oos.close();
                } 
                catch (IOException ex)
                {
                    Logger.getLogger(SerijalizatorEvidencije.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            long kraj = System.currentTimeMillis();
            long razlika = kraj - pocetak;

            try
            {
                Thread.sleep(intervalZaSerijalizaciju * 1000 - razlika);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(SerijalizatorEvidencije.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public synchronized void start()
    {
        super.start();
    }
}
