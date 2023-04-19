package Model;

public class WorkerPerson extends Person {
    private Building workerPlace;


    public WorkerPerson(User king, String type, Cell location, Building workerPlace) {
        super(king, type, location);
        this.workerPlace = workerPlace;
    }

    public Building getWorkerPlace() {
        return workerPlace;
    }
}
