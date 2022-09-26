import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements StudentServerIF, CourseServerIF {

  private static StudentDataIF studentData;
  private static CourseDataIF courseData;

  public Server() throws RemoteException {
    super( );
  }

  public static void main(String[] args) throws NotBoundException {
    try {
      System.setProperty("java.rmi.server.hostname","218.38.137.27");

      Server server = new Server();
      Naming.bind("Server", server);
      System.out.println("Server is ready!");

      studentData = (StudentDataIF) Naming.lookup("Data");
      courseData = (CourseDataIF) Naming.lookup("Data");

    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (AlreadyBoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public ArrayList<Student> getAllStudentData() throws RemoteException {
    return studentData.getAllStudentData();
  }

  @Override
  public ArrayList<Course> getAllCourseData() throws RemoteException {
    return courseData.getAllCourseData();
  }
}
