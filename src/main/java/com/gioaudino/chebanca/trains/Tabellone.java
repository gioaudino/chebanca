package com.gioaudino.chebanca.trains;

public class Tabellone {
    private NodoOrario tabellone;

    /**
     * Creates a new instance of Tabellone with one Orario
     *
     * @param orario the first Orario object to put in the Tabellone
     */
    public Tabellone(Orario orario) {
        NodoOrario head = new NodoOrario(orario);
        head.setNext(tabellone);
        this.tabellone = head;
    }

    /**
     * Adds an Orario object at the end of the queue
     *
     * @param orario the new Orario object to enqueue in the Tabellone
     */
    public void enqueueOrario(Orario orario) {
        NodoOrario tail = new NodoOrario(orario);
        NodoOrario last = tabellone;
        if (null == last) {
            this.tabellone = tail;
        } else {
            while (null != last.getNext()) {
                last = last.getNext();
            }
            last.setNext(tail);
        }
    }

    /**
     * Drops the first Orario object in the Tabellone
     */
    public void deleteFirst() {
        if (null != this.tabellone) {
            this.tabellone = this.tabellone.getNext();
        }
    }

    /**
     * Prints one row for each entry in the Tabellone. If there are no Orario objects, this won't print anything
     */
    public void printTabellone() {
        if (null != this.tabellone) {
            NodoOrario next = tabellone;
            while (null != next) {
                System.out.println(next.getOrario());
                next = next.getNext();
            }
        }
    }
}
