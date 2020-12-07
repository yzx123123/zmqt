import test.Ptest.OrderList;
import test.Ptest.TestOrder;

import java.io.FileInputStream;

public class pbtest {
    public static void main(String[] args) throws Exception{
        OrderList orderList =
                OrderList.parseFrom(new FileInputStream("/Users/yanzexiang/Documents/pbtest.file"));
        List.Print(orderList);
    }
}

