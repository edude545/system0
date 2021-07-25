package net.ethobat.system0.api.network;

public interface INetConnection {

    INetConnector getTarget();

    long getBandwidth();

}
