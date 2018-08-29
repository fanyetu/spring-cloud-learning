package cn.fanyetu.thrift.user.thrift;

import cn.fanyetu.thrift.user.UserService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 通过这个类来启动thrift服务
 *
 * @author zhanghaonan
 * @date 2018/8/29
 */
@Component
public class ThriftServer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${service.port}")
    private int servicePort;

    @Autowired
    private UserService.Iface userService;

    @PostConstruct
    public void serverRunner(){
        // 创建一个processor
        TProcessor processor = new UserService.Processor<>(userService);

        TNonblockingServerSocket socket = null;
        try {
            socket = new TNonblockingServerSocket(servicePort);
        } catch (TTransportException e) {
            e.printStackTrace();
        }

        TNonblockingServer.Args args = new TNonblockingServer.Args(socket);
        args.processor(processor);
        args.transportFactory(new TFramedTransport.Factory());
        args.protocolFactory(new TBinaryProtocol.Factory());

        TNonblockingServer server = new TNonblockingServer(args);
        logger.info("启动thrift服务成功...");
        server.serve();
    }
}
