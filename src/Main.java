import model.ByteArray;
import model.Pessoa;
import model.StringUtil;
import org.jbpm.JbpmException;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static Pessoa pessoa = new Pessoa(null, "samuel", "melo");
    static Pessoa pessoa1 = new Pessoa(null, "anderson", "silva");
    static String path = "C:\\obj\\obj.hex";
    static Byte[] byteArray = null;

    public static void main(String[] args) {


        System.out.println("pessoa 1  hashCode-1868363133");
        System.out.println(pessoa.toString());
        System.out.println(pessoa.hashCode());
        System.out.println("pessoa 2  hashCode-872071634");
        System.out.println(pessoa1.toString());
        System.out.println(pessoa1.hashCode());
//        serializar(pessoa);
//        serializar(pessoa1);
        objetoForByte(pessoa);


    }

    public static void serializar(byte[] o) {
        try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(path))) {
           oOut.write(o);

            System.out.println("Objeto Serializado!");
            oOut.close();


        } catch (RuntimeException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Pessoa deSerializar() {
        try (ObjectInputStream oOut = new ObjectInputStream(new FileInputStream(path))) {
            Pessoa p = (Pessoa) oOut.readObject();
            System.out.println("Objeto Deserializado!");
            oOut.close();
            return p;



        } catch (RuntimeException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void objetoForByte(Object o){
        try {
            Path p = FileSystems.getDefault().getPath("",path);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
           objectOutputStream.writeObject(o);
            objectOutputStream.writeObject(o);
            objectOutputStream.flush();
            objectOutputStream.close();
            byteArrayOutputStream.close();
            byteArrayOutputStream.toByteArray();
            serializar(byteArrayOutputStream.toByteArray());
            byte[] data = null;
            data =  byteArrayOutputStream.toByteArray();
            StringUtil.toHexString(data);
            data = Files.readAllBytes(p);
            String str = new String(byteArrayOutputStream.toByteArray());
              convert(byteArrayOutputStream.toByteArray());
        } catch (RuntimeException | IOException e){

        }
    }
    public static ByteArray convert(Serializable o) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(memoryStream);
            objectStream.writeObject(o);
            objectStream.flush();
            bytes = memoryStream.toByteArray();
            String str = new String(StringUtil.toHexString(bytes));
            System.out.println(str);

        } catch (IOException e) {
            throw new JbpmException("could not serialize: " + o, e);
        }
        ByteArray byteArray = new ByteArray(bytes);
        byteArray.getByteBlocks();
        System.out.println();
        return  byteArray;
    }

    public static void convertForHex(byte[] bytes){
        System.out.println(StringUtil.toHexString(bytes));
    }


}
