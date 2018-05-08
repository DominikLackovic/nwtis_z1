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
    Socket socket;
    String nazivDretve;
    Konfiguracija konfig;

    public RadnaDretva(Socket socket, String nazivDretve, Konfiguracija konfig)
    {
        super(nazivDretve);
        this.socket = socket;
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
        try (InputStream inputStream = socket.getInputStream();OutputStream outputStream = socket.getOutputStream();)
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
}
