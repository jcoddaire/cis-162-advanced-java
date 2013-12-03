package examples.interfaceHierarchy;

public class Program
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // instantiate a ClassAB as ClassAB - we have access to all methods
        ClassBC x = new ClassBC();
        x.methodA();
        x.methodB();
        x.methodC();

        // instantiate a ClassAB as InterfaceA - we only have access to methodA
        InterfaceA y = new ClassBC();
        y.methodA();

        // instantiate a ClassAB as InterfaceB - we only have access to methodA and methodB
        InterfaceB z = new ClassBC();
        z.methodA();
        z.methodB();
    }
}
