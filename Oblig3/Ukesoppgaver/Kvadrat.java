class Kvadrat extends Figur{

    double side;

    public Kvadrat(double s){
        side = s;
    }

    public double areal(){
        return side*side;
    }
}
