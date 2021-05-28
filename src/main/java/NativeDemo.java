public  class NativeDemo {
	{
		System.load(System.getProperty("user.dir")+"\\utils\\NativeDemo\\x64\\Debug\\NativeDemo.dll");
	}

	public native void sayHello();


	public static void main(String[] args) {
		new NativeDemo().sayHello();
	}
}