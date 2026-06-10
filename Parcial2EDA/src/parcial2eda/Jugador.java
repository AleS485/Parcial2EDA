package parcial2eda;

import java.util.ArrayList;
import java.util.HashMap;

public class Jugador {

    private Mundo mundo = Mundo.getMundo();
    private String nombreJugador;
    private int hp;
    private int maxHP = 100;
    private int nivel;
    private HashMap<String, Integer> mochila;
    private ArrayList<Mision> misiones;
    private Mapa posicion;
    private HashMap<String, Integer> monstruosMatados;

    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        this.hp = 100;
        this.nivel = 1;
        this.posicion = mundo.getMapa(0);
        this.mochila = new HashMap<>();
        this.misiones = new ArrayList<>();
        this.monstruosMatados = new HashMap<>();

        initMisiones();
    }

    public void recoger(String i) {
        Integer c = mochila.get(i);
        if (c == null) {
            c = 0;
        }
        mochila.put(i, c + 1);
        checkMisiones();
    }

    private void checkMisiones() {
        for (var m : misiones)
            m.completarMision(this);
    }

    public void aceptarMision(Mision m) {
        if (misiones.contains(m))
            return;
        misiones.add(m);
        checkMisiones();
    }

    public void abandonarMision(Mision m) {
        misiones.remove(m);
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        if (hp > maxHP)
            hp = maxHP;
        if (hp <= 0) {
            System.out.println("MORISTE, COMO TE VA A MATAR ESE CHOBI? XD");
        }
    }

    public int getmaxHP() {

        return maxHP;

    }

    public void setMaxHp(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
        checkMisiones();
    }

    public HashMap<String, Integer> getMochila() {
        return mochila;
    }

    public ArrayList<Mision> getMisiones() {
        return misiones;
    }

    public Mapa getPosicion() {
        return posicion;
    }

    public void subirDeNivel() {
        this.maxHP += 20;
        this.hp = this.maxHP;
        this.nivel += 1;
        System.out.println("Felicidades, subiste al nivel:" + this.nivel);
        checkMisiones();
    }

    public boolean setPosicion(int i_destino) {
        ArrayList<Mapa> mapasDisponibles = mundo.getCaminosDisponiblesMapa(this.posicion);
        Mapa destino = mundo.getMapa(i_destino);
        if (destino == null)
            return false;
        if (!mapasDisponibles.contains(destino)) {
            return false;
        }
        this.posicion = destino;
        checkMisiones();
        return true;
    }

    public boolean checkMision(Mision m) {
        return m.completarMision(this);
    }

    public void atacar(Pnj enemigo) {

        if (!enemigo.isHostil()) {
            System.out.println("El pnj :" + enemigo.getNombrePnj() + " es amistoso, no lo podes atacar");
            return;
        }

        int dañoJugador = 15 + (int) (Math.random() * 6);
        int vidaEnemigoPeleando = enemigo.getVida();
        enemigo.setVida(vidaEnemigoPeleando - dañoJugador);
        System.out.println("atacaste a: " + enemigo.getNombrePnj() + ", y le sacaste " + dañoJugador + " de hp");

        if (enemigo.getVida() <= 0) {
            System.out.println("MATASTE A: " + enemigo.getNombrePnj());
            String nombreBicho = enemigo.getNombrePnj();
            Integer bichosMatados = this.monstruosMatados.get(nombreBicho);
            this.monstruosMatados.put(nombreBicho, bichosMatados == null ? 1 : bichosMatados + 1);
            checkMisiones();
        }

    }

    @Override
    public String toString() {
        return "Jugador{" + "nombreJugador=" + nombreJugador + ", hp=" + hp + ", nivel=" + nivel + ", mochila="
                + mochila + ", misiones=" + misiones + ", posicion=" + posicion + '}';
    }

    public int getMonstruosMatados(String monstruo) {
        Integer n = this.monstruosMatados.get(monstruo);
        if (n == null) {
            monstruosMatados.put(monstruo, 0);
        }
        return monstruosMatados.get(monstruo);
    }

    // prueba 2
    private void initMisiones() {
        Mision forkear = new Mision("Subir de nivel", "Alcanzar el nivel dos.", (m, j) -> {
            if (j.getNivel() < 2) {
                return false;
            }
            j.setMaxHp(j.getmaxHP() + 25);
            j.setHp(j.getHp() + 25);
            return m.setEstadoMision(true);
        });
        var matarDuende = new Mision("Matar un duende", "Mata un duende", (m, j) -> {
            Integer n = j.getMonstruosMatados("Duende");
            if (n < 1)
                return false;

            j.recoger("Espada mataduendes");
            return m.setEstadoMision(true);
        });

        var conseguirHongo = new Mision("Encontrar un hongo", "Encontrar un hongo mágico.", (m, j) -> {
            Integer n = j.getMochila().get("Hongos");
            if (n == null)
                n = 0;
            if (n < 1)
                return false;
            j.getMochila().put("Hongos", n - 1);
            j.recoger("Te de cucumelo");
            System.out.println("Mision completada. Recibiste un te de cucumelo");
            return m.setEstadoMision(true);
        });
        this.aceptarMision(matarDuende);
        this.aceptarMision(forkear);
        this.aceptarMision(conseguirHongo);
    }

}