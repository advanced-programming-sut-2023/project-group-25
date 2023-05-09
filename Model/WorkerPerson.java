package Model;

public class WorkerPerson extends Person {
    private Building workerPlace;


    public WorkerPerson(User king, String type, Building workerPlace) {
        super(king, type);
        this.workerPlace = workerPlace;
    }
    public Building getWorkerPlace() {
        return workerPlace;
    }

}
