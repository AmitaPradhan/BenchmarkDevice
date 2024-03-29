package com.bsb.hike.mqtt.msg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.bsb.hike.common.HikeConstants;
import com.bsb.hike.mqtt.FormatUtil;

public class ConnectMessage extends Message
{
    private static final String PROTOCOL_ID = "MQIsdp";
    private static final byte PROTOCOL_VERSION = 3;
    private static int CONNECT_HEADER_SIZE = 12;

    private String clientId;
    private int version;
    private short keepAlive;
    private String username;
    private String password;
    private boolean cleanSession;
    private String willTopic;
    private String will;
    private QoS willQoS = QoS.AT_MOST_ONCE;
    private boolean retainWill = false;

    private boolean autoSubscribeDefaultTopics;

    public ConnectMessage(String clientId, boolean cleanSession, short keepAlive)
    {
        super(Type.CONNECT);
        if (clientId == null || clientId.length() > 23)
        {
            throw new IllegalArgumentException(
                    "Client id cannot be null and must be at most 23 characters long: "
                            + clientId);
        }
        //NOTE: Don't call setClientIdAndVersion here.
        this.clientId = clientId;
        this.cleanSession = cleanSession;
        this.keepAlive = keepAlive;
    }

    public ConnectMessage(Header header)
            throws IOException
    {
        super(header);
    }

    @Override
    protected int messageLength()
    {
        int payloadSize = FormatUtil.toMQttString(clientId).length;
        payloadSize += FormatUtil.toMQttString(willTopic).length;
        payloadSize += FormatUtil.toMQttString(will).length;
        payloadSize += FormatUtil.toMQttString(username).length;
        payloadSize += FormatUtil.toMQttString(password).length;
        return payloadSize + CONNECT_HEADER_SIZE;
    }

    @Override
    protected void writeMessage(OutputStream out)
            throws IOException
    {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeUTF(PROTOCOL_ID);
        dos.write(PROTOCOL_VERSION);
        int flags = cleanSession ? 2 : 0;
        flags |= (will == null) ? 0 : 0x04;
        flags |= willQoS.val << 3;
        flags |= retainWill ? 0x20 : 0;
        flags |= (password == null) ? 0 : 0x40;
        flags |= (username == null) ? 0 : 0x80;
        //System.out.println("flags :: " + flags);
        dos.write(flags);
        dos.writeShort(keepAlive);

        dos.writeUTF(clientId);
        if (will != null)
        {
            dos.writeUTF(willTopic);
            dos.writeUTF(will);
        }
        if (username != null)
        {
            dos.writeUTF(username);
        }
        if (password != null)
        {
            dos.writeUTF(password);
        }
        dos.flush();
    }

    @Override
    protected void readMessage(InputStream in, int msgLength)
            throws IOException
    {
        DataInputStream dis = new DataInputStream(in);
        dis.readUTF(); //PROTOCOL_ID
        dis.read(); //PROTOCOL_VERSION
        byte flags = dis.readByte(); //flags
        //System.out.println("Flag : " + flags);

        keepAlive = dis.readShort();
        String id = dis.readUTF();
        setClientIdAndVersion(id);
        if (((flags & 0x04) != 0))
        {
            willTopic = dis.readUTF();
            will = dis.readUTF();
        }

        username = ((flags & 0x80) == 0) ? null : dis.readUTF();
        password = ((flags & 0x40) == 0) ? null : dis.readUTF();

        retainWill = ((flags & 0x20) > 0);
        willQoS = QoS.valueOf((byte) ((flags & 0x18) >>> 3));
    }

    private void setClientIdAndVersion(String id)
    {
        //System.out.println("Client Id : " + clientId);
        String[] parts = id.split(HikeConstants.CLIENT_ID_SEPARATOR);
        this.clientId = parts[0];
        this.version = 0;
        this.autoSubscribeDefaultTopics = false;
        if (parts.length > 1)
        {
            try
            {
                this.version = Integer.parseInt(parts[1]);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid version " + clientId);
            }
        }
        if (parts.length > 2)
        {
            this.autoSubscribeDefaultTopics = Boolean.valueOf(parts[2]);
        }
    }

    public void setCredentials(String username, String password)
    {
        this.username = username;
        this.password = password;

    }

    public void setWill(String willTopic, String will)
    {
        this.willTopic = willTopic;
        this.will = will;
    }

    public void setWill(String willTopic, String will, QoS willQoS,
                        boolean retainWill)
    {
        this.willTopic = willTopic;
        this.will = will;
        this.willQoS = willQoS;
        this.retainWill = retainWill;

    }

    public String getClientId()
    {
        return clientId;
    }

    public int getVersion()
    {
        return version;
    }

    public boolean isCleanSession()
    {
        return cleanSession;
    }

    public int getKeepAlive()
    {
        return keepAlive;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public void setDup(boolean dup)
    {
        throw new UnsupportedOperationException("CONNECT messages don't use the DUP flag.");
    }

    @Override
    public void setRetained(boolean retain)
    {
        throw new UnsupportedOperationException("CONNECT messages don't use the RETAIN flag.");
    }

    @Override
    public void setQos(QoS qos)
    {
        throw new UnsupportedOperationException("CONNECT messages don't use the QoS flags.");
    }


    @Override
    public String toString()
    {
        StringBuilder strBuff = new StringBuilder();
        strBuff.append("ConnectMessage [");
        strBuff.append("clientId:" + clientId + "]");
        return strBuff.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        return super.equals(o);
    }

    public boolean isAutoSubscribeDefaultTopics()
    {
        return autoSubscribeDefaultTopics;
    }

    public static void main(String[] args)
            throws Exception
    {
        ConnectMessage m = new ConnectMessage("+919910000474:1", false, (short) 10);
        System.out.println("clientId " + m.getClientId());
        System.out.println("version " + m.getVersion());
    }
}
