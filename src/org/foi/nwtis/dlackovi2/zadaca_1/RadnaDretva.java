package org.foi.nwtis.dlackovi2.zadaca_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.dlackovi2.konfiguracije.Konfiguracija;

public class RadnaDretva extends Thread
{

    private Socket socket;
    private String nazivDretve;
    private Konfiguracija konfiguracija;
    private Evidencija evidencija;
    private long vrijemePocetka = 0;

    public RadnaDretva(Socket socket, String nazivDretve, Konfiguracija konfiguracija)
    {
        super(nazivDretve);
        this.evidencija = new Evidencija();
        this.socket = socket;
        this.nazivDretve = nazivDretve;
        this.konfiguracija = konfiguracija;
    }

    @Override
    public void interrupt()
    {
        try
        {
            evidencija.setBrojPrekinutihZahtjeva();
            evidencija.setUkupnoVrijemeRadaRadnihDretvi(System.currentTimeMillis() - vrijemePocetka);

            this.socket.close();
            super.interrupt();
        }
        catch (IOException ex)
        {
            System.out.println("ERROR; Problem kod zatvaranja socketa");
        }
    }

    @Override
    public void run()
    {
        vrijemePocetka = System.currentTimeMillis();
        try (InputStream inputStream = socket.getInputStream(); OutputStream outputStream = socket.getOutputStream();)
        {
            int znak;
            StringBuffer buffer = new StringBuffer();
            while (true)
            {
                znak = inputStream.read();
                if (znak == -1)
                {
                    break;
                }
                buffer.append((char) znak);
            }
            System.out.println("Dretva: " + nazivDretve + " Komanda: " + buffer.toString());

        }
        catch (IOException ex)
        {
            Logger.getLogger(RadnaDretva.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO Smanji broj aktivnih radnih dretvi kod ServerSustava
    }

    @Override
    public synchronized void start()
    {
        super.start();
    }

    public Evidencija getEvidencija()
    {
        return evidencija;
    }

    public void setEvidencija(Evidencija evidencija)
    {
        this.evidencija = evidencija;
    }
}
