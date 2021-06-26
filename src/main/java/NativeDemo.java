import com.sun.jna.Library;
import com.sun.jna.Native;

public  class NativeDemo {
//	static {
//		System.load("D:\\workSpace\\vs\\test\\x64\\Debug\\Test.dll");
//	}
	{
		System.load(System.getProperty("user.dir")+"\\utils\\NativeDemo\\x64\\Debug\\NativeDemo.dll");
	}

	public interface Clibrary extends Library {
		Clibrary INSTANCE = (Clibrary) Native.load("test", Clibrary.class);

		void testprintf(int a,float b);

		void printff();
	}

	public native void sayHello();


	public static void main(String[] args) {
		new NativeDemo().sayHello();
		Clibrary.INSTANCE.testprintf(1,2.0f);
		Clibrary.INSTANCE.printff();
	}
}