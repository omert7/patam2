package app;

public class CorrelationType {

    public enum typeAlgo {
        zScore,
        Circle,
        Line
    }

    public String CoralA;
    public String CoralB;
    public typeAlgo type;
    public float data;

    public CorrelationType(String coralA, String coralB, typeAlgo type, float data) {
        CoralA = coralA;
        CoralB = coralB;
        this.type = type;
        this.data = data;
    }

    public CorrelationType() {
        // TODO Auto-generated constructor stub

        CoralA = "";
        CoralB = "";
        type = typeAlgo.zScore;
        this.data = 0;
    }

    public String getCoralA() {
        return CoralA;
    }

    public void setCoralA(String coralA) {
        CoralA = coralA;
    }

    public String getCoralB() {
        return CoralB;
    }

    public void setCoralB(String coralB) {
        CoralB = coralB;
    }

    public typeAlgo getType() {
        return type;
    }

    public void setType(typeAlgo type) {
        this.type = type;
    }


    public String getCoralByA(String coralA) {
        if (this.CoralA.compareTo(coralA) == 0) {
            return this.CoralB;
        } else return null;
    }


    public void print() {
        System.out.println("The features:" + CoralA + "-" + CoralB + ": " + type.toString() + " the correlation: " + data);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((CoralA == null) ? 0 : CoralA.hashCode());
        result = prime * result + ((CoralB == null) ? 0 : CoralB.hashCode());
        return result;
    }


}
