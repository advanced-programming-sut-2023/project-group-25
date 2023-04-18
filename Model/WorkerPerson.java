package Model;

public class WorkerPerson extends Person {
    private Building workerPlace;
    private String name;


    public WorkerPerson(User king, String type, Cell location, Building workerPlace, String name) {
        super(king, type, location);
        this.workerPlace = workerPlace;
        this.name = name;
    }

    public Building getWorkerPlace() {
        return workerPlace;
    }

    public String getName() {
        return name;
    }
}
