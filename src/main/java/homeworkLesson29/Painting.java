package homeworkLesson29;

public class Painting extends WorkOfArt implements Artwork{

    private TypeOfPaint typeOfPaint;

    private double lengthM;

    private double widthM;

    public Painting(int artworkId, String author, String name, int yearOfCreation, String description, ObjectType objectType, TypeOfPaint typeOfPaint, double lengthM, double widthM) {
        super(artworkId, author, name, yearOfCreation, description, objectType);
        this.typeOfPaint = typeOfPaint;
        this.lengthM = lengthM;
        this.widthM = widthM;
    }

    public TypeOfPaint getTypeOfPaint() {
        return typeOfPaint;
    }

    public void setTypeOfPaint(TypeOfPaint typeOfPaint) {
        this.typeOfPaint = typeOfPaint;
    }

    public double getLengthM() {
        return lengthM;
    }

    public void setLengthM(double lengthM) {
        this.lengthM = lengthM;
    }

    public double getWidthM() {
        return widthM;
    }

    public void setWidthM(double widthM) {
        this.widthM = widthM;
    }

    @Override
    public String toString() {
        return "Painting{" +
                "typeOfPaint=" + typeOfPaint +
                ", lengthM=" + lengthM +
                ", widthM=" + widthM +
                "} " + super.toString();
    }

    @Override
    public void displayInfo(){
        System.out.println("\nInformation about painting:\n" +
                "ID: " + this.getArtworkId()+"\n" +
                "Name: " + this.getName() +"\n"+
                "Artist: " + this.getAuthor() + "\n" +
                "Year: " + this.getYearOfCreation() + "\n"+
                "Type of paint: " + this.typeOfPaint.description + "\n"+
                "Length (M): " + this.getLengthM() + "\n"+
                "Width (M): " + this.getWidthM());
    }

    public String getCreator(){
        String creator = this.getAuthor();
        System.out.println("\nCreator: " + this.getAuthor());
        return creator;
    }

    }
