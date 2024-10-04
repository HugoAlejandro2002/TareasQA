package bancoStatic;

public class BancoUPB2 {

    public BancoUPB2(){

    }

    public String getAmountMoney(int ci, int amount){
        if (SegipServiceGlobal.isRealPerson(ci)){
            if (AsfiServiceGlobal.isAbleToGetCredit(ci)){
                int basicAmount = AfpServiceGlobal.getAmount(ci);
                int total = basicAmount*3;
                return "se le puede realizar el prestamo: "+total;
            }else{
                return "usted no esta habilitado para prestamos";
            }
        } else{
            return "debe revisar su carnet de identidad";
        }
    }
}
