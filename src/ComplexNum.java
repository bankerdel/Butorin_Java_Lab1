public class ComplexNum {
    private double re;
    private double im;

    public ComplexNum(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public String display() {
        return re + " + " + im + "i";
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public ComplexNum add(ComplexNum other) {
        double n_re = this.re + other.re;
        double n_im = this.im + other.im;
        return new ComplexNum(n_re, n_im);
    }

    public ComplexNum subtract(ComplexNum other) {
        double n_re = this.re - other.re;
        double n_im = this.im - other.im;
        return new ComplexNum(n_re, n_im);
    }

    public ComplexNum multiply(ComplexNum other) {
        double n_re = (this.re * other.re) - (this.im * other.im);
        double n_im = (this.im * other.re) + (other.im * this.re);
        return new ComplexNum(n_re, n_im);
    }

    public ComplexNum divide(ComplexNum other) {
        double znam = other.re * other.re + other.im * other.im;
        if (znam == 0) {
            throw new ArithmeticException("Деление на ноль невозможно!");
        }
        double n_re = ((this.re * other.re) + (this.im * other.im)) / znam;
        double n_im = ((this.im * other.re) - (other.im * this.re)) / znam;
        return new ComplexNum(n_re, n_im);
    }
}