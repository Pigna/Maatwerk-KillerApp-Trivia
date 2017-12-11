package QuizMaster;

import QuizServer.QuizManager;
import fontyspublisher.IRemotePublisherForDomain;
import fontyspublisher.RemotePublisher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

public class Main extends Application {

    private static final int portNumber = 1098;
    private Registry registry = null;
    private static final String bindingName = "AEXBanner";
    private static final String propertyName = "EffectenBeurs";
    private QuizManager quizManager = null;
    private IRemotePublisherForDomain remotePublisherForDomain;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("QuizMaster.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        printIPAddresses();
        launch(args);
    }

    // Print IP addresses and network interfaces
    private static void printIPAddresses() {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + localhost.getHostAddress());
            // Just in case this host has multiple IP addresses....
            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                System.out.println("Server: Full list of IP addresses:");
                for (InetAddress allMyIp : allMyIps) {
                    System.out.println("    " + allMyIp);
                }
            }
        }
        catch (UnknownHostException ex) {
            System.out.println("Server: Cannot get IP address of local host");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }

        try {
            System.out.println("Server: Full list of network interfaces:");
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    System.out.println("        " + enumIpAddr.nextElement().toString());
                }
            }
        }
        catch (SocketException ex) {
            System.out.println("Server: Cannot retrieve network interface list");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }
        System.out.println(localhost.getHostAddress() + ":" + portNumber);
    }

    private void SetupRMI()
    {
        try
        {
            quizManager = new QuizManager();
        }
        catch (RemoteException ex)
        {
            System.out.println("Server: Cannot create EffectenBeurs");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            quizManager = null;
        }
        //Create remote publisher
        try
        {
            remotePublisherForDomain = new RemotePublisher();
            remotePublisherForDomain.registerProperty(propertyName);
        }
        catch (Exception ex)
        {
            System.out.println("Server: Cannot create remotePublisherForDomain " + ex.getMessage());
        }
        // Create registry at port number
        try
        {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        }
        catch (RemoteException ex)
        {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Bind AEXBanner using registry
        try
        {
            if(registry != null)
            {
                registry.rebind(bindingName, remotePublisherForDomain);
            }
            else
            {
                throw new RemoteException();
            }
        }
        catch (RemoteException ex)
        {
            System.out.println("Server: Cannot bind student administration");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
    }
}
