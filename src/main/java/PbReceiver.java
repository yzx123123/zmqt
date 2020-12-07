import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.StringTokenizer;
import test.Ptest.OrderList;
import test.Ptest.TestOrder;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import test.Ptest;

//
//  Weather update client in Java
//  Connects SUB socket to tcp://localhost:5556
//  Collects weather updates and finds avg temp in zipcode
//
public class PbReceiver
{
    public static void main (String[] args) throws Exception
    {
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            // System.out.println("Collecting updates from weather server");
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            subscriber.connect("tcp://localhost:5556");

            //  Subscribe to zipcode, default is NYC, 10001
            String filter = "";
            subscriber.subscribe(filter.getBytes(ZMQ.CHARSET));

            while (true){
                //  Use trim to remove the tailing '0' character
                byte[] msg = subscriber.recv(0);
                //System.out.println("1");

                //System.out.println("2");

                Ptest.OrderList orderList = Ptest.OrderList.parseFrom(msg);
                List.Print(orderList);
            }

        }
    }

}

class List{
    static  void Print(Ptest.OrderList orderList) {
        for (Ptest.TestOrder testOrder: orderList.getTorderList()) {
            System.out.println(testOrder.getId());
            for (Ptest.TestOrder.Mess mess : testOrder.getMessageList()) {
                System.out.println(mess.getNum());
                System.out.println(mess.getS());
            }
        }
    }
}