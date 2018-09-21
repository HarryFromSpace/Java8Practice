package com.ds.linklist.entity;

public class Node
{
    private Node preNode;
    private String data;
    private Node nextNode;
    


    public Node()
    {
        super();
    }


    public Node(Node preNode, String data, Node nextNode)
    {
        super();
        this.data = data;
        this.nextNode = nextNode;
        this.preNode = preNode;
    }


    public String getData()
    {
        return data;
    }


    public void setData(String data)
    {
        this.data = data;
    }


    public Node getNextNode()
    {
        return nextNode;
    }


    public void setNextNode(Node nextNode)
    {
        this.nextNode = nextNode;
    }


    public Node getPreNode()
    {
        return preNode;
    }


    public void setPreNode(Node preNode)
    {
        this.preNode = preNode;
    }


    @Override
    public String toString()
    {
        return "Node [data=" + data + ", nextNode=" + nextNode + ", preNode=" + preNode + "]";
    }

}
