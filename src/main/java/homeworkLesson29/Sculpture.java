package homeworkLesson29;

public class Sculpture extends WorkOfArt implements Artwork {
    private static ObjectType type = ObjectType.SCULPTURE;

    private String sculptureMaterial;

    private double weightKg;

    public Sculpture(int artworkId, String author, String name, int yearOfCreation, String description, ObjectType objectType, String sculptureMaterial, double weightKg) {
        super(artworkId, author, name, yearOfCreation, description, objectType);
        this.sculptureMaterial = sculptureMaterial;
        this.weightKg = weightKg;
    }

    public String getSculptureMaterial() {
        return sculptureMaterial;
    }

    public void setSculptureMaterial(String sculptureMaterial) {
        this.sculptureMaterial = sculptureMaterial;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public void displayInfo(){
        System.out.println("\nInformation about painting:\n" +
                "ID: " + this.getArtworkId()+"\n" +
                "Name: " + this.getName() +"\n"+
                "Artist: " + this.getAuthor() + "\n" +
                "Year: " + this.getYearOfCreation() + "\n"+
                "Type of paint: " + this.sculptureMaterial + "\n"+
                "Weight (Kg): " + this.getWeightKg());
    }

    public String getCreator(){
        String creator = this.getAuthor();
        System.out.println("\nCreator: " + this.getAuthor());
        return creator;
    }
}
