package QuizServer;

import Shared.IQuizManager;
import fontyspublisher.IRemotePublisherForDomain;
import fontyspublisher.RemotePublisher;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

import static javafx.application.Application.launch;

/**
 * Created by myron on 13-12-17.
 */
public class Main
{
    private static final int portNumber = 1098;
    private Registry registry = null;
    private static final String bindingName = "serverInform";
    private static final String bindingNameQM = "gameManager";
    private static final String propertyName = "";
    private IQuizManager quizManager = null;
    private IRemotePublisherForDomain remotePublisherForDomain;

    public static void main(String[] args)
    {
        printIPAddresses();
        Main server = new Main();
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

    public Main()
    {
        //Create remote publisher
        try
        {
            remotePublisherForDomain = new RemotePublisher();
        }
        catch (Exception ex)
        {
            System.out.println("Server: Cannot create remotePublisherForDomain " + ex.getMessage());
        }
        //Create quizManager
        try
        {
            quizManager = new QuizManager(remotePublisherForDomain);
        }
        catch (RemoteException ex)
        {
            System.out.println("Server: Cannot create GameManager");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            quizManager = null;
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
            if(registry != null && quizManager != null)
            {
                registry.rebind(bindingName, remotePublisherForDomain);
                registry.rebind(bindingNameQM, quizManager);
            }
            else
            {
                throw new RemoteException();
            }
        }
        catch (RemoteException ex)
        {
            System.out.println("Server: Cannot bind");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
    }
}
