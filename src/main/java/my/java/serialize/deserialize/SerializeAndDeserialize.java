package my.java.serialize.deserialize;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ds.linklist.test.Employee;
import com.ds.linklist.test.Employee.Sex;

public class SerializeAndDeserialize
{
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
    {

        Employee emp = new Employee("Himanshu", 1, "HCL", Sex.Male, 25000.0, "harry7777");

        // Object - Buffer - File (OutputStream)
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Harry.txt")));
        // Write in File
        oos.writeObject(emp);
        // Close Output Stream
        oos.close();

        // Object - Buffer - File (InputStream)
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Harry.txt")));
        // Read From File
        Object object = ois.readObject();
        // Close Input Stream
        ois.close();

        // Check password field is null because password field is transient and it will not get serialized into file.
        System.out.println(object);
    }
}
