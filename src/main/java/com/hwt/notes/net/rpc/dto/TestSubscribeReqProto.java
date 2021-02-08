package com.hwt.notes.net.rpc.dto;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;

public class TestSubscribeReqProto {

    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("hwt");
        builder.setProductName("Netty Book");
        ArrayList<String> address = new ArrayList<>();
        address.add("BeiJing PingXiFU");
        address.add("HaErBin HaLiGong");
        address.add("SuiHua JinHeZhen");
        builder.addAllAddress(address);
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq subscribeReq = createSubscribeReq();
        System.out.println("encode pre :" + subscribeReq.toString());
        System.out.println(new String(subscribeReq.toByteArray()));
        SubscribeReqProto.SubscribeReq req2 = decode(encode(subscribeReq));
        System.out.println("decode over :" + subscribeReq.toString());
        System.out.println("Assert equal : --> " + req2.equals(subscribeReq));
    }
}
