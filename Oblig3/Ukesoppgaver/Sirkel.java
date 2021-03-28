class Sirkel extends Figur{

    private double radius;

    public Sirkel(double r){
        radius = r;
    }

    public double areal(){
        return radius*radius*Math.PI;
    }
}
