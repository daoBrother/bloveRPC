package Blove.Netty.Client;

import Blove.Codecs.MsgToMsg.MsgDecoder;
import Blove.Codecs.MsgToMsg.MsgEncoder;
import Blove.Netty.Handler.MessageSendHandler;
import Blove.Packet.Enums.RPCSerializerProtocol;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/*
 * @Time    : 2019/7/4 5:11 PM
 * @Author  : YouMing
 * @Email   : myoueva@gmail.com
 * @File    : MessageSendChannelInitializer.java
 * @Software: IntelliJ IDEA
 */
public class MessageSendChannelInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * @Author myou<myoueva@gmail.com>
     * @Description //RPC客户端管道初始化
     * 做到可配置化添加pipline组件
     * @Date 5:12 PM 2019/7/4
     * @Param
     * @return
     **/
    private static final int MESSAGE_LENGTH = 4;
//    private RPCSerializerProtocol serializerProtocol;

//    public MessageSendChannelInitializer(RPCSerializerProtocol serializerProtocol) {
//        this.serializerProtocol = serializerProtocol;
//    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new MsgDecoder(
                Integer.MAX_VALUE,
                0,
                MessageSendChannelInitializer.MESSAGE_LENGTH));
        /**
         * 编解码组件祖册
         * 客户端channel管道注册
         */
        socketChannel.pipeline().addLast(new MsgEncoder());
        socketChannel.pipeline().addLast(new MessageSendHandler());
    }

}
