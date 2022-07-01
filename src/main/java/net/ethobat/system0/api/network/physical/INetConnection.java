package net.ethobat.system0.api.network.physical;

public interface INetConnection {

    INetConnector getTarget();

    long getBandwidth();

}
