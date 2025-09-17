package paqueteCirculo;

public class ProgramaCirculos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circulo miCirculo = new Circulo();
		double area = miCirculo.getArea();
		miCirculo.setRadio(300);
		area = miCirculo.getArea();
		
		System.out.println("area: " + area);
		
		Circulo miSegundoCirculo = new Circulo();
		area = miSegundoCirculo.getArea();
		System.out.println("area: " + area);
		
		double perimetro = miSegundoCirculo.getPerimetro();
		System.out.println("perimetro:" + perimetro);
		
		System.out.println(miSegundoCirculo);
	
	}

}
