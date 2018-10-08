package com.gioaudino.chebanca.trains;

public class NodoOrario {
    private Orario orario;
    private NodoOrario next;

    public NodoOrario(Orario orario, NodoOrario next) {
        this.orario = orario;
        this.next = next;
    }

    public NodoOrario(Orario orario) {
        this.orario = orario;
    }

    public Orario getOrario() {
        return orario;
    }

    public void setOrario(Orario orario) {
        this.orario = orario;
    }

    public NodoOrario getNext() {
        return next;
    }

    public void setNext(NodoOrario next) {
        this.next = next;
    }
}
