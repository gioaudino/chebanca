package com.gioaudino.chebanca.trains;

public class Orario {
    /**
     *
     */
    private String id;
    private int expectedTime;
    private int actualTime;
    private String destination;

    public Orario(String id, int expectedTime, String destination) {
        this.id = id.toUpperCase();
        this.expectedTime = expectedTime;
        this.actualTime = expectedTime;
        this.destination = destination.toUpperCase();
    }

    /**
     * @return true if the train is late (i.e. actual time > expected time), false otherwise
     */
    public boolean isLate() {
        return this.expectedTime < this.actualTime;
    }

    /**
     * @return an integer representing how many hours of delay this train has
     */
    public int delay() {
        return this.actualTime - this.expectedTime;
    }

    /**
     * @param actualTime the actual time of departure of this train
     * @throws OrarioException if the parameter holds a value greater than the expected time value of this object
     */
    public void setActualTime(int actualTime) throws OrarioException {
        if (actualTime < this.expectedTime) throw new OrarioException();
        this.actualTime = actualTime;
    }

    @Override
    public String toString() {
        if (this.isLate())
            return String.format(
                    "Treno %s per %s - partenza ore: %d - orario effettivo: ore %d - ritardo %d %s",
                    this.id,
                    this.destination,
                    this.expectedTime,
                    this.actualTime,
                    this.delay(),
                    this.delay() > 1 ? "ore" : "ora"
            );
        return String.format(
                "Treno %s per %s - partenza ore: %d - orario effettivo: ore %d",
                this.id,
                this.destination,
                this.expectedTime,
                this.actualTime
        );
    }
}
