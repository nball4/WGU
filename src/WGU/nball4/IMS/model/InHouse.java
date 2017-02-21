package WGU.nball4.IMS.model;

/**
 * Created by nicholas on 2/13/2017.
 */
public class InHouse extends Part {

         // Instance Variables
        private int machineID;

        //Getters and Setters
        public int getMachineID() {
            return machineID;
        }

        public void setMachineID(int machineID) {
            this.machineID = machineID;
        }


        //Constructors
        public InHouse(){
            this.setMachineID(0);
            this.setPrice(0.0);
            this.setMax(0);
            this.setMin(0);
            this.setInStock(0);
            this.setName("");
            this.setPartID();
        }

        public InHouse(int machineID, String name, double price, int inStock, int min, int max) {
            this.setMachineID(machineID);
            this.setName(name);
            this.setPrice(price);
            this.setInStock(inStock);
            this.setMin(min);
            this.setMax(max);
            this.setPartID();

        }
    }

