import java.util.concurrent.Semaphore;

public class Chief implements Runnable {
    private int id;
    private Semaphore semaphoreTable;
    //private Semaphore semaphoreOrder;
    private static Table[] tables;
    //private static ArrayList<Order> orders;

    public Chief(int id, Semaphore semt, Table[]tables) {
        this.id = id;
        this.semaphoreTable = semt;
        //this.semaphoreOrder = semo;
        Chief.tables = tables;
        //Waiter.orders = orders;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while(true){

            try {
                Thread.sleep(4000);
                semaphoreTable.acquire();
                for(int i=0;i<5;i++){
                    if(tables[i].getStatus()=="Notified"){
                        tables[i].setChiefID(this.id);
                        tables[i].setStatus("Prepared");
                        System.out.println("Chief " + getid() + " has prepared order of table "+ tables[i].getNo());
                        break;
                    }
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphoreTable.release();

        }
            
    }
}
