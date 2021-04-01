package test;



import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import test.Commands.DefaultIO;
import test.Server.ClientHandler;

public class AnomalyDetectionHandler implements ClientHandler{

	public class SocketIO implements DefaultIO
	{
		Scanner in;
		PrintWriter out;
		
		
		public SocketIO(InputStream inputStream, OutputStream outputStream) {
			in=new Scanner(inputStream);
			out=new PrintWriter(outputStream);
		}

		@Override
		public String readText() {
			return in.nextLine();
		}

		@Override
		public void write(String text) {
			out.print(text);
			out.flush();
		}

		@Override
		public float readVal() {
			return in.nextFloat();
		}

		@Override
		public void write(float val) {
			out.print(val);
			out.flush();
		}

		public void close() {
			in.close();
			out.close();
		}
	}

	@Override
	public void communicate(InputStream inFromClient, OutputStream outFromClient) {
		// TODO Auto-generated method stub
		SocketIO socketIO=new SocketIO(inFromClient,outFromClient) ;
		CLI cli=new CLI(socketIO);
		cli.start();
		socketIO.write("bye\n");
		socketIO.close();
	}

}
