import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.json.JSONObject;

import java.net.ServerSocket;

public class Hello {


    // https://github.com/jetty-project/embedded-jetty-websocket-examples/tree/master/native-jetty-websocket-example
    public static void main(String[] args) throws Exception {
        System.out.println("hello world");

        JSONObject x = new JSONObject();

        x.put("card", "s12");

        System.out.println(x.toString());

        //Server xxx = new Server();
        //ServerConnector conn = new ServerConnector(xxx);
        //conn.setPort(9999);
        //xxx.addConnector(conn);

        ServerSocket ss = new ServerSocket(9999);
        while (true) ss.accept();





    }
}
