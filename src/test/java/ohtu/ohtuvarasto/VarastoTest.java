package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto saldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        saldolla = new Varasto(10, 2);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonSaldolla() {
        assertEquals(2, saldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaSaldoVarastolla0Tilavuus() {
    	Varasto tyhja = new Varasto(0, 2);
        assertEquals(0, tyhja.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastolla0Tilavuus() {
    	Varasto tyhja = new Varasto(0);
        assertEquals(0, tyhja.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaSaldoVarastollaOikeaSaldo() {
        assertEquals(2, saldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaSaldoVarastollaEiNegSaldoa() {
    	Varasto negSaldo = new Varasto(10, -3);
        assertEquals(0, negSaldo.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaSaldoVarastollaEiYliTilavuudenVerranSaldoa() {
    	Varasto yliSaldo = new Varasto(10, 30);
        assertEquals(10, yliSaldo.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negLisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void suurempiLisaysKuinTilaaLisaaTilanVerranSaldoa() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void negOttaminenPalauttaa0() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void yliOttaminenPalauttaaSaldonVerran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(10);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringTulostaaVaraston() {
    	assertTrue(saldolla.toString().equals("saldo = 2.0, vielä tilaa 8.0"));
    }
}
}