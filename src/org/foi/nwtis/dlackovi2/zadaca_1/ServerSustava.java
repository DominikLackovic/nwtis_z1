package org.foi.nwtis.dlackovi2.zadaca_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.nwtis.dlackovi2.konfiguracije.Konfiguracija;
import org.foi.nwtis.dlackovi2.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.dlackovi2.konfiguracije.NeispravnaKonfiguracija;
import org.foi.nwtis.dlackovi2.konfiguracije.NemaKonfiguracije;

public class ServerSustava
{
    public static void main(String[] args)
    {
        String sintaksa = "([^\\s]+(\\.(?i)(txt|xml|bin|json))$)";
        String datotekaKonfiguracije;

        provjeriArgumente(args);
        datotekaKonfiguracije = args[0];
        provjeriSintaksu(sintaksa, datotekaKonfiguracije);

        if (datotekaPostoji(datotekaKonfiguracije))
        {
            try
            {
                Konfiguracija konfig = KonfiguracijaApstraktna.preuzmiKonfiguraciju(datotekaKonfiguracije);
                ServerSustava ss = new ServerSustava();
                ss.pokreniPosluzitelj(konfig);
            }
            catch (NemaKonfiguracije | NeispravnaKonfiguracija ex)
            {
                Logger.getLogger(ServerSustava.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        else
        {
            System.out.println("Konfiguracija ne postoji!");
        }
    }
    
    private void pokreniPosluzitelj(Konfiguracija konfig)
    {
        int port = Integer.parseInt(konfig.dajPostavku("port"));
        int maksCekanje = Integer.parseInt(konfig.dajPostavku("maks.broj.zahtjeva.cekanje"));
        int maksRadnihDretvi = Integer.parseInt(konfig.dajPostavku("maks.broj.radnih.dretvi"));
        String datotekaEvidencije = konfig.dajPostavku("datoteka.evidencije.rada");
        boolean krajRada = false;
        int brojRadnihDretvi = 0;

        // TODO Provjeri i ako postoji učitaj evidenciju rada
        SerijalizatorEvidencije se = new SerijalizatorEvidencije("dlackovi2 - Serijalizator", konfig);
        se.start();

        try
        {
            ServerSocket serverSocket = new ServerSocket(port, maksCekanje);

            while (!krajRada)
            {
                Socket socket = serverSocket.accept();
                System.out.println("Korisnik se spojio");
                if (brojRadnihDretvi == maksRadnihDretvi)
                {
                    // TODO Vrati odgovarajući odgovor                    
                }
                else
                {
                    RadnaDretva radnaDretva = new RadnaDretva(socket, "dlackovi2 - " + brojRadnihDretvi, konfig);
                    brojRadnihDretvi++;
                    radnaDretva.start();
                }
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(ServerSustava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void provjeriArgumente(String[] args)
    {
        if (args.length != 1)
        {
            System.out.println("Premalo ili previse argumenata.");
            return;
        }
    }
    
    private static void provjeriSintaksu(String sintaksa, String datotekaKonfiguracije)
    {
        Pattern pattern = Pattern.compile(sintaksa);
        Matcher matcher = pattern.matcher(datotekaKonfiguracije);
        
        if(matcher.matches())
        {
            System.out.println("Naziv konfiguracijske datoteke OK!");
        }
        else
        {
            System.out.println("Naziv konfiguracijske datoteke OK!");
        }
    }

    private static boolean datotekaPostoji(String filePath)
    {
        Path path = Paths.get(filePath);
        return (Files.exists(path) && !Files.isDirectory(path));
    }
}
