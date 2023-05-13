package Model;

public class WorkerPerson extends Person {
    private Building workerPlace = null;
    
    public WorkerPerson(User king, String type, Building workerPlace) {
        super(type);
        this.workerPlace = workerPlace;
    }
    
    public Building getWorkerPlace() {
        return workerPlace;
    }
    
    public void setWorkerPlace(Building workerPlace) {
        this.workerPlace = workerPlace;
    }
    
}
