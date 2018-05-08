package org.foi.nwtis.dlackovi2.zadaca_1;

public class KorisnikSustava
{
    String korisnik;
    String lozinka;
    String adresa;
    int port;
    boolean administrator = false;
    String[] args;

    public static void main(String[] args)
    {
        // TODO Provjeri upisane argumente
        KorisnikSustava ks = new KorisnikSustava();
        ks.preuzmiPostavke(args);
        ks.args = args;
        
        if (ks.administrator)
        {
            // TODO Kreiraj objekt AdministrSustava i predaj mu kontrolu          
        } 
        else
        {
            // TODO Kreiraj objekt KorisikSustava i predaj mu kontrolu           
        }
    }

    private void preuzmiPostavke(String[] args)
    {
        korisnik = "dlackovi2";
        lozinka = "123456";
        adresa = "127.0.0.1";
        port = 8000;

        if (korisnik != null)
        {
            korisnik = korisnik.trim();
            if (!korisnik.isEmpty())
            {
                administrator = true;
            }
        }
        if (lozinka != null)
        {
            lozinka = lozinka.trim();
            if (!lozinka.isEmpty())
            {
                administrator = true;
            } 
            else
            {
                administrator = false;
            }
        } 
        else
        {
            administrator = false;
        }
        // TODO Provjeri da li je korisnik kao administrator u postavkama        
    }
}
