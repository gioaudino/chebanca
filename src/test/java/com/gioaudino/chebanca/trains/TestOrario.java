package com.gioaudino.chebanca.trains;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestOrario {

    @Rule
    public final SystemOutRule output = new SystemOutRule().enableLog().muteForSuccessfulTests();


    @Test
    public void testPrintOrario() throws OrarioException {
        Orario orario = new Orario("AB123", 12, "Milano");
        assertThat(orario.toString()).isEqualTo("Treno AB123 per MILANO - partenza ore: 12 - orario effettivo: ore 12");
        orario.setActualTime(13);
        assertThat(orario.toString()).isEqualTo("Treno AB123 per MILANO - partenza ore: 12 - orario effettivo: ore 13 - ritardo 1 ora");
        orario.setActualTime(14);
        assertThat(orario.toString()).isEqualTo("Treno AB123 per MILANO - partenza ore: 12 - orario effettivo: ore 14 - ritardo 2 ore");
    }

    @Test
    public void testExceptionThrown() {
        Orario orario = new Orario("AB123", 12, "Milano");
        assertThatThrownBy(() -> orario.setActualTime(10)).isInstanceOf(OrarioException.class);
    }

    @Test
    public void testDelay() throws OrarioException {
        Orario orario = new Orario("AB123", 12, "Milano");
        assertThat(orario.isLate()).isFalse();
        assertThat(orario.delay()).isEqualTo(0);

        orario.setActualTime(15);
        assertThat(orario.isLate()).isTrue();
        assertThat(orario.delay()).isEqualTo(3);
    }

    @Test
    public void testTabelloneWithOneEntry() {
        Orario orario = new Orario("AB123", 12, "Milano");
        Tabellone tabellone = new Tabellone(orario);
        tabellone.printTabellone();
        assertThat(this.output.getLog()).isEqualTo("Treno AB123 per MILANO - partenza ore: 12 - orario effettivo: ore 12\n");
    }

    @Test
    public void testTabelloneMultipleEntries() throws OrarioException {
        Tabellone tabellone = new Tabellone(new Orario("AB123", 12, "Milano"));
        tabellone.enqueueOrario(new Orario("AB000", 10, "Roma"));
        Orario at = new Orario("AT1912", 20, "Bletchley");
        at.setActualTime(21);
        tabellone.enqueueOrario(at);
        tabellone.printTabellone();
        String result =
                "Treno AB123 per MILANO - partenza ore: 12 - orario effettivo: ore 12" + '\n' +
                        "Treno AB000 per ROMA - partenza ore: 10 - orario effettivo: ore 10" + '\n' +
                        "Treno AT1912 per BLETCHLEY - partenza ore: 20 - orario effettivo: ore 21 - ritardo 1 ora";
        assertThat(this.output.getLog().trim()).isEqualTo(result);
    }

    @Test
    public void testDeleteFromTabellone() throws OrarioException {
        Tabellone tabellone = new Tabellone(new Orario("AB123", 12, "Milano"));
        tabellone.enqueueOrario(new Orario("AB000", 10, "Roma"));
        Orario at = new Orario("AT1912", 20, "Bletchley");
        at.setActualTime(21);
        tabellone.enqueueOrario(at);
        tabellone.deleteFirst();
        tabellone.printTabellone();
        String result =
                "Treno AB000 per ROMA - partenza ore: 10 - orario effettivo: ore 10" + '\n' +
                        "Treno AT1912 per BLETCHLEY - partenza ore: 20 - orario effettivo: ore 21 - ritardo 1 ora";
        assertThat(this.output.getLog().trim()).isEqualTo(result);

        tabellone.deleteFirst();
        this.output.clearLog();
        tabellone.printTabellone();
        assertThat(this.output.getLog().trim()).isEqualTo("Treno AT1912 per BLETCHLEY - partenza ore: 20 - orario effettivo: ore 21 - ritardo 1 ora");

        tabellone.deleteFirst();
        this.output.clearLog();
        tabellone.printTabellone();
        assertThat(this.output.getLog()).isEmpty();
    }


}
