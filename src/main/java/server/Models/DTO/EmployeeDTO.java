package server.Models.DTO;

import server.Models.Entities.Employee;

public class EmployeeDTO {
    private int idemployee;

    public String seat;

    public EmployeeDTO(Employee employee){
        this.idemployee = employee.getIdemployee();
        this.seat = employee.getSeat();
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(int idemployee) {
        this.idemployee = idemployee;
    }
}
