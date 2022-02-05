package com.yanci.flink.flinker;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// 5a 12 5b ba 34 5b bb 88 05 5a
    	Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        String[] nn = n.split("5a");
//        System.out.println(Arrays.toString(nn));
        int len = nn.length;
        List<String> lr = new ArrayList<>();
        for(int i = 1; i < len; i++) {
            
        	String aa = nn[i];
        	String t = aa.replace(" ", "");
            if(t.length() <= 2) {
                continue;
            }
        	String l = t.substring(t.length() - 2, t.length());
        	BigInteger bi = new BigInteger(l, 16);
        	int al = bi.intValue();
        	String nt = t.substring(0, t.length() - 2);
        	String zy = nt.replaceAll("5bba", "5a").replaceAll("5bbb", "5b");
        	
        	int zl = zy.length()/2;
        	if(zl == al) {
        		lr.add(aa);
        	}
        }
        
        Iterator<String> it = lr.iterator();
        if(it.hasNext()) {
        	System.out.print("5a");
        }
        while(it.hasNext()) {
        	System.out.print(it.next());
        	System.out.print("5a");
        }
        
    }
        
}
