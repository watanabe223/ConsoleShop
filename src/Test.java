import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo = true;
        while (bo) {

            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.println("你输入的用户名为：" + username);

            System.out.println("请输入密码：");
            String password = sc.next();
            System.out.println("你输入的密码为：" + password);

            //File file =new File("D:\\Youku Files\\user.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/user.xlsx");
            InputStream inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel();//创建对象
            User users[] = readExcel.readExcel(in);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    System.out.println("登录成功");

                    //显示商品信息
                    ReadProductExcel readProductExcel =new ReadProductExcel();
                    Product products[] = readProductExcel.readExcel(inPro);
                    for(Product product:products){
                        System.out.print(product.getpId());
                        System.out.print("\t"+product.getpName());
                        System.out.print("\t"+product.getPrice());
                        System.out.println("\t"+product.getpDescribe());
                    }

                    int count=0;
                    Product productes[]=new Product[4];
                    System.out.println("请输入商品ID，将商品加入购物车");
                    String pId=sc.next();
                    ReadProductExcel readProductExcel1=new ReadProductExcel();
                    inPro = null;
                    Product product = readProductExcel1.getProductById(pId,inPro);
                    if(product!=null){
                        productes[count++]=product;
                    }
                    bo = false;
                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
}

