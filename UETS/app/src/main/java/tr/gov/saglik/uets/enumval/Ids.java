package tr.gov.saglik.uets.enumval;

public enum Ids {

    YETKINLIK_FAB(1),
    KARNE_FAB(2);

    private final int idsVal;

    private Ids(int idsVal){
        this.idsVal = idsVal;
    }

    public int getIdsVal(){
        return idsVal;
    }
}
