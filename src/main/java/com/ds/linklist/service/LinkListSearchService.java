package com.ds.linklist.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.ds.linklist.entity.Node;

public class LinkListSearchService
{

    public static void main(String[] args)
    {
        @SuppressWarnings("unused")
        List<Node> list = new LinkedList<>();
        int option;
        try (Scanner sc = new Scanner(System.in))
        {
            System.out.println("****** MENU *****");
            System.out.println(" 1) Add Node at Head \n 2) Remove Node at Head \n 3) Add Node at Tail \n 4) Remove Node at Tail \n 5) Show Link List");
            System.out.println("Enter Your Option/Choice : ");
            option = sc.nextInt();
            switch (option)
            {
                case 1:
                    
                    break;
                default:
                    break;
            }

        }
    }

}
