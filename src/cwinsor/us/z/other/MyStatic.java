package cwinsor.us.z.other;

public final class MyStatic {

	public static void errorHalt(String in) {
		System.out.println(in);
		Thread.dumpStack();
		Runtime.getRuntime().halt(0);
	}

}
