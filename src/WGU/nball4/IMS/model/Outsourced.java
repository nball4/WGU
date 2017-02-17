package WGU.nball4.IMS.model;

/**
 * Created by nicholas on 2/13/2017.
 */
public class Outsourced extends Part {
    // Instance Variables

    private int companyId;
    private String companyName;
    private String companyPhone;


    //Getters and Setters
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }



    //Constructors
    public Outsourced(int companyId, String name, double price, int inStock, int min, int max) {
        this.setCompanyId(companyId);
        this.setName(name);
        this.setPrice(price);
        this.setInStock(inStock);
        this.setMin(min);
        this.setMax(max);

    }
    public Outsourced(){

    }
}
