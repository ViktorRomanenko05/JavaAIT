package HomeworkLesson27;

public class WorkSpace {

    public void projectManger(FilmStudioEmployee employee){
        if (employee.getAccessLevel().fullAccess()){
            System.out.println("\nPROJECT MANAGER " +
                    "\nACCESS GRANTED");
        }
        else {
            System.out.println("\nCREW MANAGEMENT "+
                    "\nACCESS DENIED" +
                    "\nYour level " + employee.getAccessLevel() + " is insufficient");
        }
    }

    public void filmCrewManagement (FilmStudioEmployee employee){
        if (employee.getAccessLevel().accessToTheFilmingProcess()){
            System.out.println("\nCREW MANAGEMENT " +
                    "\nACCESS GRANTED");
        }
        else {
            System.out.println("\nCREW MANAGEMENT "+
                    "\nACCESS DENIED" +
                    "\nYour level " + employee.getAccessLevel() + " is insufficient");
        }
    }

    public void videoEditor (FilmStudioEmployee employee){
        if (employee.getAccessLevel().accessToTechnicalSpace()){
            System.out.println("\nVIDEO EDITOR" +
                    "\nACCESS GRANTED");
        }
        else {
            System.out.println("\nVIDEO EDITOR "+
                    "\nACCESS DENIED" +
                    "\nYour level " + employee.getAccessLevel() + " is insufficient");
        }
    }

    public void roles (FilmStudioEmployee employee){
        if (employee.getAccessLevel().accessToArtistSpace()){
            System.out.println("\nROLES" +
                    "\nACCESS GRANTED");
        }
        else {
            System.out.println("\nROLES "+
                    "\nACCESS DENIED" +
                    "\nYour level " + employee.getAccessLevel() + " is insufficient");
        }
    }


}
